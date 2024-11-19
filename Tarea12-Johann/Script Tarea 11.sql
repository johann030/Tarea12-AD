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
grupo VARCHAR(20) NOT NULL
);