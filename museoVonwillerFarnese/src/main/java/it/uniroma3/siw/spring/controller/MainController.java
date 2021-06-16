package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CuratoreService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class MainController {

	@Autowired
	private CuratoreService curatoreService;

	@Autowired
	private ArtistaService artistaService;

	@Autowired
	private OperaService operaService;

	@Autowired
	private CollezioneService collezioneService;

	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = {"/about", "about"}, method = RequestMethod.GET)
	public String about(Model model) {
		return "about";
	}

	@RequestMapping(value = {"/ricercaFallita", "ricercaFallta"}, method = RequestMethod.GET)
	public String riceraFallita(Model model) {
		return "ricercaFallita";
	}


	@RequestMapping(value = "/museo", method = RequestMethod.POST)
	public String cercaInMuseo(@RequestParam String oggettoDaCercare, Model model) {
		model.addAttribute(this.operaService.tutti());
		model.addAttribute(this.collezioneService.tutti());
		model.addAttribute(this.artistaService.tutti());
		model.addAttribute(this.curatoreService.tutti());

		if(this.operaService.alreadyExistsByTitolo(oggettoDaCercare)){
			model.addAttribute("opera",this.operaService.operaPerTitolo(oggettoDaCercare));
			return "opera";
		}

		else if(this.collezioneService.alreadyExistsByNome(oggettoDaCercare)){
			model.addAttribute("collezione", this.collezioneService.collezionePerNome(oggettoDaCercare));
			return "collezione";
		}

		else if(this.curatoreService.alreadyExistsByNome(oggettoDaCercare)){
			model.addAttribute("curatore",this.curatoreService.curatorePerNome(oggettoDaCercare));
			return "curatore";
		}

		else if(this.curatoreService.alreadyExistsByCognome(oggettoDaCercare)){
			model.addAttribute("curatore",this.curatoreService.curatorePerCognome(oggettoDaCercare));
			return "curatore";
		}

		else if(this.artistaService.alreadyExistsByNome(oggettoDaCercare)){
			model.addAttribute("artista",this.artistaService.artistaPerNome(oggettoDaCercare)); 
			return "artista";
		}

		else if(this.artistaService.alreadyExistsByCognome(oggettoDaCercare)){
			model.addAttribute("artista",this.artistaService.artistaPerCognome(oggettoDaCercare)); 
			return "artista";
		}
		
		return "ricercaFallita";
	}
}