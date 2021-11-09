package dz.acs.formation.web.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import dz.acs.formation.web.model.Projet;
import dz.acs.formation.web.service.ProjetService;
import dz.acs.formation.web.validation.ProjetValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
/**
 * ProjetResource restfull ..
 * @author ataibi
 *
 */
@RestController
@RequestMapping("/api/v1/projets")
@Log4j2
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjetResource {
	@Autowired
	private ProjetService projetService;

	@Autowired
	private ProjetValidator projetValidator;

	@GetMapping(path = "/{idProjet}",produces = "application/json")
	public ResponseEntity<Projet> getProjet(@PathVariable Long idProjet) {
		Projet res = projetService.chercherParId(idProjet);
		if(res==null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.ok(res);	
		}
	}
	
	@GetMapping(path = "/",produces = "application/json")
	public ResponseEntity<List<Projet>> getProjets() {
		List<Projet> res = projetService.chargerProjets();
		if(res==null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.ok(res);	
		}
	}
	
	@PostMapping(path = "/",produces = "application/json",consumes= "application/json")
	public ResponseEntity<Projet> addProjet(@RequestBody @Valid Projet projet,UriComponentsBuilder uriComponentsBuilder) {
		
		Projet res = projetService.nouveauProjet(projet);

        UriComponents uriComponents = uriComponentsBuilder
        			.path("/projets/{id}")
        			.buildAndExpand(res.getId());
        
        URI location = uriComponents.toUri();
        return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/{idProjet}",produces = "application/json")
	public ResponseEntity<Long> deleteProjet(@PathVariable Long idProjet) {
		Long id = projetService.supprimerParId(idProjet);
        if (id==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
	}

}
