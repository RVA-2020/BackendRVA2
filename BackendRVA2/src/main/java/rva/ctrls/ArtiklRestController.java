package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Artikl;
import rva.repos.ArtiklRepository;

@RestController
public class ArtiklRestController {

	@Autowired
	private ArtiklRepository artiklRepository;
	
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
			
}
