-- INSERT PARA BOLSA

INSERT INTO ${schema.cdt}.cdt_bolsa
(
    nombre,
    descripcion,
    estado,
    fecha_estado,
    fecha_creacion
)
VALUES
(
 	'Cargas',
	'Saco de cargas',
	'ACTIVO',
	LOCALTIMESTAMP,
	LOCALTIMESTAMP
);



INSERT INTO ${schema.cdt}.cdt_bolsa
(
    nombre,
    descripcion,
    estado,
    fecha_estado,
    fecha_creacion
)
VALUES
(
    'Retiros',
    'Saco de retiros',
    'ACTIVO',
    LOCALTIMESTAMP,
    LOCALTIMESTAMP
);



INSERT INTO ${schema.cdt}.cdt_bolsa
(
    nombre,
    descripcion,
    estado,
    fecha_estado,
    fecha_creacion
)
VALUES
(
    'Total',
    'Saldo',
    'ACTIVO',
    LOCALTIMESTAMP,
    LOCALTIMESTAMP
);

-- INSERT PARA CUENTA CLIENTE

INSERT INTO
	${schema.cdt}.cdt_cuenta
	(
    	id_externo,
    	descripcion,
    	estado,
    	fecha_estado,
    	fecha_creacion
    )
    VALUES
    (
    	'PREPAGO_166168813',
    	'Cliente de Prepago Multicaja',
    	'ACTIVO',
    	LOCALTIMESTAMP,
    	LOCALTIMESTAMP
    );

--INSERT TIPO MOVIMIENTO
INSERT INTO -- ID 1
	${schema.cdt}.cdt_fase_movimiento
	(
        nombre,
        descripcion,
        signo,
        ind_confirmacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
    	'Solicitud Primera Carga',
    	'Solicitud Primera Carga',
    	1,
    	'N',
    	'ACTIVO',
    	LOCALTIMESTAMP,
    	LOCALTIMESTAMP
    );

