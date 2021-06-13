package it.uniroma3.siw.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Collezione;

public interface CollezioneRepository extends CrudRepository<Collezione,Long> {
	
	public Optional<Collezione> findByNome(String nome);

}
