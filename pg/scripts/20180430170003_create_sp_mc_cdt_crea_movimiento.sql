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

CREATE OR REPLACE FUNCTION ${schema}.mc_cdt_crea_limite
(
    IN _nombre          VARCHAR,
    IN _descripcion     VARCHAR,
    IN _signo           NUMERIC,
    OUT _NumError       VARCHAR,
    OUT _MsjError       VARCHAR
) RETURNS record AS
$BODY$
    	DECLARE

    	BEGIN
	        _NumError := '0';
	        _MsjError := '';

		    IF TRIM(COALESCE(_nombre, '')) = '' THEN
	            _NumError := '1001';
	        	_MsjError := '[mc_cdt_crea_movimiento] El nombre del movimiento no puede ser vacio';
	        	RETURN;
	        END IF;

            IF COALESCE(_signo, 0) != -1 OR  COALESCE(_signo, 0) != 1 THEN
                _NumError := '1002';
                _MsjError := '[mc_cdt_crea_movimiento] El Signo del movimiento debe ser 1 o -1';
                RETURN;
            END IF;


        	INSERT INTO ${schema}.cdt_movimiento
	    		(
	    			id,
	    			nombre,
	    			descripcion,
                    signo,
                    estado,
	    			fecha_estado,
	    			fecha_creacion
	    		)
        	VALUES
        		(
        			nextval(${schema}'.cdt_movimiento_id_s1'),
                    _nombre,
                    _descripcion,
                    _signo,
        			'ACTIVO',
        			LOCALTIMESTAMP,
        			LOCALTIMESTAMP
        		);
        EXCEPTION
            WHEN OTHERS THEN
                _NumError := SQLSTATE;
                _MsjError := '[mc_cdt_crea_movimiento] Error al crear Movimiento. CAUSA ('|| SQLERRM ||')';
            RETURN;
    	END;
$BODY$
LANGUAGE 'plpgsql';

-- //@UNDO
-- SQL to undo the change goes here.

 DROP FUNCTION IF EXISTS ${schema}.mc_cdt_crea_limite
