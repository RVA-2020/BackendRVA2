package rva.repos;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Artikl;

public interface ArtiklRepository extends JpaRepository<Artikl, Integer>{

	Collection<Artikl> findByNazivContainingIgnoreCase(String naziv);
	
}
