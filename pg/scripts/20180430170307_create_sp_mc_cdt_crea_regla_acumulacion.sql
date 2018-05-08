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

-- // create_sp_mc_cdt_crea_regla_acumulacion
-- Migration SQL that makes the change goes here.

CREATE OR REPLACE FUNCTION ${schema}.mc_cdt_crea_regla_acumulacion
(
    IN _id_tipo_movimiento  NUMERIC,
    IN _periocidad          VARCHAR,
    IN _codigo_operacion    VARCHAR,
    OUT _NumError           VARCHAR,
    OUT _MsjError           VARCHAR
) RETURNS record AS
$BODY$
    	DECLARE

    	BEGIN
	        _NumError := '0';
	        _MsjError := '';

		    IF COALESCE(_id_tipo_movimiento, 0) = 0 THEN
	            _NumError := '1001';
	        	_MsjError := '[mc_cdt_crea_regla_acumulacion] El Id Tipo Movimiento no puede ser 0';
	        	RETURN;
	        END IF;

            IF TRIM(COALESCE(_periocidad, '')) = '' THEN
                _NumError := '1002';
                _MsjError := '[mc_cdt_crea_regla_acumulacion] La Periocidad no puede ser vacia';
                RETURN;
            END IF;

             IF TRIM(COALESCE(_codigo_operacion, '')) = '' THEN
                _NumError := '1002';
                _MsjError := '[mc_cdt_crea_regla_acumulacion] El Codigo Operacion no puede ser vacio';
                RETURN;
            END IF;

        	INSERT INTO ${schema}.cdt_regla_acumulacion
	    		(
	    			id,
	    			id_tipo_movimiento,
                    periocidad,
	    			codigo_operacion,
                    estado,
	    			fecha_estado,
	    			fecha_creacion
	    		)
        	VALUES
        		(
        			nextval('${schema}.cdt_regla_acumulacion_id_s1'),
                    _id_tipo_movimiento,
                    _periocidad,
                    _codigo_operacion,
                    'ACTIVO',
        			timezone('utc', now()),
        			timezone('utc', now())
        		);
        EXCEPTION
            WHEN OTHERS THEN
                _NumError := SQLSTATE;
                _MsjError := '[mc_cdt_crea_regla_acumulacion] Error al crear Regla acumulacion. CAUSA ('|| SQLERRM ||')';
            RETURN;
    	END;
$BODY$
LANGUAGE 'plpgsql';

-- //@UNDO
-- SQL to undo the change goes here.

 DROP FUNCTION IF EXISTS ${schema}.mc_cdt_crea_regla_acumulacion
