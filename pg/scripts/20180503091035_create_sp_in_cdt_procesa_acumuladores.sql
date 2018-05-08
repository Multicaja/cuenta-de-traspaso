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

-- // create_sp_mc_cdt_crea_cuenta_acumulacion
-- Migration SQL that makes the change goes here.

CREATE OR REPLACE FUNCTION ${schema}.in_cdt_procesa_acumuladores
(
    IN _id_movimiento           NUMERIC,
    IN _id_cuenta               NUMERIC,
    IN _monto                   NUMERIC,
    OUT _NumError               VARCHAR,
    OUT _MsjError               VARCHAR
) RETURNS record AS
$BODY$
        DECLARE
            _rec_tipo_mov RECORD;
            _rec_regla_acum RECORD;
            _current_date DATE;
            _fecha_ini TIMESTAMP;
            _fecha_fin TIMESTAMP;
        BEGIN

            _NumError := '0';
            _MsjError := '';
            _current_date:= current_date;


            IF COALESCE(_id_cuenta, 0) = 0 THEN
                _NumError := '1001';
                _MsjError := '[in_cdt_procesa_acumuladores] El Id Cuenta no puede ser 0';
                RETURN;
            END IF;

            IF COALESCE(_id_movimiento, 0) = 0 THEN
                _NumError := '1002';
                _MsjError := '[in_cdt_procesa_acumuladores] El Id Movimiento no puede ser 0';
                RETURN;
            END IF;

            IF COALESCE(_monto, 0) = 0 THEN
                _NumError := '1003';
                _MsjError := '[in_cdt_procesa_acumuladores] El Monto no puede ser 0';
                RETURN;
            END IF;

            FOR _rec_tipo_mov IN
                SELECT
                    MOV.id                  AS id_movimiento,
                    MOV.nombre              AS nombre_movimiento,
                    MOV.signo              AS movimiento_signo,
                    MTM.id_tipo_movimiento  AS id_tipo_movimiento,
                    TMO.nombre              AS nombre_tipo_moviniento
                FROM
                    ${schema}.cdt_movimiento MOV
                INNER JOIN ${schema}.cdt_movimiento_tipo_mov MTM ON MTM.id_movimiento = _id_movimiento
                INNER JOIN ${schema}.cdt_tipo_movimiento TMO ON TMO.id = MTM.id_tipo_movimiento
                WHERE
                    MOV.id = _id_movimiento
            LOOP -- RECORRO Y VERIFICO ACUMULADORES
                FOR _rec_regla_acum IN
                    SELECT
                        id,
                        periocidad,
                        codigo_operacion
                    FROM
                        ${schema}.cdt_regla_acumulacion
                    WHERE
                        id_tipo_movimiento = _rec_tipo_mov.id_tipo_movimiento AND
                        estado = 'ACTIVO'
                LOOP
                    BEGIN
                        IF (_rec_regla_acum.periocidad != 'VIDA') THEN
                            CASE
                                WHEN _rec_regla_acum.periocidad = 'DIA' THEN -- CALCULA FECHA INICIO Y FIN PARA EL DIA.
                                    _fecha_ini = _current_date;
                                    _fecha_fin = _current_date;
                                WHEN _rec_regla_acum.periocidad = 'SEM' THEN -- CALCULA LA FECHA DE INICIO Y FIN DE UNA SEMANA.
                                    _fecha_ini = date_trunc('week', _current_date)::date;
                                    _fecha_fin = (date_trunc('week', _current_date)+ '6 days'::interval)::date;
                                WHEN _rec_regla_acum.periocidad = 'MEN' THEN -- CALCULA LA FECHA DE INICIO Y FIN DE UN MES.
                                    _fecha_ini = cast(date_trunc('month', _current_date) as date);
                                    _fecha_fin = cast((date_trunc('MONTH', _current_date) + INTERVAL '1 MONTH - 1 day') as date);
                            END CASE;

                            UPDATE
                                ${schema}.cdt_cuenta_acumulador
                            SET
                                monto = CASE
                                            WHEN _rec_regla_acum.codigo_operacion = 'SUM' THEN
                                               (monto+(_monto*_rec_tipo_mov.movimiento_signo))
                                            WHEN _rec_regla_acum.codigo_operacion = 'COUNT' THEN
                                               (monto + 1)
                                        END,
                                fecha_actualizacion = LOCALTIMESTAMP
                            WHERE
                                id_regla_acumulacion = _rec_regla_acum.id AND
                                id_cuenta = _id_cuenta AND
                                fecha_inicio = _fecha_ini AND
                                fecha_fin = _fecha_fin;
                            IF NOT FOUND THEN
                                INSERT INTO
                                    ${schema}.cdt_cuenta_acumulador
                                        (
                                            id,
                                            id_regla_acumulacion,
                                            id_cuenta,
                                            monto,
                                            fecha_inicio,
                                            fecha_fin,
                                            fecha_creacion,
                                            fecha_actualizacion
                                        )
                                    VALUES
                                        (
                                            nextval('${schema}.cdt_cuenta_acumulador_id_s1'),
                                            _rec_regla_acum.id,
                                            _id_cuenta,
                                            CASE
                                                WHEN _rec_regla_acum.codigo_operacion = 'SUM' THEN
                                                    (_monto*_rec_tipo_mov.movimiento_signo)
                                                WHEN _rec_regla_acum.codigo_operacion = 'COUNT' THEN
                                                    1
                                            END,
                                            _fecha_ini,
                                            _fecha_fin,
                                            LOCALTIMESTAMP,
                                            LOCALTIMESTAMP
                                        );
                            END IF;
                        ELSE
                            UPDATE
                                ${schema}.cdt_cuenta_acumulador
                            SET
                                monto = CASE
                                            WHEN _rec_regla_acum.codigo_operacion = 'SUM' THEN
                                               (monto+(_monto*_rec_tipo_mov.movimiento_signo))
                                            WHEN _rec_regla_acum.codigo_operacion = 'COUNT' THEN
                                               (monto + 1)
                                        END,
                                fecha_actualizacion = LOCALTIMESTAMP
                            WHERE
                                id_regla_acumulacion =  _rec_regla_acum.id AND
                                id_cuenta = _id_cuenta;
                            IF NOT FOUND THEN
                                INSERT INTO
                                    ${schema}.cdt_cuenta_acumulador
                                    (
                                        id,
                                        id_regla_acumulacion,
                                        id_cuenta,
                                        monto,
                                        fecha_inicio,
                                        fecha_fin,
                                        fecha_creacion,
                                        fecha_actualizacion
                                    )
                                VALUES
                                    (
                                        nextval('${schema}.cdt_cuenta_acumulador_id_s1'),
                                        _rec_regla_acum.id,
                                        _id_cuenta,
                                        CASE
                                            WHEN _rec_regla_acum.codigo_operacion = 'SUM' THEN
                                                (_monto*_rec_tipo_mov.movimiento_signo)
                                            WHEN _rec_regla_acum.codigo_operacion = 'COUNT' THEN
                                                1
                                        END,
                                        to_date('01-01-1900', 'dd-MM-YYYY'),
                                        to_date('31-12-2100', 'dd-MM-YYYY'),
                                        LOCALTIMESTAMP,
                                        LOCALTIMESTAMP
                                    );
                            END IF;-- END IF NOT FOUND
                        END IF;-- EN IF PERIOCIDAD
                    EXCEPTION
                        WHEN OTHERS THEN
                            _NumError := SQLSTATE;
                            _MsjError := '[in_cdt_procesa_acumuladores] Error al insertar o actualizar acumulacion. CAUSA ('|| SQLERRM ||')';
                        RETURN;
                    END;
                END LOOP;-- END LOOP REGLA ACUMULACION
            END LOOP; -- END LOOP TIPO MOVIMIENTO

    EXCEPTION
        WHEN OTHERS THEN
            _NumError := SQLSTATE;
            _MsjError := '[in_cdt_procesa_acumuladores] Error desconocido en crea cuenta acumulacion. CAUSA ('|| SQLERRM ||')';
        RETURN;
    END;
$BODY$
LANGUAGE 'plpgsql';
-- //@UNDO
-- SQL to undo the change goes here.


 DROP FUNCTION IF EXISTS  ${schema}.in_cdt_procesa_acumuladores;

