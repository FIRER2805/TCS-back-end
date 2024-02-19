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
	,id_usuario BIGINT not null
	,nome varchar(255) not null
	,numero char(11) unique
	,foreign key (id_usuario) references usuario(id)
);

CREATE TABLE mensagem (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    conteudo VARCHAR(255) NOT NULL,
    id_setor BIGINT null,
    FOREIGN KEY (id_setor) references setor(id)
);

CREATE TABLE `input`(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	conteudo VARCHAR(255) NOT NULL,
)

CREATE TABLE mensagem_input(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_mensagem_pai BIGINT null,
	id_mensagem_filha BIGINT null,
	id_input BIGINT null,
	FOREIGN KEY (id_mensagem_pai) references mensagem(id),
	FOREIGN KEY (id_mensagem_filha) references mensagem(id),
	FOREIGN KEY (id_input) references input(id)
)