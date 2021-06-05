package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Artista {
	
	// attributi
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	
	private LocalDate dataDiNascita;
	@Column(nullable = false)
	
	private String luogoDiNascita;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	
	private LocalDate dataDiMorte;  // sono null se gli artisti
	private String luogoDiMorte;	// sono ancora in vita
	@Column(nullable = false)
	private String nazionalita;
	
	// associazioni
	@OneToMany(mappedBy = "artista")
	private List<Opera> opere;

	// costruttori
	public Artista() {
		this.opere = new ArrayList<>();
	}
	
	public Artista(String nome, String cognome, LocalDate dataDiNascita, String luogoDiNascita,
			LocalDate dataDiMorte, String luogoDiMorte, String nazionalita) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.luogoDiNascita = luogoDiNascita;
		this.dataDiMorte = dataDiMorte;
		this.luogoDiMorte = luogoDiMorte;
		this.nazionalita = nazionalita;
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getLuogoDiNascita() {
		return luogoDiNascita;
	}

	public void setLuogoDiNascita(String luogoDiNascita) {
		this.luogoDiNascita = luogoDiNascita;
	}

	public LocalDate getDataDiMorte() {
		return dataDiMorte;
	}

	public void setDataDiMorte(LocalDate dataDiMorte) {
		this.dataDiMorte = dataDiMorte;
	}

	public String getLuogoDiMorte() {
		return luogoDiMorte;
	}

	public void setLuogoDiMorte(String luogoDiMorte) {
		this.luogoDiMorte = luogoDiMorte;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public List<Opera> getOpere() {
		return opere;
	}

	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}
}
