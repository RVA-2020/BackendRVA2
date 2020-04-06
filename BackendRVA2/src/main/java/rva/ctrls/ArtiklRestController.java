package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Artikl;
import rva.repos.ArtiklRepository;

/*
 * @RestController - predstavlja anotaciju koja se koristi na nivou klase.
 * @RestController anotacija je kombinacija anotacija @Controller i @ResponseBody
 * @Controller - anotacija koja se koristi da bi se označilo da se radi o klasi koja je
 * Spring Controller i može se koristiti u npr. Spring MVC
 * @ResponseBody - označava da rezultat izvršavanja treba da bude smešten u Response Body u
 * željenom formatu (podrazumevani format je JSON). Više informacija HTTP porukama možete 
 * pronaći na linku 
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages
 */

@RestController
@Api(tags = {"Artikl CRUD operacije"})
public class ArtiklRestController {

	/*
	 * Anotacija @Autowired se može primeniti nad varijablama instace, setter metodama i
	 * konstruktorima. Označava da je neophodno injektovati zavisni objekat. Prilikom
	 * pokretanja aplikacije IoC kontejner prolazi kroz kompletan kod tražeči anotacije
	 * koje označavaju da je potrebno kreirati objekte. Upotrebom @Autowired anotacije
	 * stavljeno je do znanja da je potrebno kreirati objekta klase koja će implementirati
	 * repozitorijum AriklRepository i proslediti klasi ArtiklRestController referencu
	 * na taj objekat. 
	 */
	@Autowired
	private ArtiklRepository artiklRepository;
	
	/* JdbcTemplate klasa pojednostavljuje korišćenje JDBC (Java Database 
	 * Connectivity), objekat ove klase će se koristiti u metodi delete() i zato je
	 * deklarisan i anotiran sa @Autowired
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("artikl")
	@ApiOperation(value = "Vraća kolekciju svih artikala iz baze podataka")
	public Collection<Artikl> getArtikli() {
		return this.artiklRepository.findAll();
	}
	
	/*
	 * U slučaju metode getOne(), novina je uvođenje promenljive koja je predstavljena kao
	 * {id} u @GetMapping("artikl/{id}"). Mapiranje promenljive u vrednost koja se prosleđuje
	 * konkretnoj metodi getOne() vrši se upotrebom anotacije @PathVariable.
	 * U konkretnom slučaju HTTP GET zahtev upućen na adresu localhost:8083/artikl/1 biće
	 * prosleđen ovoj metodi, a vrednost 1 kao ID.
	 * 
	 *  Poziv metode artiklRepository.getOne(id) će vratiti konkretan artikal sa datim ID-je
	 *  i taj artikal će potom biti prikazan u browseru u JSON formatu. 
	 */
	@GetMapping("artikl/{id}")
	@ApiOperation(value = "Vraća artikl iz baze podataka čiji ID je vrednost prosleđena kao path varijabla")
	public Artikl getArtikl(@PathVariable("id") Integer id) {
		return this.artiklRepository.getOne(id);
	}
	
	@GetMapping("artiklNaziv/{naziv}")
	@ApiOperation(value = "Vraća artikle iz baze podataka koji u nazivu sadrže string koji je prosleđen kao path varijabla")
	public Collection<Artikl> getArtiklByNaziv(@PathVariable("naziv") String naziv) {
		return artiklRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	/*
	 * HTTP DELETE je jedna od HTTP metoda koja je analogna opciji DELETE iz CRUD operacija.
	 * Anotacija @DELETEMapping se koristi kako bi se mapirao HTTP DELETE zahtev.
	 * Predstavlja skraćenu verziju metode @RequestMapping(method = RequestMethod.DELETE)
	 * U konkretnom slučaju HTTP PUT zahtevi upućeni na adresu localhost:8083/artikl/{id} 
	 * biće prosleđeni ovoj metodi.
	 * Poziv metode artiklRepository.deleteByID(id) će obrisati artikl sa prosleđenim ID-ije
	 * iz baze podataka.
	 */
	
	@DeleteMapping("artikl/{id}")
	@ApiOperation(value = "Briše Artikl iz baze podataka čiji ID je vrednost prosleđena kao path varijabla")
	public ResponseEntity<Artikl> deleteArtikl(@PathVariable("id") Integer id) {
		if(!artiklRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		artiklRepository.deleteById(id);
		if(id == -100 && !artiklRepository.existsById(-100))
			jdbcTemplate.execute(" INSERT INTO \"artikl\"(\"id\", \"naziv\", \"proizvodjac\") VALUES (-100, 'Naziv test', 'Proizvodjac test') ");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/*
	 * HTTP POST je jedna od HTTP metoda koja je analogna opciji CREATE iz CRUD operacija.
	 * Anotacija @PostMapping se koristi kako bi se mapirao HTTP POST zahtev.
	 * Predstavlja skraćenu verziju metode @RequestMapping(method = RequestMethod.POST)
	 * U konkretnom slučaju HTTP POST zahtevi upućeni na adresu localhost:8083/artikl 
	 * biće prosleđeni ovoj metodi.
	 * Poziv metode artiklRepository.save(artikl) će sačuvati prosleđeni artikl u bazi
	 * podataka
	 */
	
	// insert
	@PostMapping("artikl")
	@ApiOperation(value = "Dodaje Artikl u bazu podataka")
	public ResponseEntity<Artikl> insertArtikl(@RequestBody Artikl artikl) {
		if (!artiklRepository.existsById(artikl.getId())) {
			artiklRepository.save(artikl);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	
	/*
	 * HTTP PUT je jedna od HTTP metoda koja je analogna opciji UPDATE iz CRUD operacija.
	 * Anotacija @PutMapping se koristi kako bi se mapirao HTTP PUT zahtev.
	 * Predstavlja skraćenu verziju metode @RequestMapping(method = RequestMethod.PUT)
	 * U konkretnom slučaju HTTP PUT zahtevi upućeni na adresu localhost:8083/artikl/{id} 
	 * biće prosleđeni ovoj metodi.
	 * Poziv metode artiklRepository.save(artikl) će izmeniti artikl sa prosleđenim ID-ijem
	 * i prosleđenim sadržajem u bazi podataka.
	 */
	
	// update
	@PutMapping("artikl")
	@ApiOperation(value = "Modifikuje Artikl u bazi podataka")
	public ResponseEntity<Artikl> updateArtikl(@RequestBody Artikl artikl) {
		if (!artiklRepository.existsById(artikl.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		artiklRepository.save(artikl);
		return new ResponseEntity<>(HttpStatus.OK);
	}
			
}
