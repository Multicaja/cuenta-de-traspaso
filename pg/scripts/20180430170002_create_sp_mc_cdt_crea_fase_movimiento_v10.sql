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

-- // create_sp_mc_cdt_crea_movimiento
-- Migration SQL that makes the change goes here.

CREATE OR REPLACE FUNCTION ${schema}.mc_cdt_crea_fase_movimiento_v10
(
    IN _nombre            VARCHAR,
    IN _descripcion       VARCHAR,
    IN _signo             NUMERIC,
    IN _ind_confirmacion  VARCHAR,
    OUT _num_error        VARCHAR,
    OUT _msj_Error        VARCHAR
) AS $$

    	DECLARE

    	BEGIN
	        _num_error := '0';
	        _msj_Error := '';

		    IF TRIM(COALESCE(_nombre, '')) = '' THEN
          _num_error := 'MC001';
          _msj_Error := '[mc_cdt_crea_fase_movimiento] El nombre de la fase movimiento no puede ser vacio';
          RETURN;
        END IF;

        IF COALESCE(_signo, 0) != -1 AND  COALESCE(_signo, 0) != 1 THEN
          _num_error := 'MC002';
          _msj_Error := '[mc_cdt_crea_fase_movimiento] El Signo de la fase de movimiento debe ser 1 o -1';
          RETURN;
        END IF;

         IF TRIM(COALESCE(_ind_confirmacion, ''))  = '' THEN
          _num_error := 'MC003';
          _msj_Error := '[mc_cdt_crea_fase_movimiento] El Indicador Confirmacion no puede ser vacio';
          RETURN;
        END IF;

        INSERT INTO ${schema}.cdt_fase_movimiento
        (
          nombre,
          descripcion,
          signo,
          ind_confirmacion,
          estado,
          fecha_estado,
          fecha_creacion
        )
        VALUES
          (
            _nombre,
            _descripcion,
            _signo,
            _ind_confirmacion,
            'ACTIVO',
            timezone('utc', now()),
            timezone('utc', now())
          );

        EXCEPTION
            WHEN OTHERS THEN
              _num_error := SQLSTATE;
              _msj_Error := '[mc_cdt_crea_fase_movimiento] Error al crear Fase de Movimiento. CAUSA ('|| SQLERRM ||')';
            RETURN;
    	END;
$$
LANGUAGE 'plpgsql';

-- //@UNDO
-- SQL to undo the change goes here.
 DROP FUNCTION IF EXISTS ${schema}.mc_cdt_crea_fase_movimiento_v10(VARCHAR,VARCHAR,NUMERIC,VARCHAR);