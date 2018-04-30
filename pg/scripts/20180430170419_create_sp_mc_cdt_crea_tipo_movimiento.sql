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


/* ************************************************** */
/* NOMBRE             : mc_cdt_crea_tipo_movimiento   */
/* DESCRIPCIÓN        : 							  */
/* Fecha Creación     : 30 - ABRIL - 2018             */
/* Fecha Actualización:                               */
/* Versión            : V1.0.0                        */
/* Responsable        :                               */
/* Actualizado por    : JOG                           */
/* ************************************************** */

CREATE OR REPLACE FUNCTION ${schema}.mc_cdt_crea_tipo_movimiento
(
    IN _id_bolsa        BIGSERIAL,
    IN _nombre          VARCHAR,
    IN _descripcion     VARCHAR,
    OUT _NumError       VARCHAR,
    OUT _MsjError       VARCHAR
) RETURNS record AS
$BODY$
    	DECLARE

    	BEGIN
	        _NumError := '0';
	        _MsjError := '';

		    IF COALESCE(_id_bolsa, 0)) = 0 THEN
	            _NumError := '1001';
	        	_MsjError := '[mc_cdt_crea_tipo_movimiento] El Id Bolsa no puede ser 0';
	        	RETURN;
	        END IF;

            IF TRIM(COALESCE(_nombre, '')) = '' THEN
                _NumError := '1002';
                _MsjError := '[mc_cdt_crea_tipo_movimiento] El Nombre del tipo de movimiento no puede ser vacio';
                RETURN;
            END IF;


        	INSERT INTO ${schema}.cdt_tipo_movimiento
	    		(
	    			id,
	    			id_bolsa,
                    nombre,
	    			descripcion,
                    estado,
	    			fecha_estado,
	    			fecha_creacion
	    		)
        	VALUES
        		(
        			nextval(${schema}'.cdt_tipo_movimiento_id_s1'),
                    _id_bolsa,
                    _nombre,
                    COALESCE(_descripcion,''),
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


 DROP FUNCTION IF EXISTS ${schema}.mc_cdt_crea_movimiento

