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

-- // create_sp_mc_cdt_crea_bolsa
-- Migration SQL that makes the change goes here.

  CREATE OR REPLACE FUNCTION ${schema}.mc_cdt_crea_bolsa
  (
      IN _nombre             VARCHAR,
      IN _descripcion		     VARCHAR,
      OUT _NumError          VARCHAR,
      OUT _MsjError          VARCHAR
  ) RETURNS record AS
  $BODY$
        DECLARE

        BEGIN
            _NumError := '0';
            _MsjError := '';

          IF TRIM(COALESCE(_nombre, '')) = '' THEN
                _NumError := '1000';
              _MsjError := '[mc_cdt_crea_bolsa] El nombre de la Bolsa no puede ser vacio';
              RETURN;
            END IF;

            INSERT INTO ${schema}.cdt_bolsa
            (
              id,
              nombre,
              descripcion,
              estado,
              fecha_estado,
              fecha_creacion
            )
            VALUES
              (
                 nextval('${schema}.cdt_bolsa_id_s1'),
                 _nombre,
                 COALESCE(_descripcion,''),
                 'ACTIVO',
                 LOCALTIMESTAMP,
                 LOCALTIMESTAMP
              );
          EXCEPTION
              WHEN OTHERS THEN
                  _NumError := SQLSTATE;
                  _MsjError := '[mc_cdt_crea_bolsa] Error al Insertar Bolsa. CAUSA ('|| SQLERRM ||')';
              RETURN;
        END;
  $BODY$
  LANGUAGE 'plpgsql';

-- //@UNDO
-- SQL to undo the change goes here.
  DROP FUNCTION IF EXISTS ${schema}.mc_cdt_crea_bolsa
