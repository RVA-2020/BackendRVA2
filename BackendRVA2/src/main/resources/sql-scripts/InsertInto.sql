-- dobavljac
INSERT INTO "dobavljac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('dobavljac_seq'), 'AD Imlek', 'Industrijsko naselje bb', '+381111222');
INSERT INTO "dobavljac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('dobavljac_seq'), 'Henkel Srbija', 'Bulevar Oslobodjenja 999', '+381813123');
INSERT INTO "dobavljac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('dobavljac_seq'), 'Fruit DOO', 'Novosadska 777', '+3813123126');
INSERT INTO "dobavljac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('dobavljac_seq'), 'CENTROPROIZVOD', 'Dobanovacki put bb', '+381681341');

-- test dobavljac
INSERT INTO "dobavljac"("id", "naziv", "adresa", "kontakt")
VALUES (-100, 'Test Naziv', 'Test Adresa', 'Test Kontakt');

-- artikl
INSERT INTO "artikl"("id", "naziv", "proizvodjac")
VALUES (nextval('artikl_seq'), 'Moja Kravica sveže mleko 2,8 % m.m. 1l', 'AD Imlek');
INSERT INTO "artikl"("id", "naziv", "proizvodjac")
VALUES (nextval('artikl_seq'), 'Moja Kravica dugotrajno mleko 3,2 % m.m. 1l', 'AD Imlek');
INSERT INTO "artikl"("id", "naziv", "proizvodjac")
VALUES (nextval('artikl_seq'), 'Moja Kravica beli sir 1kg', 'AD Imlek');

INSERT INTO "artikl"("id", "naziv", "proizvodjac")
VALUES (nextval('artikl_seq'), 'Persil Regular Prasak 2kg', 'Henkel Srbija');
INSERT INTO "artikl"("id", "naziv", "proizvodjac")
VALUES (nextval('artikl_seq'), 'Persil Regular Gel 2l', 'Henkel Srbija');
INSERT INTO "artikl"("id", "naziv", "proizvodjac")
VALUES (nextval('artikl_seq'), 'Persil Duo-caps', 'Henkel Srbija');

INSERT INTO "artikl"("id", "naziv", "proizvodjac")
VALUES (nextval('artikl_seq'), 'Jagoda', 'Fruit DOO');
INSERT INTO "artikl"("id", "naziv", "proizvodjac")
VALUES (nextval('artikl_seq'), 'Jabuka', 'Fruit DOO');
INSERT INTO "artikl"("id", "naziv", "proizvodjac")
VALUES (nextval('artikl_seq'), 'Kajsija', 'Fruit DOO');

INSERT INTO "artikl"("id", "naziv", "proizvodjac")
VALUES (nextval('artikl_seq'), 'Šlag pena', 'CENTROPROIZVOD');
INSERT INTO "artikl"("id", "naziv", "proizvodjac")
VALUES (nextval('artikl_seq'), 'Puding vanila', 'CENTROPROIZVOD');
INSERT INTO "artikl"("id", "naziv", "proizvodjac")
VALUES (nextval('artikl_seq'), 'Puding jagoda', 'CENTROPROIZVOD');

-- test artikl
INSERT INTO "artikl"("id", "naziv", "proizvodjac")
VALUES (-100, 'Test Naziv', 'Test Proizvodjac');

INSERT INTO "porudzbina"("id", "datum", "dobavljac", "isporuceno", "iznos", "placeno")
VALUES (nextval('porudzbina_seq'), to_date('01.03.2019.', 'dd.mm.yyyy.'), 1, to_date('01.05.2019.', 'dd.mm.yyyy.'), 500, true);
INSERT INTO "porudzbina"("id", "datum", "dobavljac", "isporuceno", "iznos", "placeno")
VALUES (nextval('porudzbina_seq'), to_date('01.06.2019.', 'dd.mm.yyyy.'), 2, to_date('21.06.2019.', 'dd.mm.yyyy.'), 1000, false);
INSERT INTO "porudzbina"("id", "datum", "dobavljac", "isporuceno", "iznos", "placeno")
VALUES (nextval('porudzbina_seq'), to_date('22.03.2019.', 'dd.mm.yyyy.'), 3, to_date('27.05.2019.', 'dd.mm.yyyy.'), 0, false);
INSERT INTO "porudzbina"("id", "datum", "dobavljac", "isporuceno", "iznos", "placeno")
VALUES (nextval('porudzbina_seq'), to_date('01.03.2019.', 'dd.mm.yyyy.'), 4, to_date('22.06.2019.', 'dd.mm.yyyy.'), 15000, true);
INSERT INTO "porudzbina"("id", "datum", "dobavljac", "isporuceno", "iznos", "placeno")
VALUES (nextval('porudzbina_seq'), to_date('01.07.2019.', 'dd.mm.yyyy.'), 4, to_date('01.10.2019.', 'dd.mm.yyyy.'), 800, true);

INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 1, 1, 1, 20, 'komad', 100);
INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 1, 2, 2, 30, 'komad', 150);
INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 1, 3, 3, 15, 'komad', 80);

INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 2, 1, 4, 30, 'komad', 120);
INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 2, 2, 5, 35, 'komad', 90);
INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 2, 3, 5, 40, 'komad', 125);

INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 3, 1, 7, 1000, 'kg', 500);
INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 3, 2, 8, 1500, 'kg', 700);
INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 3, 3, 9, 1200, 'kg', 600);

INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 4, 1, 10, 5, 'kg', 300);
INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 4, 2, 11, 10, 'kg', 320);
INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 4, 3, 12, 12, 'kg', 250);

INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 5, 1, 11, 5, 'kg', 320);
INSERT INTO "stavka_porudzbine"("id", "porudzbina", "redni_broj", "artikl", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_porudzbine_seq'), 5, 2, 12, 8, 'kg', 250);