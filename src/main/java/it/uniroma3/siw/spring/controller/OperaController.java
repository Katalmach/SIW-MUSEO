package it.uniroma3.siw.spring.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.controller.validator.OperaValidator;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class OperaController {



	@Autowired	
	private ArtistaService artistaService;

	@Autowired
	private OperaService operaService;

	@Autowired
	private OperaValidator operaValidator;

	@Autowired
	private CollezioneService collezioneService;



	@RequestMapping(value="/admin/opera", method = RequestMethod.GET)
	public String addOpera(Model model) {
		model.addAttribute("opera", new Opera());
		model.addAttribute("artisti", this.artistaService.tutti());
		model.addAttribute("collezioni", this.collezioneService.tutti());

		return "operaForm";
	}

	@RequestMapping(value = "/opera/{id}", method = RequestMethod.GET)
	public String getOpera(@PathVariable("id") Long id, Model model) {
		model.addAttribute("opera", this.operaService.operaPerId(id));
		return "opera";
	}

	@RequestMapping(value = "/eliminaOpera/{id}", method = RequestMethod.POST)
	public String eliminaOpera(@PathVariable("id") Long id, Model model) {
		this.operaService.elimina(this.operaService.operaPerId(id));
		model.addAttribute("opere", this.operaService.tutti());
		return "/admin/opere";
	}

	@RequestMapping(value = "/opera", method = RequestMethod.GET)
	public String getOpere(Model model) {
		model.addAttribute("opere", this.operaService.tutti());
		return "opere";
	}

	@RequestMapping(value = "/admin/opere", method = RequestMethod.GET)
	public String getOpereAdmin(Model model) {
		model.addAttribute("opere", this.operaService.tutti());
		return "/admin/opere";
	}

	@RequestMapping(value = "/admin/opera", method = RequestMethod.POST)
	public String addOpera(@RequestParam Long artistaSelezionato,
			@RequestParam Long collezioneSelezionata,
			@ModelAttribute("opera") Opera opera, Model model, BindingResult bindingResult) {
		this.operaValidator.validate(opera, bindingResult);
		if(!bindingResult.hasErrors()) {
			opera.setArtista(this.artistaService.artistaPerId(artistaSelezionato));
			opera.setCollezione(this.collezioneService.collezionePerId(collezioneSelezionata));
			this.operaService.inserisci(opera);
			model.addAttribute("opere", this.operaService.tutti());
			return "opere";
		}
		return "operaForm";
	}

	@RequestMapping(value = "/admin/modificaOpera/{id}", method = RequestMethod.POST)
	public String modificaOpera(@PathVariable("id")Long id,@ModelAttribute("opera") Opera opera,@RequestParam Long artistaSelezionato,
			@RequestParam Long collezioneSelezionata, Model model, BindingResult bindingResult) {
		this.operaService.elimina(opera);
		this.operaValidator.validate(opera, bindingResult);
		if(!bindingResult.hasErrors()) {
				opera.setArtista(this.artistaService.artistaPerId(artistaSelezionato));
				opera.setCollezione(this.collezioneService.collezionePerId(collezioneSelezionata));
			this.operaService.inserisci(opera);
			model.addAttribute("opere", this.operaService.tutti());
			return "/admin/opere";
		}
		return "/admin/modificaOpera";
	}

	@RequestMapping(value="/admin/modificaOpera/{id}", method = RequestMethod.GET)
	public String modificaOpera(@PathVariable("id") Long id, Model model) {
		model.addAttribute("opera", this.operaService.operaPerId(id));
		model.addAttribute("artisti", this.artistaService.tutti());
		model.addAttribute("collezioni", this.collezioneService.tutti());

		return "/admin/modificaOpera";
	}

}
