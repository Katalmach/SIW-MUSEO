package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Collezione {

	// attributi
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String nome;
	@Column(length = 2000)
	private String descrizione;
	
	// associazioni
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE,CascadeType.MERGE})
	private Curatore curatore;
	
	
	@OneToMany(mappedBy = "collezione")
	private List<Opera> opere;

	// costruttori
	public Collezione() {
	}
	
	public Collezione(String nome, String descrizione) {

		this.nome = nome;
		this.descrizione = descrizione;
	}

	// metodi getter e setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Curatore getCuratore() {
		return curatore;
	}

	public void setCuratore(Curatore curatore) {
		this.curatore = curatore;
	}


	public List<Opera> getOpere() {
		return opere;
	}

	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}
}
