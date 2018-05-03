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

-- // create_sp_mc_crea_movimiento_cuenta
-- Migration SQL that makes the change goes here.

CREATE OR REPLACE FUNCTION ${schema}.mc_cdt_crea_movimiento_cuenta
(
    IN _id_cuenta               NUMERIC,
    IN _id_movimiento           NUMERIC,
    IN _id_mov_referencia       NUMERIC,
    IN _id_tx_externo           VARCHAR,
    IN _glosa                   VARCHAR,
    IN _monto                   NUMERIC,
    OUT _id_movimiento_cuenta   NUMERIC,
    OUT _NumError               VARCHAR,
    OUT _MsjError               VARCHAR
) RETURNS record AS
$BODY$
DECLARE
    _fecha_ini    TIMESTAMP;
    _fecha_fin    TIMESTAMP;
    _current_date DATE;
BEGIN
    _NumError := '0';
    _MsjError := '';
    _current_date:= current_date;

    IF COALESCE(_id_cuenta, 0) = 0 THEN
        _NumError := '1001';
    	_MsjError := '[mc_cdt_crea_movimiento_cuenta] El Id Cuenta no puede ser 0';
    	RETURN;
    END IF;

    IF COALESCE(_id_movimiento, 0) = 0 THEN
        _NumError := '1002';
        _MsjError := '[mc_cdt_crea_movimiento_cuenta] El Id Movimiento no puede ser 0';
        RETURN;
    END IF;

    IF COALESCE(_id_tx_externo, '') = '' THEN
        _NumError := '1003';
        _MsjError := '[mc_cdt_crea_movimiento_cuenta] EL Id Tx Externo no puede ser vacio';
        RETURN;
    END IF;

    -- LLAMADA
    SELECT
        ${schema}.in_cdt_procesa_acumuladores
        (
            _id_movimiento,
            _id_cuenta,
            _monto,
            _NumError,
            _MsjError
        );

    IF(_NumError != '0') THEN
        RETURN; -- RETORNA ERROR SI EXISTE ERROR EN SP in_cdt_procesa_acumuladores
    ELSE
        BEGIN
            _id_movimiento_cuenta :=  nextval('${schema}.cdt_movimiento_cuenta_id_s1');
            INSERT INTO
                ${schema}.cdt_movimiento_cuenta
                (
                    id,
                    id_cuenta,
                    id_movimiento,
                    id_mov_referencia,
                    id_tx_externo,
                    glosa,
                    monto,
                    fecha_registro,
                    estado,
                    fecha_estado
                )
            VALUES
                (
                    _id_movimiento_cuenta,
                    _id_cuenta,
                    _id_movimiento,
                    _id_mov_referencia,
                    _id_tx_externo,
                    _glosa,
                    _monto,
                    localtimestamp,
                    'PEND',
                    localtimestamp
                );
        -- LLAMADA A SP QUE VERIFICA QUE EL MOVIMIENTO CUMPLA CON LOS LIMITES ESTABLECIDOS PARA EL TIPO DE MOVIMIENTO
        SELECT
            ${schema}.in_cdt_verifica_limites
            (
                _id_movimiento,
                _id_cuenta,
                _monto,
                _NumError,
                _MsjError
            );
        IF  _NumError != '0' THEN
            RETURN;
        END IF;

        EXCEPTION
        WHEN OTHERS THEN
            _NumError := SQLSTATE;
            _MsjError := '[mc_cdt_crea_movimiento_cuenta] Error al insertar movimiento cuenta CAUSA ('|| SQLERRM ||')';
            RETURN;
        END;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        _NumError := SQLSTATE;
        _MsjError := '[mc_cdt_crea_movimiento_cuenta] Error desconocido al crear movimiento cuenta CAUSA ('|| SQLERRM ||')';
    RETURN;
END;
$BODY$
LANGUAGE 'plpgsql';

-- //@UNDO
-- SQL to undo the change goes here.

 DROP FUNCTION IF EXISTS  ${schema}.mc_cdt_crea_movimiento_cuenta;
