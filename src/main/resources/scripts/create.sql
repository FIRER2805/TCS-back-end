DROP DATABASE IF EXISTS TCS;
CREATE DATABASE TCS;
USE TCS;

CREATE TABLE mensagem (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    conteudo VARCHAR(255) NOT NULL
);

CREATE TABLE input (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    conteudo VARCHAR(100) NOT NULL
);

CREATE TABLE arvore_mensagem (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_mensagem INT,
    id_input INT,
    FOREIGN KEY (id_mensagem) REFERENCES mensagem(id),
    FOREIGN KEY (id_input) REFERENCES input(id)
);

CREATE TABLE usuario (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    senha VARCHAR(255),
    telefone CHAR(11) UNIQUE
);

CREATE TABLE setor (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    id_arvore_mensagem INT,
    FOREIGN KEY (id_arvore_mensagem) REFERENCES arvore_mensagem(id)
);

CREATE TABLE usuario_setor (
    id_usuario INT,
    id_setor INT,
    administrador TINYINT NOT NULL DEFAULT 0,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_setor) REFERENCES setor(id)
);

create table contato(
	id int not null auto_increment primary key
	,id_usuario int not null
	,nome varchar(255) not null
	,numero char(11) unique
	,foreign key (id_usuario) references usuario(id)
);