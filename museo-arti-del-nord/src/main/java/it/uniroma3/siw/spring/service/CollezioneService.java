package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.repository.CollezioneRepository;

@Service
public class CollezioneService {
	
	@Autowired
	private CollezioneRepository collezioneRepository; 
	
	@Transactional
	public Collezione inserisci(Collezione collezione) {
		return collezioneRepository.save(collezione);
	}

	@Transactional
	public void elimina(Collezione collezione) {
		this.collezioneRepository.delete(collezione);
	}

	
	@Transactional
	public List<Collezione> tutti() {
		return (List<Collezione>) collezioneRepository.findAll();
	}

	@Transactional
	public Collezione collezionePerId(Long id) {
		Optional<Collezione> optional = collezioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Collezione collezione) {
		Optional<Collezione> collezioni = this.collezioneRepository.findByNome(collezione.getNome());
		if (collezioni.isPresent())
			return true;
		else 
			return false;
	}

	@Transactional
	public Object collezionePerNome(String oggettoDaCercare) {
		Optional<Collezione> collezioni = collezioneRepository.findByNome(oggettoDaCercare);
		if (collezioni.isPresent())
			return collezioni;
		else 
			return null;
		
	}

	public boolean alreadyExistsByNome(String oggettoDaCercare) {
		Optional<Collezione> collezioni = this.collezioneRepository.findByNome(oggettoDaCercare);
		if (collezioni.isPresent())
			return true;
		else 
			return false;
	}
}
