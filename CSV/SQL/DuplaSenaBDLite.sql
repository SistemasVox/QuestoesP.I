DROP DATABASE  IF EXISTS `DuplaSena`;
CREATE DATABASE DuplaSena;
USE DuplaSena;

CREATE TABLE [DuplaSena](
  [Concurso] INT PRIMARY KEY NOT NULL UNIQUE, 
  [DataSorteio] DATE, 
  [1Dezena] SMALLINT, 
  [2Dezena] SMALLINT, 
  [3Dezena] SMALLINT, 
  [4Dezena] SMALLINT, 
  [5Dezena] SMALLINT, 
  [6Dezena] SMALLINT, 
  [Ganhadores] SMALLINT, 
  [1Dezena2] SMALLINT, 
  [2Dezena2] SMALLINT, 
  [3Dezena2] SMALLINT, 
  [4Dezena2] SMALLINT, 
  [5Dezena2] SMALLINT, 
  [6Dezena2] SMALLINT, 
  [Ganhadores2] SMALLINT);[Ganhadores2] INT
);

SELECT * FROM DuplaSena ORDER BY [DataSorteio] DESC;

SELECT max(concurso) FROM DuplaSena WHERE ([1Dezena] = '02' OR [2Dezena] = '02' OR [3Dezena] = '02' OR [4Dezena] = '02' OR [5Dezena] = '02' OR [1Dezena2] = '02' OR [2Dezena2] = '02' OR [3Dezena2] = ',2' OR [4Dezena2] = '02' OR [5Dezena2] = '02' OR [6Dezena2] = '02' OR [6Dezena] = '02');