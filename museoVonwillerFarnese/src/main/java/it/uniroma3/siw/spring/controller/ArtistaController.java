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

import it.uniroma3.siw.spring.controller.validator.ArtistaValidator;
import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.service.ArtistaService;

@Controller
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;
	
    @Autowired
    private ArtistaValidator artistaValidator;

        
    @RequestMapping(value="/admin/artista", method = RequestMethod.GET)
    public String addArtista(Model model) {
    	model.addAttribute("artista", new Artista());
        return "artistaForm";
    }
    
    @RequestMapping(value = "/eliminaArtista/{id}", method = RequestMethod.POST)
    public String eliminaArtista(@PathVariable("id") Long id, Model model) {
    	this.artistaService.artistaPerId(id).setOpere(null);
    	this.artistaService.elimina(this.artistaService.artistaPerId(id));
    	model.addAttribute("artisti", this.artistaService.tutti());
    	
    	return "artisti";
    }

    @RequestMapping(value = "/artista/{id}", method = RequestMethod.GET)
    public String getArtista(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("artista", this.artistaService.artistaPerId(id));
    	return "artista";
    }
    

    @RequestMapping(value = "/admin/artisti", method = RequestMethod.GET)
    public String getArtistiAdmin(Model model) {
    		model.addAttribute("artisti", this.artistaService.tutti());
    		return "/admin/artisti";
    }

    @RequestMapping(value = "/artista", method = RequestMethod.GET)
    public String getArtisti(Model model) {
    		model.addAttribute("artisti", this.artistaService.tutti());
    		return "artisti";
    }
    
    @RequestMapping(value = "/admin/artista", method = RequestMethod.POST)
    public String addArtista(@ModelAttribute("artista") Artista artista, 
    									Model model, BindingResult bindingResult) {
    	this.artistaValidator.validate(artista, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.artistaService.inserisci(artista);
            model.addAttribute("artisti", this.artistaService.tutti());
            return "artisti";
        }
        return "artistaForm";
    }
    
	@RequestMapping(value = "/admin/modificaArtista/{id}", method = RequestMethod.POST)
	public String modificaArtista(@PathVariable("id")Long id,@ModelAttribute("artista") Artista artista,
			Model model, BindingResult bindingResult) {
		this.artistaService.elimina(artista);
		this.artistaValidator.validate(artista, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.artistaService.inserisci(artista);
			model.addAttribute("artisti", this.artistaService.tutti());
			return "/admin/artisti";
		}
		return "/admin/modificaArtista";
	}

	@RequestMapping(value="/admin/modificaArtista/{id}", method = RequestMethod.GET)
	public String modificaArtista(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artista", this.artistaService.artistaPerId(id));
		model.addAttribute("artisti", this.artistaService.tutti());
		return "/admin/modificaArtista";
	}
}
