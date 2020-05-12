package rva.ctrls;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Porudzbina;
import rva.jpa.StavkaPorudzbine;
import rva.repos.PorudzbinaRepository;
import rva.repos.StavkaPorudzbineRepository;

@RestController
@CrossOrigin
public class StavkaPorudzbineRestController {

	@Autowired
	private StavkaPorudzbineRepository stavkaPorudzbineRepository;
	
	@Autowired
	private PorudzbinaRepository porudzbinaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("stavkaPorudzbine")
	public Collection<StavkaPorudzbine> getStavkePorudzbine(){
		return stavkaPorudzbineRepository.findAll();
	}
	
	@GetMapping("stavkaPorudzbine/{id}")
	public ResponseEntity<StavkaPorudzbine> getStavkaPorudzbine(@PathVariable("id") Integer id){
		StavkaPorudzbine stavkaPorudzbine = stavkaPorudzbineRepository.getOne(id);
		return new ResponseEntity<StavkaPorudzbine>(stavkaPorudzbine, HttpStatus.OK);
	}
	
	@GetMapping("stavkeZaPorudzbinaId/{id}")
	public Collection<StavkaPorudzbine> stavkaPoPorudzbiniId(@PathVariable("id") int id){
		Porudzbina p = porudzbinaRepository.getOne(id);
		return stavkaPorudzbineRepository.findByPorudzbina(p);
	}
	
	@GetMapping("stavkaPorudzbineCena/{cena}")
	public Collection<StavkaPorudzbine> getStavkaPorudzbineCena(@PathVariable("cena") BigDecimal cena){
		return stavkaPorudzbineRepository.findByCenaLessThanOrderById(cena);
	}
	
	// insert
	@PostMapping("stavkaPorudzbine")
	public ResponseEntity<StavkaPorudzbine> insertStavkaPorudzbine(@RequestBody StavkaPorudzbine stavkaPorudzbine) {
		if (stavkaPorudzbineRepository.existsById(stavkaPorudzbine.getId()))
			return new ResponseEntity<StavkaPorudzbine>(HttpStatus.CONFLICT);
		stavkaPorudzbine.setRedniBroj(stavkaPorudzbineRepository.nextRBr(stavkaPorudzbine.getPorudzbina().getId()));
		stavkaPorudzbineRepository.save(stavkaPorudzbine);
		return new ResponseEntity<StavkaPorudzbine>(HttpStatus.OK);
	}
	
	// update
	@PutMapping("stavkaPorudzbine")
	public ResponseEntity<StavkaPorudzbine> updateStavkaPorudzbine(@RequestBody StavkaPorudzbine stavkaPorudzbine) {
		if (!stavkaPorudzbineRepository.existsById(stavkaPorudzbine.getId()))
			return new ResponseEntity<StavkaPorudzbine>(HttpStatus.NO_CONTENT);
		stavkaPorudzbineRepository.save(stavkaPorudzbine);
		return new ResponseEntity<StavkaPorudzbine>(HttpStatus.OK);
	}
	
	@DeleteMapping("stavkaPorudzbine/{id}")
	public ResponseEntity<StavkaPorudzbine> deleteStavkaPorudzbine(@PathVariable("id") Integer id){
		
		if(!stavkaPorudzbineRepository.existsById(id))
			return new ResponseEntity<StavkaPorudzbine>(HttpStatus.NO_CONTENT);
		stavkaPorudzbineRepository.deleteById(id);
		
		if (id == -100) {  
            jdbcTemplate.execute("INSERT INTO stavka_porudzbine (\"id\", \"redni_broj\", \"kolicina\", \"jedinica_mere\", \"cena\", \"porudzbina\", \"artikl\") "
                    + "VALUES ('-100', '100', '1', 'kom', '1', '1', '1')");
        }
		
		return new ResponseEntity<StavkaPorudzbine>(HttpStatus.OK);
	} 
	
}
