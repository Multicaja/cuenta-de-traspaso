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

-- // create_table_cdt_movimiento_cuenta
-- Migration SQL that makes the change goes here.


 CREATE SEQUENCE ${schema}.cdt_movimiento_cuenta_id_s1
    INCREMENT 1
    MINVALUE 1
    START 1;

 COMMENT ON SEQUENCE ${schema}.cdt_movimiento_cuenta_id_s1 IS 'ID del movimiento cuenta';

  CREATE TABLE ${schema}.cdt_movimiento_cuenta(
      id                 BIGSERIAL NOT NULL,
      id_cuenta          BIGSERIAL NOT NULL,
      id_movimiento      BIGSERIAL NOT NULL,
      id_mov_referencia  BIGSERIAL NOT NULL,
      id_tx_externo      VARCHAR(50) NOT NULL,
      glosa              VARCHAR(100) NOT NULL,
      monto              NUMERIC NOT NULL,
      fecha_registro     TIMESTAMP NOT NULL,
      estado             VARCHAR(10) NOT NULL,
      fecha_estado       TIMESTAMP NOT NULL,
      CONSTRAINT cdt_movimiento_cuenta_pk PRIMARY KEY(id)
  );

  CREATE INDEX cdt_movimiento_cuenta_i1 ON ${schema}.cdt_movimiento_cuenta (id_movimiento);
-- //@UNDO
-- SQL to undo the change goes here.
  DROP TABLE IF EXISTS ${schema}.cdt_movimiento_cuenta;
  DROP SEQUENCE IF EXISTS ${schema}.cdt_movimiento_cuenta_id_s1;
