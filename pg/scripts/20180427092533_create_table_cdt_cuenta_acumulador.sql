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

-- // create_table_cdt_cuenta_acumulador
-- Migration SQL that makes the change goes here.


  CREATE SEQUENCE ${schema}.cdt_cuenta_acumulador_id_s1
    INCREMENT 1
    MINVALUE 1
    START 1;

  COMMENT ON SEQUENCE ${schema}.cdt_cuenta_acumulador_id_s1 IS 'ID del acumulador cuenta';

  CREATE TABLE ${schema}.cdt_cuenta_acumulador (
      id                    BIGSERIAL NOT NULL,
      id_regla_acumulacion  BIGSERIAL NOT NULL,
      id_cuenta             BIGSERIAL NOT NULL,
      monto                 DECIMAL NOT NULL,
      fecha_inicio          TIMESTAMP NOT NULL,
      fecha_fin             TIMESTAMP NOT NULL,
      fecha_creacion        TIMESTAMP NOT NULL,
      fecha_actualizacion   TIMESTAMP NOT NULL,
      CONSTRAINT cdt_cuenta_acumulador_pk PRIMARY KEY(id)
  );
--  CREATE INDEX cdt_cuenta_acumulador_i1 ON {schema}.cdt_cuenta_acumulador (estado);

-- //@UNDO
-- SQL to undo the change goes here.
  DROP TABLE IF EXISTS  ${schema}.cdt_cuenta_acumulador;
