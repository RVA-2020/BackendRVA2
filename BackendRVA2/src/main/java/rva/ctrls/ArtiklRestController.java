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

import rva.jpa.Artikl;
import rva.repos.ArtiklRepository;

@RestController
public class ArtiklRestController {

	@Autowired
	private ArtiklRepository artiklRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("artikl")
	public Collection<Artikl> getArtikli() {
		return this.artiklRepository.findAll();
	}
	
	@GetMapping("artikl/{id}")
	public Artikl getArtikl(@PathVariable("id") Integer id) {
		return this.artiklRepository.getOne(id);
	}
	
	@GetMapping("artiklNaziv/{naziv}")
	public Collection<Artikl> getArtiklByNaziv(@PathVariable("naziv") String naziv) {
		return artiklRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@DeleteMapping("artikl/{id}")
	public ResponseEntity<Artikl> deleteArtikl(@PathVariable("id") Integer id) {
		if(!artiklRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		artiklRepository.deleteById(id);
		if(id == -100)
			jdbcTemplate.execute(" INSERT INTO \"artikl\"(\"id\", \"naziv\", \"proizvodjac\") VALUES (-100, 'Naziv test', 'Proizvodjac test') ");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// insert
	@PostMapping("artikl")
	public ResponseEntity<Artikl> insertArtikl(@RequestBody Artikl artikl) {
		if (!artiklRepository.existsById(artikl.getId())) {
			artiklRepository.save(artikl);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	// update
	@PutMapping("artikl")
	public ResponseEntity<Artikl> updateArtikl(@RequestBody Artikl artikl) {
		if (!artiklRepository.existsById(artikl.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		artiklRepository.save(artikl);
		return new ResponseEntity<>(HttpStatus.OK);
	}
			
}
