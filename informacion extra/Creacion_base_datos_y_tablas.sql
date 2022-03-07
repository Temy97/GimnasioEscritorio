USE master;

GO

CREATE DATABASE gimnasio_db;

GO

USE gimnasio_db;

GO

CREATE TABLE profesor (
	dni char(9) CONSTRAINT PK_PROFESOR PRIMARY KEY,
	nombre varchar(45) NOT NULL,
	salario decimal(6,2) NOT NULL
);

CREATE TABLE clase(
	id char(3) CONSTRAINT PK_CLASE PRIMARY KEY,
	nombre varchar(45) NOT NULL,
	descripcion varchar(100),
	dni_profesor char(9) NOT NULL DEFAULT '00000000X',
	CONSTRAINT FK_DNI_PROFESOR FOREIGN KEY (dni_profesor) REFERENCES profesor(dni)
		ON DELETE SET DEFAULT
		ON UPDATE CASCADE
);

CREATE TABLE administrativo(
	dni char(9) CONSTRAINT PK_ADMINISTRATIVO PRIMARY KEY,
	pasword varchar(20) NOT NULL DEFAULT 'admin',
	nombre varchar(45) NOT NULL DEFAULT 'admin',
	apellidos varchar(45)
);

CREATE TABLE cliente(
	dni char(9) CONSTRAINT PK_CLIENTE PRIMARY KEY,
	pasword varchar(20) NOT NULL DEFAULT 'BIENVENIDO',
	nombre varchar (45) NOT NULL,
	apellidos varchar(45) NOT NULL,
	edad int NOT NULL,
	estado_empleo char(1) CHECK (estado_empleo IN ('S', 'N')),
	cuota decimal(6,2)
);

CREATE TABLE gestiona(
	dni_administrativo char(9) CONSTRAINT FK_DNI_ADMINISTRATIVO_GESTION FOREIGN KEY (dni_administrativo) REFERENCES administrativo(dni)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	id_clase char(3) CONSTRAINT FK_ID_CLASE_GESTIONA FOREIGN KEY (id_clase) REFERENCES clase(id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
);

CREATE TABLE alta_baja(
	dni_administrativo char(9) CONSTRAINT FK_DNI_ADMINISTRATIVO_ALTABAJA FOREIGN KEY (dni_administrativo) REFERENCES administrativo(dni)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	dni_cliente char(9) CONSTRAINT FK_ID_CLIENTE_ALTABAJA FOREIGN KEY (dni_cliente) REFERENCES cliente(dni)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE asiste(
	dni_cliente char(9) CONSTRAINT FK_DNI_CLIENTE_ASISTE FOREIGN KEY (dni_cliente) REFERENCES cliente(dni)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	id_clase char(3) CONSTRAINT FK_ID_CLASE_ASISTE FOREIGN KEY (id_clase) REFERENCES clase(id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
);
GO