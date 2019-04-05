-- http://www.dpriver.com/pp/sqlformat.htm

CREATE TABLE [Questoes](
  [cod] BIGINT PRIMARY KEY, 
  [enunciado] TEXT, 
  [dificuldade] TEXT CHECK([dificuldade] IN ('Fácil', 'Médio', 'Difícil')), 
  [referencia] TEXT);

CREATE TABLE [Alternativa](
  [cod] BIGINT PRIMARY KEY, 
  [cod_q] BIGINT REFERENCES [Questoes]([cod]) ON DELETE CASCADE ON UPDATE CASCADE, 
  [classificacao] SMALLINT DEFAULT 0, 
  [resposta] TEXT, 
  [justificativa] TEXT);

CREATE TABLE [Area_Conhecimento](
  [cod_area] INTEGER PRIMARY KEY AUTOINCREMENT, 
  [nome_area] TEXT NOT NULL UNIQUE);

CREATE TABLE [Disciplina](
  [cod_disciplina] INTEGER PRIMARY KEY AUTOINCREMENT, 
  [nome_disciplina] TEXT, 
  [descricao_disciplina] TEXT, 
  [cod_area] INT, 
  FOREIGN KEY([cod_area]) REFERENCES [area_conhecimento]([cod_area]) ON DELETE CASCADE ON UPDATE CASCADE);
  
  CREATE TABLE [Conteudo](
  [cod_conteudo] INTEGER PRIMARY KEY AUTOINCREMENT, 
  [nome_conteudo] TEXT, 
  [descricao_conteudo] TEXT, 
  [disciplina_auxiliar] TEXT);

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
  FOREIGN KEY([cod_questao]) REFERENCES [Questoes]([cod]));

UPDATE sqlite_sequence SET seq = 0 WHERE name = 'Area_Conhecimento';
INSERT INTO [Area_Conhecimento]  ([nome_area])  VALUES ('Ciências Humanas e suas Tecnologias');
INSERT INTO [Area_Conhecimento]  ([nome_area])  VALUES ('Ciências da Natureza e suas Tecnologias');
INSERT INTO [Area_Conhecimento]  ([nome_area])  VALUES ('Linguagens, Códigos e suas Tecnologias');
INSERT INTO [Area_Conhecimento]  ([nome_area])  VALUES ('Matemática e suas Tecnologias');
SELECT * FROM [Area_Conhecimento];

UPDATE sqlite_sequence SET seq = 0 WHERE name = 'Disciplina';
INSERT INTO [Disciplina] (cod_area, nome_disciplina) VALUES (1, 'História');
INSERT INTO [Disciplina] (cod_area, nome_disciplina) VALUES (1, 'Geografia');
INSERT INTO [Disciplina] (cod_area, nome_disciplina) VALUES (1, 'Filosofia');
INSERT INTO [Disciplina] (cod_area, nome_disciplina) VALUES (1, 'Sociologia');

INSERT INTO [Disciplina] (cod_area, nome_disciplina) VALUES (2, 'Química');
INSERT INTO [Disciplina] (cod_area, nome_disciplina) VALUES (2, 'Física');
INSERT INTO [Disciplina] (cod_area, nome_disciplina) VALUES (2, 'Biologia');

INSERT INTO [Disciplina] (cod_area, nome_disciplina) VALUES (3, 'Língua Portuguesa');
INSERT INTO [Disciplina] (cod_area, nome_disciplina) VALUES (3, 'Literatura');
INSERT INTO [Disciplina] (cod_area, nome_disciplina) VALUES (3, 'Língua Estrangeira (Inglês ou Espanhol)');
INSERT INTO [Disciplina] (cod_area, nome_disciplina) VALUES (3, 'Artes');
INSERT INTO [Disciplina] (cod_area, nome_disciplina) VALUES (3, 'Educação Física e Tecnologias da Informação e Comunicação');

