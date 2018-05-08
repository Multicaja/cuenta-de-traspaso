--
--    Copyright 2010-2016 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

-- // create_sp_verifica_limite
-- Migration SQL that makes the change goes here.

CREATE OR REPLACE FUNCTION ${schema}.in_cdt_verifica_limites
(
    IN _id_cuenta               NUMERIC,
    IN _id_movimiento           NUMERIC,
    IN _monto                   NUMERIC,
    OUT _NumError               VARCHAR,
    OUT _MsjError               VARCHAR
) RETURNS record AS
$BODY$
    	DECLARE
         _limite RECORD;
         _monto_acumulado   NUMERIC;
         _current_date      DATE;
    	BEGIN
	        _NumError := '0';
	        _MsjError := '';
            _current_date:= current_date;

		      IF COALESCE(_id_movimiento, 0) = 0 THEN
	            _NumError := '1001';
	        	_MsjError := '[in_cdt_verifica_limites] El Id Movimiento no puede ser 0';
	        	RETURN;
	        END IF;

          IF COALESCE(_monto, 0) = 0 THEN
              _NumError := '1002';
              _MsjError := '[in_cdt_verifica_limites] El monto no puede ser 0';
              RETURN;
          END IF;
            BEGIN
                FOR _limite IN
                    SELECT
                        id,
                        id_regla_acumulacion,
                        descripcion,
                        valor,
                        cod_operacion
                    FROM
                         ${schema}.cdt_limite
                    WHERE
                        id_movimiento = _id_movimiento AND
                        estado = 'ACTIVO'
                    ORDER BY
                        id_regla_acumulacion asc
                LOOP -- RECORRO Y VERIFICO LOS LIMITES

                        IF( _limite.id_regla_acumulacion = -1) THEN -- VERIFICACION DE LIMITES SIMPLES
                            BEGIN
                                CASE
                                    WHEN _limite.cod_operacion = 'MAYORQIG' THEN
                                        IF (_monto < _limite.valor) THEN
                                            _NumError := _limite.id;
                                            _MsjError := _limite.descripcion||' '||_limite.valor;
                                            RETURN;
                                        END IF;
                                    WHEN _limite.cod_operacion = 'MENORQIG' THEN
                                        IF (_monto > _limite.valor) THEN
                                            _NumError := _limite.id;
                                            _MsjError := _limite.descripcion||' '||_limite.valor;
                                            RETURN;
                                        END IF;
                                    WHEN _limite.cod_operacion = 'IGUAL' THEN
                                        IF (_monto != _limite.valor) THEN
                                           _NumError := _limite.id;
                                           _MsjError := _limite.descripcion||' '||_limite.valor;
                                            RETURN;
                                        END IF;
                                END CASE;
                            EXCEPTION
                            WHEN OTHERS THEN
                                _NumError := SQLSTATE;
                                _MsjError := '[in_cdt_verifica_limites] Error al verificar contra monto acumulado. CAUSA ('|| SQLERRM ||')';
                                 RETURN;
                            END;
                        ELSE -- VERIFICACION DE LIMITES CONTRA ACUMULADORES
                            BEGIN
                                SELECT  -- SIRVE PARA BUSCAR EL MONTO ACUMULADOR DE PARA VALIDAR SU LIMITE
                                    COALESCE(monto,0)
                                INTO
                                    _monto_acumulado
                                FROM
                                     ${schema}.cdt_cuenta_acumulador
                                WHERE
                                    id_regla_acumulacion = _limite.id_regla_acumulacion AND
                                    id_cuenta = _id_cuenta AND
                                    _current_date BETWEEN fecha_inicio AND fecha_fin;

                                CASE

                                    WHEN _limite.cod_operacion = 'MAYORQIG' THEN
                                        IF (_monto < _limite.valor) THEN
                                            _NumError := _limite.id;
                                            _MsjError := _limite.descripcion;
                                            RETURN;
                                        END IF;
                                    WHEN _limite.cod_operacion = 'MENORQIG' THEN
                                        IF (_monto > _limite.valor) THEN
                                            _NumError := _limite.id;
                                            _MsjError := _limite.descripcion;
                                            RETURN;
                                        END IF;
                                    WHEN _limite.cod_operacion = 'IGUAL' THEN
                                        IF (_monto != _limite.valor) THEN
                                            _NumError := _limite.id;
                                            _MsjError := _limite.descripcion;
                                            RETURN;
                                        END IF;
                                END CASE;
                            EXCEPTION
                                WHEN OTHERS THEN
                                    _NumError := SQLSTATE;
                                    _MsjError := '[in_cdt_verifica_limites] Error al verificar contra monto acumulado. CAUSA ('|| SQLERRM ||')';
                                RETURN;
                            END;
                        END IF;
                END LOOP;

            EXCEPTION
                WHEN OTHERS THEN
                    _NumError := SQLSTATE;
                    _MsjError := '[in_cdt_verifica_limites] Error desconocido al recorrer Limites. CAUSA ('|| SQLERRM ||')';
                RETURN;
            END;

        EXCEPTION
            WHEN OTHERS THEN
                _NumError := SQLSTATE;
                _MsjError := '[in_cdt_verifica_limites] Error desconocido. CAUSA ('|| SQLERRM ||')';
            RETURN;
    	END;
$BODY$
LANGUAGE 'plpgsql';

-- //@UNDO
-- SQL to undo the change goes here.

DROP FUNCTION IF EXISTS ${schema}.in_cdt_verifica_limites;

