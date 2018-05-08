-- INSERT PARA BOLSA

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
	'Cargas',
	'Saco de cargas',
	'ACTIVO',
	LOCALTIMESTAMP,
	LOCALTIMESTAMP
);



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
    'Retiros',
    'Saco de retiros',
    'ACTIVO',
    LOCALTIMESTAMP,
    LOCALTIMESTAMP
);



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
    'Total',
    'Saldo',
    'ACTIVO',
    LOCALTIMESTAMP,
    LOCALTIMESTAMP
);

-- INSERT PARA CUENTA CLIENTE

INSERT INTO
	${schema}.cdt_cuenta
	(
    	id,
    	id_externo,
    	descripcion,
    	estado,
    	fecha_estado,
    	fecha_creacion
    )
    VALUES
    (
    	nextval('${schema}.cdt_cuenta_id_s1'),
    	'PREPAGO_166168813',
    	'Cliente de Prepago Multicaja',
    	'ACTIVO',
    	LOCALTIMESTAMP,
    	LOCALTIMESTAMP
    );

--INSERT TIPO MOVIMIENTO
INSERT INTO
	${schema}.cdt_movimiento
	(
        id,
        nombre,
        descripcion,
        signo,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_movimiento_id_s1'),
    	'Solicitud Primera Carga',
    	'Solicitud Primera Carga',
    	1,
    	'ACTIVO',
    	LOCALTIMESTAMP,
    	LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_movimiento
    (
        id,
        nombre,
        descripcion,
        signo,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_movimiento_id_s1'),
        'Confirmación Primera Carga',
        'Confirmación Primera Carga',
        -1,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_movimiento
    (
        id,
        nombre,
        descripcion,
        signo,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_movimiento_id_s1'),
        'Solicitud Carga Web',
        'Solicitud Carga Web',
        1,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_movimiento
    (
        id,
        nombre,
        descripcion,
        signo,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_movimiento_id_s1'),
        'Confirmación Carga Web',
        'Confirmación Carga Web',
        1,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_movimiento
    (
        id,
        nombre,
        descripcion,
        signo,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_movimiento_id_s1'),
        'Solicitud Carga POS',
        'Solicitud Carga POS',
        1,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_movimiento
    (
        id,
        nombre,
        descripcion,
        signo,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_movimiento_id_s1'),
        'Confirmación Carga POS',
        'Confirmación Carga POS',
        1,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_movimiento
    (
        id,
        nombre,
        descripcion,
        signo,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_movimiento_id_s1'),
        'Reversa de Carga',
        'Reversa de Carga',
        1,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_movimiento
    (
        id,
        nombre,
        descripcion,
        signo,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_movimiento_id_s1'),
        'Solicitud Retiro Web',
        'Solicitud Retiro Web',
        1,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_movimiento
    (
        id,
        nombre,
        descripcion,
        signo,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_movimiento_id_s1'),
        'Solicitud Retiro POS',
        'Solicitud Retiro POS',
        1,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_movimiento
    (
        id,
        nombre,
        descripcion,
        signo,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_movimiento_id_s1'),
        'Confirmación Retiro POS',
        'Confirmación Retiro POS',
        1,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_movimiento
    (
        id,
        nombre,
        descripcion,
        signo,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_movimiento_id_s1'),
        'Solicitud Reversa de Retiro',
        'Solicitud Reversa de Retiro',
        1,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_movimiento
    (
        id,
        nombre,
        descripcion,
        signo,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_movimiento_id_s1'),
        'Confirmación Reversa de Retiro',
        'Confirmación Reversa de Retiro',
        1,
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


-- INSERT TIPO MOVIMIENTO
INSERT INTO
	${schema}.cdt_tipo_movimiento
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
    	nextval('${schema}.cdt_tipo_movimiento_id_s1'),
    	1,
    	'Solicitud de Carga',
    	'Solicitud de Carga',
    	'ACTIVO',
    	LOCALTIMESTAMP,
    	LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_tipo_movimiento
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
        nextval('${schema}.cdt_tipo_movimiento_id_s1'),
        1,
        'Confirmación de Carga',
        'Confirmación de Carga',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_tipo_movimiento
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
        nextval('${schema}.cdt_tipo_movimiento_id_s1'),
        1,
        'Reversa de Carga',
        'Reversa de Carga',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_tipo_movimiento
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
        nextval('${schema}.cdt_tipo_movimiento_id_s1'),
        2,
        'Solicitud de Retiro',
        'Solicitud de Retiro',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_tipo_movimiento
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
        nextval('${schema}.cdt_tipo_movimiento_id_s1'),
        2,
        'Confirmación de Retiro',
        'Confirmación de Retiro',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_tipo_movimiento
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
        nextval('${schema}.cdt_tipo_movimiento_id_s1'),
        2,
        'Reversa de Retiro',
        'Reversa de Retiro',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_tipo_movimiento
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
        nextval('${schema}.cdt_tipo_movimiento_id_s1'),
        3,
        'Transacción',
        'Transacción',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        1,
        1
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        1,
        7
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        2,
        2
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        3,
        1
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        3,
        7
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        4,
        2
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        5,
        1
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        5,
        7
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        6,
        2
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        7,
        3
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        8,
        4
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        8,
        7
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        9,
        5
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        10,
        4
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        10,
        7
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        11,
        5
    );

INSERT INTO
    ${schema}.cdt_movimiento_tipo_mov
    (
        id_movimiento,
        id_tipo_movimiento
    )
VALUES
    (
        12,
        6
    );

--REGLAS DE ACUMULACION

INSERT INTO
	${schema}.cdt_regla_acumulacion
	(
        id,
        id_tipo_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
    	nextval('${schema}.cdt_regla_acumulacion_id_s1'),
    	1,
    	'MEN',
    	'SUM',
    	'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_regla_acumulacion
    (
        id,
        id_tipo_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_regla_acumulacion_id_s1'),
        1,
        'MEN',
        'COUNT',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );



INSERT INTO
    ${schema}.cdt_regla_acumulacion
    (
        id,
        id_tipo_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_regla_acumulacion_id_s1'),
        3,
        'MEN',
        'SUM',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );



INSERT INTO
    ${schema}.cdt_regla_acumulacion
    (
        id,
        id_tipo_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_regla_acumulacion_id_s1'),
        4,
        'MEN',
        'SUM',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_regla_acumulacion
    (
        id,
        id_tipo_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_regla_acumulacion_id_s1'),
        4,
        'MEN',
        'COUNT',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_regla_acumulacion
    (
        id,
        id_tipo_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_regla_acumulacion_id_s1'),
        6,
        'MEN',
        'SUM',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_regla_acumulacion
    (
        id,
        id_tipo_movimiento,
        periocidad,
        codigo_operacion,
        estado,
        fecha_estado,
        fecha_creacion
    )
    VALUES
    (
        nextval('${schema}.cdt_regla_acumulacion_id_s1'),
        7,
        'VIDA',
        'SUM',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );



-- LIMITES
INSERT INTO
	${schema}.cdt_limite
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
    	nextval('${schema}.cdt_limite_id_s1'),
    	1,
    	-1,
    	'Primera carga debe ser menor o igual a ',
    	50000,
        'MENORQIG',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        1,
        -1,
        'Primera carga debe ser mayor o igual a ',
        3000,
        'MAYORQIG',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        1,
        1,
        'Carga debe ser menor o igual a ',
        50000,
        'MENORQIG',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        1,
        2,
        'Contador Primera carga debe ser = 0',
        1,
        'IGUAL',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        3,
        -1,
        'DEBE SER MENOR O IGUAL QUE',
        500000,
        'MENORQIG',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        3,
        -1,
        'DEBE SER MAYOR O IGUAL QUE',
        3000,
        'MAYORQIG',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        3,
        1,
        'DEBE SER MENOR O IGUAL QUE',
        1000000,
        'MENORQIG',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        5,
        -1,
        'DEBE SER MENOR O IGUAL QUE',
        100000,
        'MENORQIG',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        5,
        -1,
        'DEBE SER MAYOR O IGUAL QUE',
        3000,
        'MAYORQIG',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        5,
        1,
        'DEBE SER MENOR O IGUAL QUE',
        1000000,
        'MENORQIG',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        8,
        -1,
        'Primera carga debe ser menor o igual a ',
        50000,
        'MENORQIG',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        8,
        -1,
        'DEBE SER MAYOR O IGUAL QUE',
        1000,
        'MAYORQIG',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        8,
        4,
        'DEBE SER MENOR O IGUAL QUE',
        1000000,
        'MENORQIGU',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        8,
        -1,
        'DEBE SER MENOR O IGUAL QUE',
        1000000,
        'MENORQIG',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        10,
        -1,
        'DEBE SER MAYOR O IGUAL QUE',
        1000,
        'MAYORQIG',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_limite
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
        nextval('${schema}.cdt_limite_id_s1'),
        10,
        4,
        'DEBE SER MENOR O IGUAL QUE',
        1000000,
        'MENORQIGU',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );
