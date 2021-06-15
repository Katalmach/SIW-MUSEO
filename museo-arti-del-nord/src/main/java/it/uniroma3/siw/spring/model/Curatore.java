package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"nome","cognome","email"}))
public class Curatore {
	
	// attributi
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	/*@Column(nullable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataDiNascita;
	
	@Column(nullable = false)
	private String luogoDiNascita;*/
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false, unique = true)
	private String numeroDiTelefono;
	
	// associazioni
	@OneToMany(mappedBy = "curatore")
	private List<Collezione> collezioni;
	
	// costruttori
	public Curatore() {
	}
	
//	public LocalDate getDataDiNascita() {
//		return dataDiNascita;
//	}
//
//	public void setDataDiNascita(LocalDate dataDiNascita) {
//		this.dataDiNascita = dataDiNascita;
//	}
//
//	public String getLuogoDiNascita() {
//		return luogoDiNascita;
//	}
//
//	public void setLuogoDiNascita(String luogoDiNascita) {
//		this.luogoDiNascita = luogoDiNascita;
//	}

	public Curatore(String nome, String cognome, LocalDate dataDiNascita, String luogoDiNascita,
			String email, String numeroDiTelefono) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.numeroDiTelefono = numeroDiTelefono;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumeroDiTelefono() {
		return numeroDiTelefono;
	}

	public void setNumeroDiTelefono(String numeroDiTelefono) {
		this.numeroDiTelefono = numeroDiTelefono;
	}

	public List<Collezione> getCollezioni() {
		return collezioni;
	}

	public void setCollezioni(List<Collezione> collezioni) {
		this.collezioni = collezioni;
	}


	// nuovo toString
	@Override
	public String toString() {
		return "Curatore [matricola=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", numeroDiTelefono="
				+ numeroDiTelefono + "]";
	}	
}