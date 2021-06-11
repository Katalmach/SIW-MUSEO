package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;

public interface OperaRepository extends CrudRepository<Opera,Long>{
	
	public List<Opera> findByArtista(Artista artista);

	public List<Opera> findByCollezione(Collezione collezione);
	
	public List<Opera> findByTitolo(String titolo);
	
	public List<Opera> findByAnno(int anno);
}
