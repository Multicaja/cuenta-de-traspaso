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
    IN _id_cuenta               VARCHAR,
    IN _id_fase_movimiento      NUMERIC,
    IN _id_mov_referencia       NUMERIC,
    IN _id_tx_externo           VARCHAR,
    IN _glosa                   VARCHAR,
    IN _monto                   NUMERIC,
    OUT _id_movimiento_cuenta   NUMERIC,
    OUT _num_error              VARCHAR,
    OUT _msj_error              VARCHAR
) AS $$
DECLARE
    _ind_confirmacion VARCHAR(1);
    _id_cuenta_interno NUMERIC;
    _current_date DATE;
BEGIN
    _num_error := '0';
    _msj_error := '';
    _current_date:= current_date;
    _id_movimiento_cuenta:= 0;

    IF TRIM(COALESCE(_id_cuenta, '')) = '' THEN
      _num_error := '1001';
    	_msj_error := '[mc_cdt_crea_movimiento_cuenta] El Id Cuenta no puede ser vacio';
    	RETURN;
    END IF;

    IF COALESCE(_id_fase_movimiento, 0) = 0 THEN
        _num_error := '1002';
        _msj_error := '[mc_cdt_crea_movimiento_cuenta] El Id Movimiento no puede ser 0';
        RETURN;
    END IF;

    IF COALESCE(_id_tx_externo, '') = '' THEN
        _num_error := '1003';
        _msj_error := '[mc_cdt_crea_movimiento_cuenta] EL Id Tx Externo no puede ser vacio';
        RETURN;
    END IF;

     BEGIN
        -- BUSCA FASE MOVIMIENTO
        SELECT
          ind_confirmacion
        INTO
          _ind_confirmacion
        FROM
          ${schema}.cdt_fase_movimiento
        WHERE
          id = _id_fase_movimiento;

        -- BUSCA LA CUENTA
        SELECT
           id
        INTO
           _id_cuenta_interno
        FROM
           ${schema}.cdt_cuenta
        WHERE
           id_externo = _id_cuenta;

        INSERT INTO
            ${schema}.cdt_movimiento_cuenta
            (
                id_cuenta,
                id_fase_movimiento,
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
                _id_cuenta_interno,
                _id_fase_movimiento,
                _id_mov_referencia,
                _id_tx_externo,
                _glosa,
                _monto,
                timezone('utc', now()),
                'PEND',
                timezone('utc', now())
            )
        RETURNING id INTO _id_movimiento_cuenta;

        -- LLAMADA
        SELECT
            PAC._num_error,
            PAC._msj_error
        INTO
          _num_error,
          _msj_error
        FROM
            ${schema}.in_cdt_procesa_acumuladores
            (
                _id_fase_movimiento,
                _id_cuenta_interno,
                _monto
            ) PAC;

        IF(_num_error != '0') THEN
             RAISE EXCEPTION 'Error en Pocesar Acumuladores'; -- RETORNA ERROR SI EXISTE ERROR EN SP in_cdt_procesa_acumuladores
        END IF;


        -- LLAMADA A SP QUE VERIFICA QUE EL MOVIMIENTO CUMPLA CON LOS LIMITES ESTABLECIDOS PARA EL TIPO DE MOVIMIENTO
        SELECT
          VLI._num_error,
          VLI._msj_error
        INTO
          _num_error,
          _msj_error
        FROM
            ${schema}.in_cdt_verifica_limites
            (
              _id_cuenta_interno,
              _id_fase_movimiento,
              _monto
            )VLI;
        IF  _num_error != '0' THEN
          RAISE EXCEPTION 'Error en Verificacion de Limites';
        END IF;

        IF(_ind_confirmacion = 'S') THEN
          INSERT INTO
            ${schema}.cdt_confirmacion_movimiento
            (
              id_mov_cuenta_origen,
              id_mov_cuenta_confirmacion
            )
          VALUES
            (
              _id_mov_referencia,
              _id_movimiento_cuenta
            );
        END IF;
    EXCEPTION
    WHEN OTHERS THEN
      IF  _num_error != '0' THEN
           RETURN;
       ELSE
          _num_error := SQLSTATE;
          _msj_error := '[mc_cdt_crea_movimiento_cuenta] Error al insertar movimiento cuenta CAUSA ('|| SQLERRM ||')';
          RETURN;
       END IF;
    END;

EXCEPTION
    WHEN OTHERS THEN
        _num_error := SQLSTATE;
        _msj_error := '[mc_cdt_crea_movimiento_cuenta] Error desconocido al crear movimiento cuenta CAUSA ('|| SQLERRM ||')';
    RETURN;
END;
$$
LANGUAGE 'plpgsql';

-- //@UNDO
-- SQL to undo the change goes here.

 DROP FUNCTION IF EXISTS  ${schema}.mc_cdt_crea_movimiento_cuenta;