INSERT INTO -- ID 2
    ${schema.cdt}.cdt_fase_movimiento
    (
        nombre,
        descripcion,
        signo,
        ind_confirmacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        'Confirmación Primera Carga',
        'Confirmación Primera Carga',
        -1,
        'S',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO -- ID 3
    ${schema.cdt}.cdt_fase_movimiento
    (
        nombre,
        descripcion,
        signo,
        ind_confirmacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        'Solicitud Carga Web',
        'Solicitud Carga Web',
        1,
        'N',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO -- ID 4
    ${schema.cdt}.cdt_fase_movimiento
    (
        nombre,
        descripcion,
        signo,
        ind_confirmacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        'Confirmación Carga Web',
        'Confirmación Carga Web',
        -1,
        'S',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO -- ID 5
    ${schema.cdt}.cdt_fase_movimiento
    (
        nombre,
        descripcion,
        signo,
        ind_confirmacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        'Solicitud Carga POS',
        'Solicitud Carga POS',
        1,
        'N',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO -- ID 6
    ${schema.cdt}.cdt_fase_movimiento
    (
        nombre,
        descripcion,
        signo,
        ind_confirmacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        'Confirmación Carga POS',
        'Confirmación Carga POS',
        -1,
        'S',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO -- ID 7
    ${schema.cdt}.cdt_fase_movimiento
    (
        nombre,
        descripcion,
        signo,
        ind_confirmacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        'Reversa de Carga',
        'Reversa de Carga',
        -1,
        'S',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO -- ID 8
    ${schema.cdt}.cdt_fase_movimiento
    (
        nombre,
        descripcion,
        signo,
        ind_confirmacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        'Solicitud Retiro Web',
        'Solicitud Retiro Web',
        1,
        'N',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO -- ID 9
    ${schema.cdt}.cdt_fase_movimiento
    (
        nombre,
        descripcion,
        signo,
        ind_confirmacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        'Confirmación Retiro Web',
        'Confirmación Retiro Web',
        -1,
        'S',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO -- ID 10
    ${schema.cdt}.cdt_fase_movimiento
    (
        nombre,
        descripcion,
        signo,
        ind_confirmacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        'Solicitud Retiro POS',
        'Solicitud Retiro POS',
        1,
        'N',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO -- ID 11
    ${schema.cdt}.cdt_fase_movimiento
    (
        nombre,
        descripcion,
        signo,
        ind_confirmacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        'Confirmación Retiro POS',
        'Confirmación Retiro POS',
        -1,
        'S',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO -- ID 12
    ${schema.cdt}.cdt_fase_movimiento
    (
        nombre,
        descripcion,
        signo,
        ind_confirmacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        'Reversa de Retiro',
        'Reversa de Retiro',
        -1,
        'S',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


-- INSERT TIPO MOVIMIENTO
INSERT INTO
	${schema.cdt}.cdt_categoria_movimiento
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
    	1,
    	'Solicitud de Carga',
    	'Solicitud de Carga',
    	'ACTIVO',
    	LOCALTIMESTAMP,
    	LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_movimiento
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
        1,
        'Confirmación de Carga',
        'Confirmación de Carga',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_movimiento
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
        1,
        'Reversa de Carga',
        'Reversa de Carga',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_movimiento
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
        2,
        'Solicitud de Retiro',
        'Solicitud de Retiro',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_movimiento
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
        2,
        'Confirmación de Retiro',
        'Confirmación de Retiro',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema.cdt}.cdt_categoria_movimiento
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
        2,
        'Reversa de Retiro',
        'Reversa de Retiro',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema.cdt}.cdt_categoria_movimiento
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
        3,
        'Transacción',
        'Transacción',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        1,
        1
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        1,
        7
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        2,
        2
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        2,
        7
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        3,
        1
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        3,
        7
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        4,
        2
    );
INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        4,
        7
    );
INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        5,
        1
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        5,
        7
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        6,
        2
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        6,
        7
    );
INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        7,
        3
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        8,
        4
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        8,
        7
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        9,
        5
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        9,
        7
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        10,
        4
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
   (
       10,
        7
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        11,
        5
    );

INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        11,
        7
    );
INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        12,
        6
    );

-- PARA PRUEBAS DE REVERSA
INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        7,
        1
    );
INSERT INTO
    ${schema.cdt}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
    )
VALUES
    (
        7,
        7
    );
--REGLAS DE ACUMULACION

INSERT INTO
	${schema.cdt}.cdt_regla_acumulacion
	(
        id_categoria_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
    	1,
    	'MEN',
    	'SUM',
    	'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema.cdt}.cdt_regla_acumulacion
    (
        id_categoria_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        1,
        'MEN',
        'COUNT',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );



INSERT INTO
    ${schema.cdt}.cdt_regla_acumulacion
    (
        id_categoria_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        3,
        'MEN',
        'SUM',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_regla_acumulacion
    (
        id_categoria_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        3,
        'SEM',
        'SUM',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_regla_acumulacion
    (
        id_categoria_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        4,
        'MEN',
        'SUM',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema.cdt}.cdt_regla_acumulacion
    (
        id_categoria_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        4,
        'MEN',
        'COUNT',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema.cdt}.cdt_regla_acumulacion
    (
        id_categoria_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        6,
        'MEN',
        'SUM',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema.cdt}.cdt_regla_acumulacion
    (
        id_categoria_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        7,
        'VIDA',
        'SUM',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );



-- LIMITES
INSERT INTO
	${schema.cdt}.cdt_limite
	(
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
    	1,
    	-1,
    	'La carga supera el monto máximo de primera carga',
    	50000,
      'MENORQIG',
      108206,
      'ACTIVO',
      LOCALTIMESTAMP,
      LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        1,
        -1,
        'La carga es menor al mínimo de carga',
        3000,
        'MAYORQIG',
        108203,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        1,
        1,
        'La carga supera el monto máximo de primera carga',
        50000,
        'MENORQIG',
        108205,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        1,
        2,
        'Contador Primera carga debe ser = a 1',
        1,
        'IGUAL',
        108001,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        3,
        -1,
        'La carga supera el monto máximo de carga web',
        500000,
        'MENORQIG',
        108201,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        3,
        -1,
        'La carga es menor al mínimo de carga',
        3000,
        'MAYORQIG',
        108203,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        3,
        1,
        'La carga supera el monto máximo de cargas mensuales',
        1000000,
        'MENORQIG',
        108204,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        5,
        -1,
        'La carga supera el monto máximo de carga pos',
        100000,
        'MENORQIG',
        108202,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        5,
        -1,
        'La carga es menor al mínimo de carga',
        3000,
        'MAYORQIG',
        108203,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        5,
        1,
        'La carga supera el monto máximo de cargas mensuales',
        1000000,
        'MENORQIG',
        108204,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        8,
        -1,
        'El retiro supera el monto máximo de un retiro web',
        500000,
        'MENORQIG',
        108301,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        8,
        -1,
        'El monto de retiro es menor al monto mínimo de retiros',
        1000,
        'MAYORQIG',
        108303,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        8,
        4,
        'El retiro supera el monto máximo de retiros mensuales',
        1000000,
        'MENORQIG',
        108304,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        10,
        -1,
        'El retiro supera el monto máximo de un retiro pos',
        100000,
        'MENORQIG',
        108302,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        10,
        -1,
        'El monto de retiro es menor al monto mínimo de retiros',
        1000,
        'MAYORQIG',
        108303,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema.cdt}.cdt_limite
    (
        id_fase_movimiento,
        id_regla_acumulacion,
        descripcion,
        valor,
        cod_operacion,
        cod_error,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        10,
        4,
        'El retiro supera el monto máximo de retiros mensuales',
        1000000,
        'MENORQIG',
        108304,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );
