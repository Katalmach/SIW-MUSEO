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

import it.uniroma3.siw.spring.controller.validator.CuratoreValidator;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.service.CuratoreService;

@Controller
public class CuratoreController {
	
	@Autowired
	private CuratoreService curatoreService;
	
    @Autowired
    private CuratoreValidator curatoreValidator;
        
    @RequestMapping(value="/admin/curatore", method = RequestMethod.GET)
    public String addCuratore(Model model) {
    	model.addAttribute("curatore", new Curatore());
        return "curatoreForm";
    }

    @RequestMapping(value = "/curatore/{id}", method = RequestMethod.GET)
    public String getCuratore(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("curatore", this.curatoreService.curatorePerId(id));
    	return "curatore";
    }

    @RequestMapping(value = "/curatore", method = RequestMethod.GET)
    public String getCuratori(Model model) {
    		model.addAttribute("curatori", this.curatoreService.tutti());
    		return "curatori";
    }
    
    @RequestMapping(value = "/admin/curatore", method = RequestMethod.POST)
    public String addCuratore(@ModelAttribute("curatore") Curatore curatore, 
    									Model model, BindingResult bindingResult) {
    	this.curatoreValidator.validate(curatore, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.curatoreService.inserisci(curatore);
            model.addAttribute("curatori", this.curatoreService.tutti());
            return "curatori";
        }
        return "curatoreForm";
    }
}
