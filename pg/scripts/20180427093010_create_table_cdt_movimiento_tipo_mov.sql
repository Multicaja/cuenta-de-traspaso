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

-- // create_table_cdt_movimiento_tipo_mov
-- Migration SQL that makes the change goes here.
CREATE SCHEMA IF NOT EXISTS ${schema};

  CREATE TABLE ${schema}.cdt_movimiento_tipo_mov(
      id_movimiento         BIGSERIAL NOT NULL,
      id_tipo_movimiento    BIGSERIAL NOT NULL,
      CONSTRAINT cdt_movimiento_tipo_mov_pk PRIMARY KEY(id_movimiento,id_tipo_movimiento)
  );

-- //@UNDO
-- SQL to undo the change goes here.
  DROP TABLE IF EXISTS ${schema}.cdt_movimiento_tipo_mov;

  DROP SCHEMA IF EXISTS ${schema};
