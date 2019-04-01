DROP database IF exists `questoes`;
CREATE DATABASE questoes;
USE questoes;

CREATE TABLE Questoes (
    cod BIGINT AUTO_INCREMENT PRIMARY KEY,
    enunciado TEXT,
	dificuldade TEXT,
    referencia TEXT
);

CREATE TABLE Alternativa (
    cod BIGINT AUTO_INCREMENT PRIMARY KEY,
    cod_q BIGINT,
    classificacao SMALLINT DEFAULT 0,
    resposta TEXT,
	justificativa TEXT,
    FOREIGN KEY (cod_q)
        REFERENCES Questoes (cod)
);

-- SQLLite --
CREATE TABLE [Questoes](
  [cod] BIGINT AUTO_INCREMENT PRIMARY KEY, 
  [enunciado] TEXT, 
  [dificuldade] TEXT CHECK ([dificuldade] IN ('Fácil', 'Médio', 'Difícil')), 
  [referencia] TEXT);

CREATE TABLE [Alternativa](
  [cod] [BIGINT AUTO_INCREMENT] PRIMARY KEY, 
  [cod_q] BIGINT REFERENCES [Questoes]([cod]) ON DELETE CASCADE ON UPDATE CASCADE, 
  [classificacao] SMALLINT DEFAULT 0, 
  [resposta] TEXT, 
  [justificativa] TEXT);
