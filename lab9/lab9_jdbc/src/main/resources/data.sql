DROP TABLE IF EXISTS masini;
CREATE TABLE masini
	( nr_inmatriculare VARCHAR(20) NOT NULL PRIMARY KEY,
	marca VARCHAR(20) NOT NULL,
	an_fabricatie INTEGER NOT NULL CHECK (an_fabricatie > 1900),
	culoare VARCHAR(20) NOT NULL,
	nr_km INTEGER NOT NULL DEFAULT 0);

INSERT INTO masini
	VALUES ('HD09KBU', 'Dacia', 2010, 'negru', 200000);
INSERT INTO masini
	VALUES ('B068LGN', 'Ford', 2008, 'gri', 1000000);
INSERT INTO masini
	VALUES ('HD95GCD', 'Dacia', 2012, 'gri', 500000);
INSERT INTO masini
	VALUES ('AR00MIM', 'BMW', 2019, 'alb', 20000);
INSERT INTO masini
	VALUES ('TM96JGI', 'Mercedes', 2021, 'rosu', 10000);