CREATE TABLE ThemeOfUse (
idThemeOfUse INT,
nameThemeOfUse VARCHAR(30),

CONSTRAINT pk_theme_of_use PRIMARY KEY (idThemeOfUse));

CREATE TABLE Discipline (
idDiscipline INT,
nameDiscipline VARCHAR(30),

CONSTRAINT pk_discipline PRIMARY KEY (idDiscipline));

CREATE TABLE Degree (
idDegree INT,
nameDegree VARCHAR(30),

CONSTRAINT pk_degree PRIMARY KEY (idDegree));

CREATE TABLE Academy (
idAcademy INT,
nameAcademy VARCHAR(30),

CONSTRAINT pk_academy PRIMARY KEY (idAcademy));

CREATE TABLE AcademicRegion (
idAcademicRegion INT,
nameAcademicRegion VARCHAR(50),

CONSTRAINT pk_academic_region PRIMARY KEY (idAcademicRegion));

CREATE TABLE Typology (
idTypology INT,
idThemeOfUse INT,
idDiscipline INT,
idDegree INT,
idAcademy INT,
idAcademicRegion INT,
actorType VARCHAR(30),
link text,
resourceName VARCHAR(50),
resourceType VARCHAR(30),
commentary text,

CONSTRAINT pk_typology PRIMARY KEY (idTypology),
CONSTRAINT fk_idThemeOfUse_themeOfUse FOREIGN KEY (idThemeOfUse) REFERENCES ThemeOfUse(idThemeOfUse),
CONSTRAINT fk_idDiscipline_discipline FOREIGN KEY (idDiscipline) REFERENCES Discipline(idDiscipline),
CONSTRAINT fk_idDegree_degree FOREIGN KEY (idDegree) REFERENCES Degree(idDegree),
CONSTRAINT fk_idAcademy_academy FOREIGN KEY (idAcademy) REFERENCES Academy(idAcademy),
CONSTRAINT fk_idAcademicRegion_academicRegion FOREIGN KEY (idAcademicRegion) REFERENCES AcademicRegion(idAcademicRegion));

CREATE TABLE ActorIdentity (
idActorIdentity INT,
idTypology INT,
name VARCHAR(100),

CONSTRAINT pk_actorIdentity PRIMARY KEY (idActorIdentity),
CONSTRAINT fk_idTypology_Typology FOREIGN KEY (idTypology) REFERENCES typology(idTypology));