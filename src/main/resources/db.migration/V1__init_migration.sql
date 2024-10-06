CREATE TABLE empresas
(
    id          BIGSERIAL NOT NULL,
    estado BOOLEAN                                 NOT NULL,
    nombre VARCHAR(255),
    CONSTRAINT pk_empresas PRIMARY KEY (id)
);

CREATE TABLE eventos
(
    id          BIGSERIAL NOT NULL,
    estado              BOOLEAN                                 NOT NULL,
    nombre              VARCHAR(255),
    nivel_riesgo        VARCHAR(255),
    probabilidad        VARCHAR(255),
    impacto             VARCHAR(255),
    valor               INTEGER,
    dominio             VARCHAR(255),
    objetivo            VARCHAR(255),
    control             VARCHAR(255),
    matriz_id           BIGINT,
    id_usuario_asignado VARCHAR(255),
    CONSTRAINT pk_eventos PRIMARY KEY (id)
);

CREATE TABLE iso
(
    id          BIGSERIAL NOT NULL,
    dominio  VARCHAR(255),
    objetivo VARCHAR(255),
    control  VARCHAR(255),
    CONSTRAINT pk_iso PRIMARY KEY (id)
);

CREATE TABLE matriz
(
    id          BIGSERIAL NOT NULL,
    estado      BOOLEAN                                 NOT NULL,
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
    id_empresa  BIGINT,
    CONSTRAINT pk_matriz PRIMARY KEY (id)
);

CREATE TABLE usuarios
(
    id         VARCHAR(255) NOT NULL,
    estado     BOOLEAN      NOT NULL,
    nombre     VARCHAR(255),
    apellido   VARCHAR(255),
    email      VARCHAR(255),
    username   VARCHAR(255),
    id_empresa BIGINT,
    CONSTRAINT pk_usuarios PRIMARY KEY (id)
);

ALTER TABLE eventos
    ADD CONSTRAINT FK_EVENTOS_ON_MATRIZ FOREIGN KEY (matriz_id) REFERENCES matriz (id);