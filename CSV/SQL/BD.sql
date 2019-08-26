DROP database IF exists `questoes`;
CREATE DATABASE questoes;
USE questoes;

CREATE TABLE Questoes (
    cod INT AUTO_INCREMENT PRIMARY KEY,
    enunciado TEXT,
    dificuldade TEXT,
    referencia TEXT
);

CREATE TABLE alternativa (
    cod INT AUTO_INCREMENT PRIMARY KEY,
    cod_q INT,
    classificacao SMALLINT DEFAULT 0,
    resposta TEXT,
    justificativa TEXT,
    FOREIGN KEY (cod_q)
        REFERENCES Questoes (cod)
);


CREATE TABLE area_conhecimento (
  cod_area INT AUTO_INCREMENT PRIMARY KEY, 
  nome_area text
);

CREATE TABLE disciplina (
  cod_disciplina INT AUTO_INCREMENT PRIMARY KEY, 
  nome_disciplina text,
  descricao_disciplina text,
  cod_area int,
  FOREIGN KEY (cod_area) REFERENCES Area_conhecimento (cod_area) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Conteudo (
  cod_conteudo INT AUTO_INCREMENT PRIMARY KEY, 
  nome_conteudo text,
  descricao_conteudo text,
  disciplina_auxiliar text
);

CREATE TABLE disciplina_conteudo (
  Serie int,
  cod_disciplina int,
  cod_conteudo int,
  FOREIGN KEY (cod_disciplina) REFERENCES Disciplina (cod_disciplina),
  FOREIGN KEY (cod_conteudo) REFERENCES Conteudo (cod_conteudo)
);

CREATE TABLE conteudo_questao (
  cod_conteudo int,
  cod_questao int,
  FOREIGN KEY (cod_conteudo) REFERENCES Conteudo (cod_conteudo),
  FOREIGN KEY (cod_questao) REFERENCES Questoes (cod)
);

-- SQLLite --
CREATE TABLE [Questoes](
  [cod] BIGINT AUTO_INCREMENT PRIMARY KEY, 
  [enunciado] TEXT, 
  [dificuldade] TEXT CHECK([dificuldade] IN ('Fácil', 'Médio', 'Difícil')), 
  [referencia] TEXT);

CREATE TABLE [Alternativa](
  [cod] [BIGINT AUTO_INCREMENT] PRIMARY KEY, 
  [cod_q] BIGINT REFERENCES [Questoes]([cod]) ON DELETE CASCADE ON UPDATE CASCADE, 
  [classificacao] SMALLINT DEFAULT 0, 
  [resposta] TEXT, 
  [justificativa] TEXT);

CREATE TABLE [Area_Conhecimento](
  [cod_area] AUTO_INCREMENT PRIMARY KEY, 
  [nome_area] TEXT);

CREATE TABLE [Disciplina](
  [cod_disciplina] AUTO_INCREMENT PRIMARY KEY, 
  [nome_disciplina] TEXT, 
  [descricao_disciplina] TEXT, 
  [cod_area] INT, 
  FOREIGN KEY([cod_area]) REFERENCES [area_conhecimento]([cod_area]) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE [Disciplina_Conteudo](
  [serie] INT, 
  [cod_disciplina] INT, 
  [cod_conteudo] INT, 
  FOREIGN KEY([cod_disciplina]) REFERENCES [disciplina]([cod_disciplina]), 
  FOREIGN KEY([cod_conteudo]) REFERENCES [conteudo]([cod_conteudo]));

CREATE TABLE [Conteudo_Questao](
  [cod_conteudo] INT, 
  [cod_questao] INT, 
  FOREIGN KEY([cod_conteudo]) REFERENCES [conteudo]([cod_conteudo]), 
  FOREIGN KEY([cod_questao]) REFERENCES [questao]([cod_questao]));
