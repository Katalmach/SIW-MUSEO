package it.uniroma3.siw.spring.model;



import java.awt.Image;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Opera {

	// attributi
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String titolo;
	
	@Column(nullable = false)
	private Integer anno;
	
	@Column(length = 2000)
	private String descrizione;
	
	//@Column(nullable=false)
	//private Image image;
	
	// associazioni
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name ="artista_id")
	private Artista artista;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
	@JoinColumn(name = "collezione_id")
	private Collezione collezione;

	// costruttori
	public Opera() {
	}
	
	public Opera(String titolo, Integer anno, String descrizione,Image image) {
		
		this.titolo = titolo;
		this.anno = anno;
	//	this.image= image;
		this.descrizione = descrizione;
	}

	// metodi getter e setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Image getImage() {
//		return image;
//	}
//
//	public void setImage(Image image) {
//		this.image = image;
//	}

	public Collezione getCollezione() {
		return collezione;
	}

	public void setCollezione(Collezione collezione) {
		this.collezione = collezione;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public Collezione getCollezioni() {
		return collezione;
	}

	public void setCollezioni(Collezione collezione) {
		this.collezione = collezione;
	}

	// nuovo toString
	@Override
	public String toString() {
		return "Opera [id=" + id + ", titolo=" + titolo + ", anno=" + anno + ", descrizione=" + descrizione
				+ ", artista=" + this.getArtista().getNome() + " " + this.getArtista().getCognome() 
				+ ", collezione=" + this.getCollezioni().getNome() + "]";
	}	
}
