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

import it.uniroma3.siw.spring.controller.validator.CollezioneValidator;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CuratoreService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class CollezioneController {
	
	@Autowired
	private CollezioneService collezioneService;
	
    @Autowired
    private CollezioneValidator collezioneValidator;
    
    @Autowired
	private CuratoreService curatoreService;

    @Autowired
	private OperaService operaService;

    @Autowired
	private ArtistaService artistaService;
        
    @RequestMapping(value="/admin/collezione", method = RequestMethod.GET)
    public String addCollezione(Model model) {
    	model.addAttribute("collezione", new Collezione());
    	model.addAttribute("curatori", this.curatoreService.tutti());
        return "collezioneForm";
    }

    @RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET)
    public String getCollezione(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("collezione", this.collezioneService.collezionePerId(id));
    	model.addAttribute("opere", this.operaService.operaPerCollezione(this.collezioneService.collezionePerId(id)));
    	model.addAttribute("artisti", this.artistaService.tutti());
    	return "collezione";
    }
    
    @RequestMapping(value = "/eliminaOpera/{id}", method = RequestMethod.POST)
    public String eliminaOpera(@PathVariable("id") Long id, Model model) {
    	Long idCollezione= this.operaService.operaPerId(id).getCollezione().getId();
    	model.addAttribute("collezione", this.collezioneService.collezionePerId(idCollezione));
    	
    	this.operaService.operaPerId(id).setArtista(null);
    	this.operaService.elimina(this.operaService.operaPerId(id));
    	return "collezione";
    }
    
    @RequestMapping(value = "/eliminaCollezione/{id}", method = RequestMethod.POST)
    public String eliminaCollezione(@PathVariable("id") Long id, Model model) {
    	this.collezioneService.elimina(this.collezioneService.collezionePerId(id));
    	return "collezione";
    }

    @RequestMapping(value = "/admin/collezioni", method = RequestMethod.GET)
    public String getCollezioniAdmin(Model model) {
    		model.addAttribute("collezioni", this.collezioneService.tutti());
    		return "/admin/collezioni";
    }
    @RequestMapping(value = "/collezione", method = RequestMethod.GET)
    public String getCollezioni(Model model) {
    		model.addAttribute("collezioni", this.collezioneService.tutti());
    		return "collezioni";
    }
    
    @RequestMapping(value = "/admin/collezione", method = RequestMethod.POST)
    public String addCollezione(@RequestParam Long curatoreSelezionato,
    		@ModelAttribute("collezione") Collezione collezione, 
    									Model model, BindingResult bindingResult) {
    	this.collezioneValidator.validate(collezione, bindingResult);
        if (!bindingResult.hasErrors()) {
        	collezione.setCuratore(this.curatoreService.curatorePerId(curatoreSelezionato));
        	this.collezioneService.inserisci(collezione);
        	model.addAttribute("collezioni", this.collezioneService.tutti());
            return "collezioni";
        }
        return "collezioneForm";
    }

}

