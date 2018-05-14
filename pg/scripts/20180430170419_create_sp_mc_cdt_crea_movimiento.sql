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

-- // create_sp_mc_cdt_crea_tipo_movimiento
-- Migration SQL that makes the change goes here.


CREATE OR REPLACE FUNCTION ${schema}.mc_cdt_crea_categoria_movimiento
(
    IN _id_bolsa        NUMERIC,
    IN _nombre          VARCHAR,
    IN _descripcion     VARCHAR,
    OUT _num_error       VARCHAR,
    OUT _msj_error       VARCHAR
)AS $$

    	DECLARE

    	BEGIN
	        _num_error := '0';
	        _msj_error := '';

		    IF COALESCE(_id_bolsa, 0) = 0 THEN
	            _num_error := '1001';
	        	_msj_error := '[mc_cdt_crea_categoria_movimiento] El Id Bolsa no puede ser 0';
	        	RETURN;
	        END IF;

            IF TRIM(COALESCE(_nombre, '')) = '' THEN
                _num_error := '1002';
                _msj_error := '[mc_cdt_crea_categoria_movimiento] El Nombre del tipo de movimiento no puede ser vacio';
                RETURN;
            END IF;


        	INSERT INTO ${schema}.cdt_categoria_movimiento
	    		(
	    			id_bolsa,
            nombre,
	    			descripcion,
            estado,
	    			fecha_estado,
	    			fecha_creacion
	    		)
        	VALUES
        		(
              _id_bolsa,
              _nombre,
              COALESCE(_descripcion,''),
              'ACTIVO',
        			timezone('utc', now()),
        			timezone('utc', now())
        		);
        EXCEPTION
            WHEN OTHERS THEN
                _num_error := SQLSTATE;
                _msj_error := '[mc_cdt_crea_categoria_movimiento] Error al crear Categoria Movimiento. CAUSA ('|| SQLERRM ||')';
            RETURN;
    	END;
$$
LANGUAGE 'plpgsql';


-- //@UNDO
-- SQL to undo the change goes here.


 DROP FUNCTION IF EXISTS ${schema}.mc_cdt_crea_movimiento;
