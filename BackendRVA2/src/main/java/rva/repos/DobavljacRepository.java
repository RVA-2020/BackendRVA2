package rva.repos;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

import rva.jpa.Dobavljac;

public interface DobavljacRepository extends JpaRepository<Dobavljac, Integer>{
	
	Collection<Dobavljac> findByNazivContainingIgnoreCase(String naziv);
	
}
