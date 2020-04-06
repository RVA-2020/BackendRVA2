package rva.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the artikl database table.
 * 
 */

/*
 * Artikl klasa predstavlja jedanu Java Bean klasu, znači da se u klasi nalaze varijable
 * instanci i get i set metode za te varijable, da implementira Serializable interface i da ima,
 * u ovom slučaju implicitni, prazan konstruktor. Varijable instance u datoj klasi odgovaraju
 * kolonama u tabeli u bazi podataka.
 */

/*
 * @Entity predstavlja JPA anotaciju. Uloga anotacije je da stavi do znanja da se radi
 * o entitetu koji ima ID i koji se koristi kako bi se omogućila persistencija podatak.
 * Klasa as @Entity anotacijom predstavlja klasu koja se mapira u tabelu bazi podataka.  
 */

/*
 * @NamedQuery anotacija (konkretna anotacija je kreirana prilikom nastanka ovih JPA klasa)
 * je takođe JPA anotacija koja omogućava da određenom upitu, koji je pisan u Java Persistency
 * Querry Language, date naziv po kom ga kasnije možete referencirati.
 */

/*
 * @JsonIgnoreProperties anotacija dolazi iz FasterXML/Jackson paketa (možete ga potražiti
 * unutar Maven Dependencies foldera) i može se koristiti i na klasama i na varijablama instance.
 * Oznaćava da određenu osobinu treba ignorisati. U konkretnom slučaju potrebno je ignorisati
 * hibernateLazyInitializer. Ova anotacija nije automatski kreirana kada su kreirane JPA klase,
 * već je dodata naknadno kako bi se ova osobina ignorisala. Možete zakomentarisati ovu anotaciju
 * kako bi videli razliku u ispisu prilikom poziva nekih od @GetMapping metoda u 
 * ArtiklRestController-u. 
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
@Entity
@NamedQuery(name="Artikl.findAll", query="SELECT a FROM Artikl a")
public class Artikl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/*
	 * @Id predstavlja JPA anotaciju i označava varijablu instance koja mapira primarni kljuc
	 * u bazi podataka. Ukoliko je klasa anotirana sa @Entity obavezno je da ima varijablu
	 * instance koja će biti anotirana sa @Id 
	 */
	
	/*
	 * @SequenceGenerator predstavlja JPA anotaciju koja se koristi kako bi se naveo naziv
	 * sekvence u bazi podataka koja će se koristiti za određivanje naredne vrednosti, naziv 
	 * sekvence u bazi podataka i vrednost za allocatioSize. Anotacija je kreirana automatski 
	 * sa kreiranjem klase, izuzetak je parametar allocationSize=1 koji određuje da se vrednosti
	 * povećavaju za 1. Bez ovog parametra kreirani ID-evi imale bi negativne vrednosti.
	 */
	
	/*
	 * @GeneratedValue je JPA anotacija i označava da će vrednost biti automatski generisana i
	 * navodi neophodne parametre, strategiju i generator koji će se koristiti.
	 */
	
	@Id
	@SequenceGenerator(name="ARTIKL_ID_GENERATOR", sequenceName="ARTIKL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARTIKL_ID_GENERATOR")
	private Integer id;

	private String naziv;

	private String proizvodjac;
	
	/*
	 * Ukoliko pogledate šemu koju smo koristili za kreiranje baze podataka možete videti
	 * da je Artikl strani ključ u relaciji StavkaPorudzbine i da se jedan artikal može naći
	 * u više stavki porudžbine, a da jedna stavka porudžbine može imati samo jedan artikal.
	 * Iako se u tabeli Artikal ne nalazi kolona Stavka_Porudzbine, ona se u ovoj klasi
	 * pojavljuje zato što se po automatizmu prilikom kreiranja JPA klasa kreira tzv. 
	 * bidirekciona asocijacija između Artikla i StavkePorudzbine što je naglašeno komentarom
	 * na engleskoj jeziku nekoliko linija niže.
	 * Bidirekciona asocijacija označava da je moguće "navigirati" sa obe strane, odnosno da je
	 * "preko" klase Artikla moguće doći do svih stavki porudžbine u kojima se određeni artikl
	 * nalazi i obrnuto, da je preko klase StavkaPorudzbine moguće doći do svakog artikla
	 * koji se nalazi u stavi porudžbine.
	 * Ukoliko nije potrebna bidirekciona navigacija asocijacije, moguće je realizovati tzv.
	 * unidirekcionu asocijaciju
	 */
	
	/*
	 * @OneToMany je JPA anotacija koja mapira odnos jedan prema više u asocijaciji
	 */
	
	/*
	 * @JsonIgnore je FasterXML/Jackson anotacija koja označava da određenu varijablu instance
	 * treba ignorisati. Ova anotacija nije kreirana prilikom automatskog generisanja JPA klasa
	 * već je naknadno dodata. Bez ove anotacije stvorila bi se "beskonačna petlja" prilikom
	 * pokušaja prikaza podataka iz baze podataka. Ukoliko bi npr. zatražili da se prikažu svi
	 * artikli, kada bi učitali prvi, jedna od vrednosti bile bi stavke porudžbina gde se taj
	 * artikl može pronaći, pa bi se prikazivali podaci vezani za konkretne stavke porudžbine.
	 * U svakoj stavci porudžbine postiji "drugi kraj" bidirekcionih mapiranja, koji pokazuje
	 * na Artikal, pa bi se vratili u Artikal klasu, iz nje ponovo u stavku porudžbine i tako
	 * beskonačno.
	 * Kako izgleda pomenuti problem može se testirati ukoliko se zakomentariše ova anotacija
	 * i pokuša prikaz podataka o jednom ili svim artiklima
	 */

	//bi-directional many-to-one association to StavkaPorudzbine
	@JsonIgnore
	@OneToMany(mappedBy="artikl")
	private List<StavkaPorudzbine> stavkaPorudzbines;

	public Artikl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getProizvodjac() {
		return this.proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public List<StavkaPorudzbine> getStavkaPorudzbines() {
		return this.stavkaPorudzbines;
	}

	public void setStavkaPorudzbines(List<StavkaPorudzbine> stavkaPorudzbines) {
		this.stavkaPorudzbines = stavkaPorudzbines;
	}

	public StavkaPorudzbine addStavkaPorudzbine(StavkaPorudzbine stavkaPorudzbine) {
		getStavkaPorudzbines().add(stavkaPorudzbine);
		stavkaPorudzbine.setArtikl(this);

		return stavkaPorudzbine;
	}

	public StavkaPorudzbine removeStavkaPorudzbine(StavkaPorudzbine stavkaPorudzbine) {
		getStavkaPorudzbines().remove(stavkaPorudzbine);
		stavkaPorudzbine.setArtikl(null);

		return stavkaPorudzbine;
	}

}