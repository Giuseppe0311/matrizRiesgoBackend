CREATE TABLE matriz
(
    id          BIGSERIAL NOT NULL,
    estado      BOOLEAN   NOT NULL,
    nombre      VARCHAR(255),
    minima      INTEGER,
    menor       INTEGER,
    moderada    INTEGER,
    mayor       INTEGER,
    maxima      INTEGER,
    muy_alta    INTEGER,
    alta        INTEGER,
    media       INTEGER,
    baja        INTEGER,
    muy_baja    INTEGER,
    de_verde    INTEGER,
    a_verde     INTEGER,
    de_amarillo INTEGER,
    a_amarillo  INTEGER,
    de_naranja  INTEGER,
    a_naranja   INTEGER,
    de_rojo     INTEGER,
    a_rojo      INTEGER,
    id_usuario  BIGINT,
    CONSTRAINT pk_matriz PRIMARY KEY (id)
);

CREATE TABLE eventos
(
    id           BIGSERIAL NOT NULL,
    estado       BOOLEAN   NOT NULL,
    nombre       VARCHAR(255),
    nivel_riesgo VARCHAR(255),
    probabilidad VARCHAR(255),
    impacto      VARCHAR(255),
    valor        INTEGER,
    dominio      VARCHAR(255),
    objetivo     VARCHAR(255),
    control      VARCHAR(255),
    id_usuario   BIGINT,
    matriz_id    BIGINT,
    CONSTRAINT pk_eventos PRIMARY KEY (id)
);

CREATE TABLE iso
(
    id       BIGSERIAL NOT NULL,
    dominio  VARCHAR(255),
    objetivo VARCHAR(255),
    control  VARCHAR(255),
    CONSTRAINT pk_iso PRIMARY KEY (id)
);

ALTER TABLE eventos
    ADD CONSTRAINT FK_EVENTOS_ON_MATRIZ FOREIGN KEY (matriz_id) REFERENCES matriz (id);

INSERT INTO matriz (
    estado, a_amarillo, a_naranja, a_rojo, a_verde, alta, baja,
    de_amarillo, de_naranja, de_rojo, de_verde, id_usuario, maxima,
    mayor, media, menor, minima, moderada, muy_alta, muy_baja, nombre
) VALUES (
             TRUE, 5, 5, 4, 2, 2, 4,
             3, 4, 4, 1, 0, 1,
             1, 3, 1, 1, 1, 1, 5, 'Matriz Demo'
         );

INSERT INTO eventos (
    estado, control, dominio, id_usuario, impacto, nivel_riesgo,
    nombre, objetivo, probabilidad, valor, matriz_id
) VALUES (
             TRUE, NULL, NULL, NULL, '4', 'Riesgo Tolerable',
             'Incendio', NULL, '3', 4, 1
         );
