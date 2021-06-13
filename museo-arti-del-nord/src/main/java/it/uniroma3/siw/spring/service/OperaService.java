package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.OperaRepository;

@Service
public class OperaService {
	
	@Autowired
	private OperaRepository operaRepository; 
	

	
	@Transactional
	public Opera inserisci(Opera opera) {
		
		return operaRepository.save(opera);
	}

	@Transactional
	public List<Opera> tutti() {
		return (List<Opera>) operaRepository.findAll();
	}

	@Transactional
	public Opera operaPerId(Long id) {
		Optional<Opera> optional = operaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Opera opera) {
		Optional<Opera> opere = this.operaRepository.findByTitolo(opera.getTitolo());
		if (opere.isPresent())
			return true;
		else 
			return false;
	}
	
	@Transactional
	public void elimina(Opera opera) {
		operaRepository.delete(opera);
	}

	@Transactional
	public Opera operaPerCollezione(Collezione collezione) {
		Optional<Opera> opere = operaRepository.findByCollezione(collezione);
		if (opere.isPresent())
			return opere.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExistsByTitolo(String nomeOpera) {
		Optional<Opera> opere = this.operaRepository.findByTitolo(nomeOpera);
		if (opere.isPresent())
			return true;
		else 
			return false;
		
	}

	public Opera operaPerTitolo(String nomeOpera) {
		Optional<Opera> optional = operaRepository.findByTitolo(nomeOpera);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	public Optional<Opera> operePerArtista(Artista artistaPerNomeECognome) {
		Optional<Opera> opere = operaRepository.findByArtista(artistaPerNomeECognome);
		if(opere.isPresent())
			return opere;
		return null;
	}
}
