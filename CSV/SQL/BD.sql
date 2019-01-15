DROP database IF exists `questoes`;
CREATE DATABASE questoes;
USE questoes;

CREATE TABLE Questoes (
    cod BIGINT AUTO_INCREMENT PRIMARY KEY,
    enunciado TEXT,
    referencia TEXT
);

CREATE TABLE Alternativa (
    cod BIGINT AUTO_INCREMENT PRIMARY KEY,
    cod_q BIGINT,
    classificacao SMALLINT DEFAULT 0,
    resposta TEXT,
    FOREIGN KEY (cod_q)
        REFERENCES Questoes (cod)
);