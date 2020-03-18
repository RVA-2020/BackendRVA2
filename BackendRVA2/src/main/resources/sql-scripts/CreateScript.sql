DROP TABLE IF EXISTS artikl CASCADE;
DROP TABLE IF EXISTS dobavljac CASCADE;
DROP TABLE IF EXISTS porudzbina CASCADE;
DROP TABLE IF EXISTS stavka_porudzbine CASCADE;

DROP SEQUENCE IF EXISTS artikl_seq;
DROP SEQUENCE IF EXISTS dobavljac_seq;
DROP SEQUENCE IF EXISTS porudzbina_seq;
DROP SEQUENCE IF EXISTS stavka_porudzbine_seq;

CREATE TABLE artikl(
	id integer not null,
    naziv varchar(50) not null,
    proizvodjac varchar(50)
);

CREATE TABLE dobavljac(
	id integer not null,
    naziv varchar(50) not null,
    adresa varchar(200) not null,
    kontakt varchar(100) not null
);

CREATE TABLE porudzbina(
    id integer not null,
    datum date not null,
    isporuceno date not null,
    iznos numeric not null,
    placeno boolean,
    dobavljac integer not null
);

CREATE TABLE stavka_porudzbine(
	id integer not null,
    redni_broj integer not null,
    kolicina numeric not null,
    jedinica_mere varchar(30) not null,
    cena numeric not null,
    porudzbina integer not null,
    artikl integer not null
);

ALTER TABLE artikl ADD CONSTRAINT
PK_Artikl PRIMARY KEY(id);
ALTER TABLE dobavljac ADD CONSTRAINT
PK_Dobavljac PRIMARY KEY(id);
ALTER TABLE porudzbina ADD CONSTRAINT
PK_Porudzbina PRIMARY KEY(id);
ALTER TABLE stavka_porudzbine ADD CONSTRAINT
PK_Stavka_Porudzbine PRIMARY KEY(id);

ALTER TABLE porudzbina ADD CONSTRAINT
FK_Porudzbina_Dobavljac FOREIGN KEY(dobavljac) REFERENCES dobavljac(id);
ALTER TABLE stavka_porudzbine ADD CONSTRAINT
FK_Stavka_Porudzbine_Porudzbina FOREIGN KEY(porudzbina) REFERENCES porudzbina(id);
ALTER TABLE stavka_porudzbine ADD CONSTRAINT
FK_Stavka_Porudzbine_Artikl FOREIGN KEY(artikl) REFERENCES artikl(id);

CREATE INDEX IDXFK_Porudzbina_Dobavljac
ON porudzbina(dobavljac);
CREATE INDEX IDXFK_Stavka_Porudzbine_Porudzbina
ON stavka_porudzbine(porudzbina);
CREATE INDEX IDXFK_Stavka_Porudzbine_Artikl
ON stavka_porudzbine(artikl);

CREATE SEQUENCE artikl_seq
INCREMENT 1;
CREATE SEQUENCE dobavljac_seq
INCREMENT 1;
CREATE SEQUENCE porudzbina_seq
INCREMENT 1;
CREATE SEQUENCE stavka_porudzbine_seq
INCREMENT 1;