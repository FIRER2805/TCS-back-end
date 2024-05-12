drop database if exists tcs;
create database tcs;
use tcs;

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
	,numero char(30) unique
	,foreign key (id_usuario) references usuario(id)
);

create table mensagem(
	id bigint not null auto_increment primary key,
    conteudo varchar(255) not null,
    id_setor bigint not null,
    foreign key(id_setor) references setor(id)
);

create table input(
	id bigint not null auto_increment primary key,
    conteudo varchar(50),
    id_mensagem_pai bigint,
    id_mensagem_filha bigint,
    foreign key(id_mensagem_pai) references mensagem(id) on delete cascade,
    foreign key(id_mensagem_filha) references mensagem(id) on delete set null
);

CREATE TABLE mensagem_historico(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	conteudo VARCHAR(255) NOT NULL,
	data_envio DATETIME NOT NULL,
	id_contato BIGINT NOT NULL,
	FOREIGN KEY (id_contato) references contato(id)
);