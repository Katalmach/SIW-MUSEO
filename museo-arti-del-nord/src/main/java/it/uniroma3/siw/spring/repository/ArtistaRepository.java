package it.uniroma3.siw.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Artista;

public interface ArtistaRepository extends CrudRepository<Artista,Long> {
	
	public Optional<Artista> findByNomeAndCognome(String nome, String cognome);

	public Optional<Artista> findByCognome(String oggettoDaCercare);

	public Optional<Artista> findByNome(String nome);

	
}
