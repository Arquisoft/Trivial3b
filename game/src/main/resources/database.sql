CREATE TABLE JUGADORES(ID SERIAL NOT NULL PRIMARY KEY,USERNAME VARCHAR(100) NOT NULL,PASSWORD VARCHAR(100) NOT NULL,ROL VARCHAR(20) NOT NULL)

CREATE TABLE PREGUNTAS(ID SERIAL NOT NULL PRIMARY KEY,IDPREGUNTA VARCHAR(64) NOT NULL,CATEGORIA VARCHAR(64) NOT NULL,ACIERTOS INTEGER,FALLOS INTEGER,UNIQUE(IDPREGUNTA))

CREATE TABLE ESTADISTICASJUGADOR(IDJUGADOR INTEGER,IDPREGUNTA VARCHAR(64),ACIERTOS INTEGER,FALLOS INTEGER,FOREIGN KEY(IDJUGADOR) REFERENCES JUGADORES(ID) ON DELETE CASCADE ON UPDATE CASCADE,FOREIGN KEY(IDPREGUNTA) REFERENCES PREGUNTAS(IDPREGUNTA) ON DELETE CASCADE ON UPDATE CASCADE)

INSERT INTO JUGADORES (USERNAME, PASSWORD, ROL) VALUES('cris','cris','usuario')
INSERT INTO JUGADORES (USERNAME, PASSWORD, ROL) VALUES('dani','dani','usuario')
INSERT INTO JUGADORES (USERNAME, PASSWORD, ROL) VALUES('llana','llana','usuario')
INSERT INTO JUGADORES (USERNAME, PASSWORD, ROL) VALUES('admin','admin','administrador')
