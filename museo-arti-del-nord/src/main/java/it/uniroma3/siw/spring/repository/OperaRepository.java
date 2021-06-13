package it.uniroma3.siw.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;

public interface OperaRepository extends CrudRepository<Opera,Long>{
	
	public Optional<Opera> findByArtista(Artista artista);

	public Optional<Opera> findByCollezione(Collezione collezione);
	
	public Optional<Opera> findByTitolo(String titolo);
	
	public Optional<Opera> findByAnno(int anno);
}
