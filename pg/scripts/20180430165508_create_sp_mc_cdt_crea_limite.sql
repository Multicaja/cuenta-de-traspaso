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

-- // create_sp_mc_cdt_crea_limite
-- Migration SQL that makes the change goes here.

CREATE OR REPLACE FUNCTION ${schema}.mc_cdt_crea_limite
(
    IN _id_movimiento           BIGSERIAL,
    IN _id_regla_acumulacion    BIGSERIAL,
    IN _descripcion             VARCHAR,
    IN _valor    		        VARCHAR,
    IN _cod_operacion           VARCHAR,
    OUT _NumError               VARCHAR,
    OUT _MsjError               VARCHAR
) RETURNS record AS
$BODY$
    	DECLARE

    	BEGIN
	        _NumError := '0';
	        _MsjError := '';

		    IF COALESCE(_id_movimiento, 0) = 0 THEN
	            _NumError := '1001';
	        	_MsjError := '[mc_cdt_crea_limite] El Id Movimiento puede ser 0';
	        	RETURN;
	        END IF;

        IF COALESCE(descripcion, '') = 0 THEN
            _NumError := '1002';
            _MsjError := '[mc_cdt_crea_limite] La descripcion no puede estar vacia';
            RETURN;
        END IF;

        IF _valor IS NULL THEN
            _NumError := '1003';
            _MsjError := '[mc_cdt_crea_limite] El Valor de un limite no puede ser Nulo';
            RETURN;
        END IF;

          IF COALESCE(_cod_operacion, '') = '' THEN
            _NumError := '1004';
            _MsjError := '[mc_cdt_crea_limite] El Codigo de operacion no puede estar vacio';
            RETURN;
        END IF;

        INSERT INTO ${schema}.cdt_limite
        (
          id,
          id_movimiento,
          id_regla_acumulacion,
          descripcion,
                  valor,
                  cod_operacion,
                  estado,
          fecha_estado,
          fecha_creacion
        )
        VALUES
          (
            nextval(${schema}'.cdt_limite_id_s1'),
            id_movimiento,
                  id_regla_acumulacion,
                  descripcion,
                  valor,
                  cod_operacion,
            'ACTIVO',
            LOCALTIMESTAMP,
            LOCALTIMESTAMP
          );
      EXCEPTION
          WHEN OTHERS THEN
              _NumError := SQLSTATE;
              _MsjError := '[mc_cdt_crea_limite] Error al crear Limite. CAUSA ('|| SQLERRM ||')';
          RETURN;
    END;
$BODY$
LANGUAGE 'plpgsql';


-- //@UNDO
-- SQL to undo the change goes here.

  DROP FUNCTION IF EXISTS ${schema}.mc_cdt_crea_limite
