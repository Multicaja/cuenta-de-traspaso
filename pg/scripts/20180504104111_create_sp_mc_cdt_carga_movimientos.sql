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

-- // create_sp_mc_cdt_carga_movimientos
-- Migration SQL that makes the change goes here.

CREATE OR REPLACE FUNCTION ${schema}.mc_cdt_carga_movimientos
(
    IN  _NOMBRE         VARCHAR,
    OUT _movimientos    REFCURSOR,
    OUT _NumError       VARCHAR,
    OUT _MsjError       VARCHAR
) RETURNS record AS
$BODY$
DECLARE

BEGIN
    _NumError = '0';
    _MsjError = '';
    OPEN _movimientos FOR
        SELECT
            id,
            nombre,
            descripcion,
            signo
        FROM
             ${schema}.cdt_movimiento
        WHERE
            estado = 'ACTIVO' AND
            ( COALESCE(_NOMBRE,'') = '' OR lOWER(nombre) LIKE '%'||LOWER(_NOMBRE)||'%');

EXCEPTION
    WHEN OTHERS THEN
        _NumError := SQLSTATE;
        _MsjError := '[mc_cdt_carga_movimientos] Error al buscar movimientos CAUSA ('|| SQLERRM ||')';
    RETURN;
END;
$BODY$
LANGUAGE 'plpgsql';

-- //@UNDO
-- SQL to undo the change goes here.

DROP FUNCTION IF EXISTS ${schema}.mc_cdt_carga_movimientos;