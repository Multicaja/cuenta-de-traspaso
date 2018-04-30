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

-- // create_table_cdt_limite
-- Migration SQL that makes the change goes here.

  CREATE SEQUENCE ${schema}.cdt_limite_id_s1
    INCREMENT 1
    MINVALUE 1
    START 1;

  COMMENT ON SEQUENCE ${schema}.cdt_limite_id_s1 IS 'ID del limite';


  CREATE TABLE ${schema}.cdt_limite(
      id                    BIGSERIAL NOT NULL,
      id_movimiento         BIGSERIAL NOT NULL,
      id_regla_acumulacion  BIGSERIAL,
      descripcion           VARCHAR(100) NOT NULL,
      valor                 DECIMAL NOT NULL,
      cod_operacion         VARCHAR(10) NOT NULL,
      estado                VARCHAR(10) NOT NULL,
      fecha_estado          TIMESTAMP NOT NULL,
      fecha_creacion        TIMESTAMP NOT NULL,
      CONSTRAINT cdt_limite_pk PRIMARY KEY(id)
  );

  CREATE INDEX cdt_limite_i1 ON ${schema}.cdt_limite (estado);
-- //@UNDO
-- SQL to undo the change goes here.
  DROP TABLE IF EXISTS  ${schema}.cdt_limite;
