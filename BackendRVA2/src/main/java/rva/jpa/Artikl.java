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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
@Entity
@NamedQuery(name="Artikl.findAll", query="SELECT a FROM Artikl a")
public class Artikl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ARTIKL_ID_GENERATOR", sequenceName="ARTIKL_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARTIKL_ID_GENERATOR")
	private Integer id;

	private String naziv;

	private String proizvodjac;

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