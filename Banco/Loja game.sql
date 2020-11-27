CREATE DATABASE lojagame;

USE lojagame;

CREATE TABLE cliente (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    rg VARCHAR(9) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    cidade VARCHAR(150),
    bairro VARCHAR(150),
    complemento VARCHAR(150),
    endereco VARCHAR(100),
    numero VARCHAR(100),
    telefone VARCHAR(14),
    celular VARCHAR(9),
    email VARCHAR(100),
    genero VARCHAR(100),
    estado_civil VARCHAR(100),
    data_nascimento DATE NOT NULL
);

CREATE TABLE produto (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome_produto VARCHAR(100) NOT NULL,
    nome_criador VARCHAR(100) NOT NULL,
    plataforma VARCHAR(100) NOT NULL,
    generos VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    quantidade_produto INT NOT NULL,
    preco FLOAT NOT NULL
);

CREATE TABLE venda (
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_cliente INT NOT NULL,
    data_venda DATE NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

CREATE TABLE item_venda (
	id_produto INT NOT NULL,
    id_venda INT NOT NULL,
	qtd_produto INT NOT NULL,
	valor_total float NOT NULL,
	FOREIGN KEY (id_produto) REFERENCES produto(id),
	FOREIGN KEY (id_venda) REFERENCES venda(id)
);
