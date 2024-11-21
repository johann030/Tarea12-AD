drop database if exists johann06;

create database Johann06;

use Johann06;

create table alumno(
NIA INTEGER PRIMARY KEY NOT NULL,
nombre VARCHAR(50) NOT NULL,
apellidos VARCHAR(80) NOT NULL,
genero VARCHAR(20) NOT NULL,
fechaNacimiento DATE NOT NULL,
ciclo VARCHAR(40) NOT NULL,
curso VARCHAR(20) NOT NULL,
id_grupo INTEGER DEFAULT 0 NOT NULL
);

create table grupos(
cod_grupo INTEGER PRIMARY KEY NOT NULL,
nombre VARCHAR(40) NOT NULL,
ciclo VARCHAR(40) NOT NULL,
aula INTEGER NOT NULL
);

ALTER TABLE alumno ADD CONSTRAINT fk_grupo FOREIGN KEY (id_grupo) REFERENCES grupos(cod_grupo) ON DELETE CASCADE;