INSERT INTO [Disciplina] (cod_area, nome_disciplina) VALUES (4, 'Matemática');

SELECT * FROM Disciplina;

SELECT * FROM Disciplina WHERE cod_area = (SELECT cod_area FROM Area_Conhecimento WHERE nome_area = 'Ciências Humanas e suas Tecnologias');

UPDATE sqlite_sequence SET seq = 0 WHERE name = 'Conteudo';
-- História
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Monarquia no Brasil - I',  'Com exercícios para você estudar e revisar os seus conhecimentos de datas e acontecimentos do período monárquico no Brasil.');
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('História do Brasil - IV',  'Com exercícios para você estudar e revisar os seus conhecimentos com perguntas desde o Brasil pré-colonial até a fundação da república.');

-- Geografia
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Geografia Mundial - IX',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre história da filosofia e filosofia básica, desde os primórdios da filosofia até a atualidade.');
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Planeta Terra - I',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre o que é biosfera, atmosfera, hidrosfera e litosfera? Conhece os movimentos de rotação e translação?.');

-- Filosofia
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('História da Filosofia - I',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre e história da filosofia e filosofia básica, desde os primórdios da filosofia até a atualidade.');
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Filosofia - I',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre os filósofos, as obras, as teorias e outros assuntos sobre filosofia.');

-- Sociologia
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Sociologia - I',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre a sociologia, a cidadania e sobre os sociólogos.');
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Sociologia - II',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre Sociologia, um das matérias que está cada vez mais presente nos grandes vestibulares.');

-- Química
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Tabela Periódica - VI',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre tabela periódica e os seus elementos.');
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Átomos - III',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre S átomos para aqueles que estão estudando para o ENEM.');

-- Física
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Termometria e Calorimetria',  'Com exercícios para você estudar e revisar os seus conhecimentos básicos de escalas termométricas e calorimetria.');
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Física Básica - II',  'Com exercícios para você estudar e revisar os seus conhecimentos básicos de introdução à óptica e à termologia.');

-- Biologia
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Reino Fungi - II',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre o Reino Fungi.');
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Biomedicina - III',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre o corpo humano, seu funcionamento e suas doenças.');

-- Português
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Português - VII',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre sobre hiato, sujeito, crase, palavras oxítonas, paroxítonas e proparoxítonas, sinônimos, antônimos e parônimos..');
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Vocabulário - III',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre o significado de palavras como "analgia", "quilombo" e outros.');

-- Literatura
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Pré-Modernismo - I',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre o pré modernismo na literatura brasileira.');
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Escolas Literárias - I',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre os autores e as características de escola literárias como Parnasianismo, Romantismo e Trovadorismo.');

-- Inglês
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Falsos Cognatos',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre quais são alguns dos falsos cognatos em inglês (false friends).');
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Avalie seu Inglês',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre língua inglesa.');

-- Arte
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Pablo Picasso - II',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre um dos mais famosos artistas de todos os tempo: Pablo Picasso.');
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Romeu e Julieta - II',  'Com exercícios para você estudar e revisar os seus conhecimentos sobreo clássico Romeu e Julieta.');

-- Edu Fisi e Cominicação
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Os Vingadores 2: A Era de Ultron',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre sobre o filme "Os Vingadores 2: A Era de Ultron".');
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Batman vs Superman: A Origem da Justiça',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre Batman vs Superman: A Origem da Justiça.');

-- Matemática
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Matemática Básica - IX',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre sobre conteúdo do 6° ano (potenciação, expressões e problemas matemáticos).');
INSERT INTO [Conteudo] (nome_conteudo, descricao_conteudo) VALUES ('Matemática Básica - VII',  'Com exercícios para você estudar e revisar os seus conhecimentos sobre radiciação, potenciação, porcentagem, frações e outros mais.');

SELECT * FROM Conteudo;

-- Disciplina Conteúdo
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (1, 1, 1);
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (1, 2, 1);

INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (1, 3, 2);
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (2, 4, 2);

INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (1, 5, 3);
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (3, 6, 3);

INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (1, 7, 4);
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (2, 8, 4);

INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (2, 9, 5);
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (2, 10, 5);

INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (1, 11, 6);
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (2, 12, 6);

INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (1, 13, 7);
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (2, 14, 7);

INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (1, 15, 8);
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (1, 16, 8);

INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (2, 17, 9);
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (2, 18, 9);

INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (3, 19, 10);
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (3, 20, 10);

INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (3, 21, 11);
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (3, 22, 11);

INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (1, 23, 12);
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (1, 24, 12);

INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (1, 25, 13);
INSERT INTO Disciplina_Conteudo (serie, cod_conteudo, cod_disciplina) VALUES (1, 26, 13);

SELECT * FROM Disciplina_Conteudo;

SELECT nome_conteudo FROM Conteudo c, Disciplina_Conteudo dc where dc.cod_disciplina = (SELECT cod_disciplina FROM Disciplina WHERE nome_disciplina = 'História') and c.cod_conteudo = dc.cod_conteudo;

SELECT nome_conteudo FROM Conteudo c, Disciplina_Conteudo dc where dc.cod_disciplina = (SELECT cod_disciplina FROM Disciplina WHERE nome_disciplina = 'História') and c.cod_conteudo = dc.cod_conteudo;

-- Conteúdo Questão
-- Monarquia no Brasil - I
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (1, 1);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (1, 2);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (1, 3);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (1, 4);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (1, 5);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (1, 6);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (1, 7);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (1, 8);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (1, 9);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (1, 10);

-- História do Brasil - IV
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (2, 10);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (2, 11);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (2, 12);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (2, 13);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (2, 14);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (2, 15);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (2, 16);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (2, 17);

-- Geografia Mundial - IX
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (3, 18);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (3, 19);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (3, 20);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (3, 21);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (3, 22);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (3, 23);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (3, 24);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (3, 25);

-- Planeta Terra - I
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (4, 26);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (4, 27);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (4, 28);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (4, 29);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (4, 30);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (4, 31);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (4, 32);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (4, 33);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (4, 34);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (4, 35);

-- História da Filosofia - I
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 36);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 37);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 38);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 39);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 40);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 41);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 36);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 42);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 43);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 44);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 45);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 46);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 47);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 48);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 49);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (5, 50);

-- Filosofia - I
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (6, 51);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (6, 52);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (6, 53);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (6, 54);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (6, 55);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (6, 56);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (6, 57);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (6, 58);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (6, 59);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (6, 60);

-- Sociologia - I
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (7, 61);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (7, 62);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (7, 63);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (7, 64);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (7, 65);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (7, 66);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (7, 67);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (7, 68);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (7, 69);

-- Sociologia - II
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (8, 70);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (8, 71);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (8, 72);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (8, 73);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (8, 74);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (8, 75);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (8, 76);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (8, 77);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (8, 78);

-- Tabela Periódica - VI
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (9, 79);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (9, 80);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (9, 81);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (9, 82);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (9, 83);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (9, 84);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (9, 85);

-- Átomos - III
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (10, 86);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (10, 87);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (10, 88);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (10, 89);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (10, 90);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (10, 91);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (10, 92);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (10, 93);

-- Termometria e Calorimetria
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (11, 94);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (11, 95);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (11, 96);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (11, 97);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (11, 98);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (11, 99);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (11, 100);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (11, 101);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (11, 102);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (11, 103);

-- Física Básica - II
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (12, 104);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (12, 105);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (12, 106);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (12, 107);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (12, 108);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (12, 109);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (12, 110);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (12, 111);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (12, 112);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (12, 113);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (12, 114);
INSERT INTO Conteudo_Questao (cod_conteudo, cod_questao) VALUES (12, 115);