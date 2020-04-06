package rva.repos;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Integer> {

	Collection<Porudzbina> findByPlacenoTrue();
	
}
