
-------
-- BOLSAS
-------
INSERT INTO ${schema.cdt}.cdt_bolsa
(nombre, descripcion, estado, fecha_estado, fecha_creacion)
VALUES('Cargas', 'Saco de cargas', 'ACTIVO', LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_bolsa
(nombre, descripcion, estado, fecha_estado, fecha_creacion)
VALUES('Retiros', 'Saco de retiros', 'ACTIVO', LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_bolsa
(nombre, descripcion, estado, fecha_estado, fecha_creacion)
VALUES('Total', 'Saldo', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

-------
--  FASES DE LOS MOVIMIENTOS
-------

-- PRIMERA CARGA
INSERT INTO ${schema.cdt}.cdt_fase_movimiento -- ID 1
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Solicitud Primera Carga', 'Solicitud Primera Carga', 'N', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 2
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Confirmación Primera Carga', 'Confirmación Primera Carga', 'S', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 3
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Solicitud Reversa Primera Carga', 'Solicitud Reversa Primera Carga', 'N', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 4
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Confirmación Reversa Primera Carga', 'Confirmación Reversa Primera Carga', 'S', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

-- SOLICITUD CARGA
INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 5
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Solicitud Carga Web', 'Solicitud Carga Web', 'N', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 6
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Confirmación Carga Web', 'Confirmación Carga Web', 'S', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 7
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Solicitud Carga POS', 'Solicitud Carga POS', 'N', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 8
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Confirmación Carga POS', 'Confirmación Carga POS', 'S', 'ACTIVO', LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 9
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Solicitud Reversa Carga', 'Solicitud Reversa Carga WEB y POS', 'N', 'ACTIVO', LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 10
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Confirmación Reversa Carga', 'Confirmación Reversa Carga WEB y POS', 'S', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

-- RETIRO
INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 11
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Solicitud Retiro Web', 'Solicitud Retiro Web', 'N', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 12
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Confirmación Retiro Web', 'Confirmación Retiro Web', 'S', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 13
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Solicitud Retiro POS', 'Solicitud Retiro POS', 'N', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 14
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Confirmación Retiro POS', 'Confirmación Retiro POS', 'S', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 15
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Solicitud Reversa de Retiro', 'Solicitud Reversa de Retiro', 'S', 'ACTIVO', LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_fase_movimiento-- ID 16
(nombre, descripcion, ind_confirmacion, estado, fecha_estado, fecha_creacion)
VALUES('Confirmacion Reversa de Retiro', 'Confirmacion Reversa de Retiro', 'S', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

-------
-- REGLAS DE ACUMULACION
-------
INSERT INTO ${schema.cdt}.cdt_regla_acumulacion
(id_bolsa, descripcion, periocidad, codigo_operacion, estado, fecha_estado, fecha_creacion)
VALUES(1, 'Acumulador mensual primera carga', 'MEN', 'SUM', 'ACTIVO', LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_regla_acumulacion
(id_bolsa, descripcion, periocidad, codigo_operacion, estado, fecha_estado, fecha_creacion)
VALUES(1, 'Contador trasacciones primera carga', 'MEN', 'COUNT', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_regla_acumulacion
(id_bolsa, descripcion, periocidad, codigo_operacion, estado, fecha_estado, fecha_creacion)
VALUES(1, 'Acumulador Mensual Cargas', 'MEN', 'SUM', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_regla_acumulacion
(id_bolsa, descripcion, periocidad, codigo_operacion, estado, fecha_estado, fecha_creacion)
VALUES(1, 'Contador transacciones Cargas', 'MEN', 'COUNT', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_regla_acumulacion
(id_bolsa, descripcion, periocidad, codigo_operacion, estado, fecha_estado, fecha_creacion)
VALUES(2, 'Acumulador mensual Retiros', 'MEN', 'SUM', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_regla_acumulacion
(id_bolsa, descripcion, periocidad, codigo_operacion, estado, fecha_estado, fecha_creacion)
VALUES(2, 'Contador de transacciones mensuales de Retiro', 'MEN', 'COUNT', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_regla_acumulacion
(id_bolsa, descripcion, periocidad, codigo_operacion, estado, fecha_estado, fecha_creacion)
VALUES(1, 'Contador de reversas de carga', 'MEN', 'COUNT', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_regla_acumulacion
(id_bolsa, descripcion, periocidad, codigo_operacion, estado, fecha_estado, fecha_creacion)
VALUES(2, 'Contador de reversas de retiro', 'MEN', 'COUNT', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_regla_acumulacion
(id_bolsa, descripcion, periocidad, codigo_operacion, estado, fecha_estado, fecha_creacion)
VALUES(3, 'Saldo cuenta en CDT', 'VIDA', 'SUM', 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);


-------
-- LIMITES
-------

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(1, -1, 'La carga supera el monto máximo de primera carga', 50000, 'MENORQIG', 108206, 'ACTIVO', LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(1, -1, 'La carga es menor al mínimo de carga', 3000, 'MAYORQIG', 108203, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(1, 1, 'La carga supera el monto máximo de primera carga', 50000, 'MENORQIG', 108205, 'ACTIVO', LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(1, 2, 'Contador Primera carga debe ser = a 1', 1, 'IGUAL', 108001, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(5, -1, 'La carga supera el monto máximo de carga web', 500000, 'MENORQIG', 108201, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(5, -1, 'La carga es menor al mínimo de carga', 3000, 'MAYORQIG', 108203, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(5, 1, 'La carga supera el monto máximo de cargas mensuales', 1000000, 'MENORQIG', 108204, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(7, -1, 'La carga supera el monto máximo de carga pos', 100000, 'MENORQIG', 108202, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(7, -1, 'La carga es menor al mínimo de carga', 3000, 'MAYORQIG', 108203, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(7, 1, 'La carga supera el monto máximo de cargas mensuales', 1000000, 'MENORQIG', 108204, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(11, -1, 'El retiro supera el monto máximo de un retiro web', 500000, 'MENORQIG', 108301, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(11, -1, 'El monto de retiro es menor al monto mínimo de retiros', 1000, 'MAYORQIG', 108303, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(11, 4, 'El retiro supera el monto máximo de retiros mensuales', 1000000, 'MENORQIG', 108304, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(13, -1, 'El retiro supera el monto máximo de un retiro pos', 100000, 'MENORQIG', 108302, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(13, -1, 'El monto de retiro es menor al monto mínimo de retiros', 1000, 'MAYORQIG', 108303, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);

INSERT INTO ${schema.cdt}.cdt_limite
(id_fase_movimiento, id_regla_acumulacion, descripcion, valor, cod_operacion, cod_error, estado, fecha_estado, fecha_creacion)
VALUES(13, 4, 'El retiro supera el monto máximo de retiros mensuales', 1000000, 'MENORQIG', 108304, 'ACTIVO',  LOCALTIMESTAMP,LOCALTIMESTAMP);



INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(1, 1, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(1, 2, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(1, 9, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(2, 9, -1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(3, 1, -1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(3, 2, -1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(3, 9, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(4, 9, -1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(5, 3, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(5, 4, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(5, 9, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(6, 9, -1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(7, 3, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(7, 4, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(7, 9, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(8, 9, -1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(9, 3, -1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(9, 4, -1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(9, 9, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(10, 9, -1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(11, 5, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(11, 6, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(11, 9, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(12, 9, -1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(13, 5, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(13, 6, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(13, 9, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(14, 9, -1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(15, 5, -1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(15, 6, -1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(15, 9, 1);

INSERT INTO ${schema.cdt}.cdt_fase_acumulador
(id_fase_movimiento, id_regla_acumulacion, signo)
VALUES(16, 9, -1);
