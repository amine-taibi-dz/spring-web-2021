package dz.acs.formation.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dz.acs.formation.web.model.Projet;
import dz.acs.formation.web.service.ProjetService;
import dz.acs.formation.web.validation.ProjetValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
/**
 * HomeController
 * @author ataibi
 *
 */
@Controller
@RequestMapping("/formation")
@Log4j2
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormationController {

	@Autowired
	private ProjetService projetService;
	
	@Autowired
	private ProjetValidator projetValidator;
	
	@Value("${test.demo:false}")
	private boolean testDemo;
	
	@RequestMapping("session")
	public String welcomeSessionReq(HttpServletRequest req,HttpServletResponse resp) {
		log.info("session request");
		Integer nb = Integer.parseInt(req.getParameter("nb")==null?"100":req.getParameter("nb"));
		resp.addHeader("Number", String.valueOf(nb));
		
		//do something very painfull and evil !!
		//projetService.addProjet(new Projet(null, "SECU", "SECU", "HTTP://WWW.SECU.DZ.AC", false));
		if(testDemo) {
			throw new IllegalArgumentException("Une exception pour tester la page error");
		}else {
			return "session";	
		}
		
		
	}
	@RequestMapping("/session1")
	public String welcomeSession(ModelMap mm) {
		log.info("session model");
		mm.addAttribute("values", "1,2,3");
		return "session";
		
	}
	
	@RequestMapping("/projets")
	public String listProjet(ModelMap mm) {
		log.info("les projets");
		List<Projet> projets = projetService.chargerProjets();
		mm.addAttribute("projets", projets);
		return "list_projets";		
	}
	

	@RequestMapping(value = "/projets/addProjet", method = RequestMethod.GET)
	public String viewAddPage(ModelMap mm) {
		log.info("aller à la page addProjet");
		Projet projet = new Projet();
		mm.addAttribute("projet", projet);
		return "new_projet";		
	}
	
	@RequestMapping(value = "/projets/addProjet", method = RequestMethod.POST)
	
    public String addProjet(/*@ProjetValid*/ @Valid @ModelAttribute("projet") final Projet projet, final BindingResult result, final ModelMap modelMap ) {
		projetValidator.validate(projet, result);
        if (result.hasErrors()) {
            return "new_projet";
        }
        projetService.nouveauProjet(projet);
        modelMap.addAttribute("projet", projet);
        
        return "redirect:/formation/projets";
    }
}
