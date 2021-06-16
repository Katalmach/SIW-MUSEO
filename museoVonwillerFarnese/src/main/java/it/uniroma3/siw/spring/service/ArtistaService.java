package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.repository.ArtistaRepository;

@Service
public class ArtistaService {
	
	@Autowired
	private ArtistaRepository artistaRepository; 
	
	@Transactional
	public Artista inserisci(Artista artista) {
		return artistaRepository.save(artista);
	}

	@Transactional
	public void elimina(Artista artista) {
		artistaRepository.delete(artista);
	}

	
	@Transactional
	public List<Artista> tutti() {
		return (List<Artista>) artistaRepository.findAll();
	}

	@Transactional
	public Artista artistaPerId(Long id) {
		Optional<Artista> optional = artistaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Artista artista) {
		Optional<Artista> artisti = this.artistaRepository.findByNomeAndCognome(artista.getNome(),artista.getCognome());
		if (artisti.isPresent())
			return true;
		else 
			return false;
	}
	
	@Transactional
	 public Artista artistaPerCognome(String oggettoDaCercare) {
		Optional<Artista> optional = artistaRepository.findByCognome(oggettoDaCercare);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExistsByCognome(String oggettoDaCercare) {
		Optional<Artista> artisti = this.artistaRepository.findByNome(oggettoDaCercare);
		if (artisti.isPresent())
			return true;
		else 
			return false;
	
	}
	
	@Transactional
	public Artista artistaPerNome(String oggettoDaCercare) {
		Optional<Artista> artisti = this.artistaRepository.findByNome(oggettoDaCercare);
		if (artisti.isPresent())
			return artisti.get();
		else 
			return null;

	}

	@Transactional
	public boolean alreadyExistsByNome(String oggettoDaCercare) {
		Optional<Artista> artisti = this.artistaRepository.findByNome(oggettoDaCercare);
		if (artisti.isPresent())
			return true;
		else 
			return false;
	}

	public Artista artistaPerNomeECognome(String nome, String cognome) {
		Optional<Artista> optional = this.artistaRepository.findByNomeAndCognome("nome","cognome");
		if (optional.isPresent())
			return optional.get();
		else 
			return null;

	}
}
