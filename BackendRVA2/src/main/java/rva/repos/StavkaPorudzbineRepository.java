package rva.repos;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rva.jpa.Porudzbina;
import rva.jpa.StavkaPorudzbine;

public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine, Integer> {

	Collection<StavkaPorudzbine> findByPorudzbina(Porudzbina p);
	Collection<StavkaPorudzbine> findByCenaLessThanOrderById(BigDecimal cena);
	
	/*
	 * Svaka stavka porudžbine unutar jedne porudžbine ima svoj redni broj. Potrebno je da se stavke
	 * porudžbine numerišu od 1, pa nadalje. Da bi se to postiglo deklarisana je metoda nextRBR kojoj
	 * se prosleđuje ID poružbine, a čijim pozivom će biti izvršen SQL upit naveden u okviru anotacije
	 * @Query
	 * Objašnjenje:
	 * - vrednost ?1 i uzrazu predstavlja vrednost koja će biti prosleđena na osnovu vrednosti
	 * porudžbinaId iz deklaracije metode
	 * - coleasce(val1, val2, ...) - vraća prvu not null vrednost iz prosleđenog izraza
	 * - max(redni_broj)+1 - vraća nejveću vrednost za redni_broj, uvećanu za jedan
	 * - coalesce(max(redni_broj)+1, 1) - ukoliko ne postoji ni jedna stavka porudžbine za određenu
	 * porudžbinu, vrednost max(redni_broj)+1 će biti null, što znači da izraz ima vrednost
	 * coalesce(null, 1) i kao rezultat izvršavanja biće prosleđena vrednost 1 što je i potrebno za
	 * prvu stavku porudžbine unutar nove porudžbine
	 */
	
	@Query(value = "select coalesce(max(redni_broj)+1, 1) from stavka_porudzbine where porudzbina = ?1", nativeQuery = true)
	Integer nextRBr(Integer porudzbinaId);
	
}
