package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;

public interface OperaRepository extends CrudRepository<Opera,Long>{
	
	public Optional<Opera> findByArtista(Artista artista);

	public Optional<Opera> findByTitolo(String titolo);
	
	public List<Opera> findByAnno(int anno);

	public List<Opera> findByCollezione(Collezione collezione);
	
	@Query("FROM Opera ORDER BY anno ASC")
	public List<Opera> findByCollezioneOrderByAnno(Collezione collezione);

	@Query("FROM Opera ORDER BY artista.cognome, artista.nome")
	public List<Opera> findByCollezioneOrderByNomeAndCognome(Collezione collezione);
}
