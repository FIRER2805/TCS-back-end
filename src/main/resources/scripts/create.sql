DROP DATABASE IF EXISTS TCS;
CREATE DATABASE TCS;
USE TCS;

CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    senha VARCHAR(255),
    telefone CHAR(11) UNIQUE
);

CREATE TABLE setor (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE usuario_setor (
    id_usuario BIGINT,
    id_setor BIGINT,
    administrador TINYINT NOT NULL DEFAULT 0,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_setor) REFERENCES setor(id)
);

create table contato(
	id BIGINT not null auto_increment primary key
	,id_usuario BIGINT
	,nome varchar(255)
	,numero char(11) unique
	,foreign key (id_usuario) references usuario(id)
);

CREATE TABLE mensagem (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    conteudo VARCHAR(255) NOT NULL,
    input_pai varchar(255),
    id_mensagem_pai BIGINT,
    FOREIGN KEY (id_mensagem_pai) references mensagem(id),
    id_setor BIGINT null,
    FOREIGN KEY (id_setor) references setor(id)
);

CREATE TABLE mensagem_historico(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	conteudo VARCHAR(255) NOT NULL,
	id_contato BIGINT NOT NULL,
	FOREIGN KEY (id_contato) references contato(id)
);