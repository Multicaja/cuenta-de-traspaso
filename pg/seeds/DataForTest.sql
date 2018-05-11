-- INSERT PARA BOLSA

INSERT INTO ${schema}.cdt_bolsa
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



INSERT INTO ${schema}.cdt_bolsa
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



INSERT INTO ${schema}.cdt_bolsa
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
	${schema}.cdt_cuenta
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
INSERT INTO
	${schema}.cdt_fase_movimiento
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

INSERT INTO
    ${schema}.cdt_fase_movimiento
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

INSERT INTO
    ${schema}.cdt_fase_movimiento
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

INSERT INTO
    ${schema}.cdt_fase_movimiento
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
        1,
        'S',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_fase_movimiento
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

INSERT INTO
    ${schema}.cdt_fase_movimiento
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
        1,
        'S',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_fase_movimiento
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
        1,
        'N',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


INSERT INTO
    ${schema}.cdt_fase_movimiento
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


INSERT INTO
    ${schema}.cdt_fase_movimiento
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


INSERT INTO
    ${schema}.cdt_fase_movimiento
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
        1,
        'S',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_fase_movimiento
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
        'Solicitud Reversa de Retiro',
        'Solicitud Reversa de Retiro',
        1,
        'N',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_fase_movimiento
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
        'Confirmación Reversa de Retiro',
        'Confirmación Reversa de Retiro',
        1,
        'S',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );


-- INSERT TIPO MOVIMIENTO
INSERT INTO
	${schema}.cdt_categoria_movimiento
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
    ${schema}.cdt_categoria_movimiento
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
    ${schema}.cdt_categoria_movimiento
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
    ${schema}.cdt_categoria_movimiento
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
    ${schema}.cdt_categoria_movimiento
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
    ${schema}.cdt_categoria_movimiento
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
    ${schema}.cdt_categoria_movimiento
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
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
    ${schema}.cdt_categoria_mov_fase
    (
        id_fase_movimiento,
        id_categoria_movimiento
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
    ${schema}.cdt_regla_acumulacion
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
    ${schema}.cdt_regla_acumulacion
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
    ${schema}.cdt_regla_acumulacion
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
    ${schema}.cdt_regla_acumulacion
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
    ${schema}.cdt_regla_acumulacion
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
    ${schema}.cdt_regla_acumulacion
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
	${schema}.cdt_limite
	(
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        1,
        2,
        'Contador Primera carga debe ser = a',
        1,
        'IGUAL',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );

INSERT INTO
    ${schema}.cdt_limite
    (
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        id_fase_movimiento,
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
        10,
        4,
        'DEBE SER MENOR O IGUAL QUE',
        1000000,
        'MENORQIGU',
        'ACTIVO',
        LOCALTIMESTAMP,
        LOCALTIMESTAMP
    );
