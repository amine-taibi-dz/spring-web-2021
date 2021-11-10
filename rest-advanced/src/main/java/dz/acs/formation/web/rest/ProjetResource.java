package dz.acs.formation.web.rest;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import dz.acs.formation.web.dto.CommonResponse;
import dz.acs.formation.web.model.Projet;
import dz.acs.formation.web.service.ProjetService;
import dz.acs.formation.web.validation.ProjetValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**************** hateoas ....
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
*/
import lombok.extern.log4j.Log4j2;
/**
 * ProjetResource restfull ..
 * @author ataibi
 *
 */
@RestController
@RequestMapping("/api/v1/projets")
@Log4j2
@Data
@NoArgsConstructor
@AllArgsConstructor

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:4200"}, maxAge = 3600)
public class ProjetResource {
	@Autowired
	private ProjetService projetService;

	@Autowired
	private ProjetValidator projetValidator;

	/**
	 * getProjet
	 * @param idProjet
	 * @return   ResponseEntity<CommonResponse> 
	 */
	@GetMapping(path = "/{idProjet}",produces = "application/json")
	public ResponseEntity<CommonResponse> getProjet(@PathVariable Long idProjet) {
		
		Projet res = projetService.chercherParId(idProjet);
		CommonResponse commonResponse =null;
		if(res==null) {
			commonResponse = CommonResponse.builder()
					.timeStamp(LocalDateTime.now())
					.message( String.format(" L'id projet %d non trouvé",idProjet))
					.status(HttpStatus.NOT_FOUND)
					.statusCode(HttpStatus.NOT_FOUND.value())
					.build();
		}else {
			commonResponse = CommonResponse.builder()
					.timeStamp(LocalDateTime.now())
					.data(Map.of("projet",res))
					.message(String.format("L'id projet %d recupéré",idProjet))
					.status(HttpStatus.OK)
					.statusCode(HttpStatus.OK.value())
					.build();
		}
		log.info(commonResponse);
		return ResponseEntity.ok(commonResponse);
	}
	
	/**
	 * getProjets
	 * @return ResponseEntity<CommonResponse>
	 */
	@GetMapping(path = "/",produces = "application/json")
	public ResponseEntity<CommonResponse> getProjets() {
		List<Projet> res = projetService.chargerProjets();
		
		CommonResponse commonResponse = null;
		if(res==null) {
			commonResponse = CommonResponse.builder()
					.timeStamp(LocalDateTime.now())
					.message("Aucun projets")
					.status(HttpStatus.NO_CONTENT)
					.statusCode(HttpStatus.NO_CONTENT.value())
					.build();
		}else {
			commonResponse = CommonResponse.builder()
					.timeStamp(LocalDateTime.now())
					.data(Map.of("projets",res))
					.message("Liste projets disponible")
					.status(HttpStatus.OK)
					.statusCode(HttpStatus.OK.value())
					.build();
		}
		log.info(commonResponse);
		return ResponseEntity.ok(commonResponse);
	}
	/**
	 * addProjet
	 * @param projet
	 * @param uriComponentsBuilder
	 * @return  ResponseEntity<CommonResponse>
	 */
	@PostMapping(path = "/",produces = "application/json",consumes= "application/json")
	public ResponseEntity<CommonResponse>  addProjet(@RequestBody @Valid Projet projet,UriComponentsBuilder uriComponentsBuilder) {
		CommonResponse commonResponse =null;
		Projet res =null;
		try {
			res = projetService.nouveauProjet(projet);
			
			/*Link lienProjet = linkTo(methodOn(ProjetResource.class)
					  .getProjet(res.getId())).withRel("projet");				
			URI location = lienProjet.toUri();*/
			UriComponents uriComponents = uriComponentsBuilder
					.path("/api/v1/projets/{id}")
					.buildAndExpand(res.getId());
			URI location = uriComponents.toUri();
			commonResponse = CommonResponse.builder()
					.timeStamp(LocalDateTime.now())
					.data(Map.of("projet",res))
					.location(location.toString())
					.message(String.format("L'id projet %d créée",res.getId()))
					.status(HttpStatus.CREATED)
					.statusCode(HttpStatus.CREATED.value())
					.build();
		} catch (Exception e) {
			commonResponse = CommonResponse.builder()
					.timeStamp(LocalDateTime.now())
					.data(Map.of("projet",projet))
					.message( "Impossible de créer le projet")
					.detailedMessage(e.getMessage())
					.status(HttpStatus.NOT_ACCEPTABLE)
					.statusCode(HttpStatus.NOT_ACCEPTABLE.value())
					.build();
		}
		log.info(commonResponse);
		return ResponseEntity.ok(commonResponse);
	}
	
	/**
	 * updateProjet
	 * @param idProjet
	 * @param projet
	 * @param uriComponentsBuilder
	 * @return ResponseEntity<CommonResponse> 
	 */
	@PutMapping(path = "/{idProjet}",produces = "application/json",consumes= "application/json")
	public ResponseEntity<CommonResponse>  updateProjet(@PathVariable Long idProjet, @RequestBody @Valid Projet projet,UriComponentsBuilder uriComponentsBuilder) {
		CommonResponse commonResponse =null;
		Projet res =null;
		try {
			res = projetService.modifierProjet(projet);
			/*Link lienProjet = linkTo(methodOn(ProjetResource.class)
					  .getProjet(res.getId())).withRel("projet");				
			URI location = lienProjet.toUri();
			*/
			UriComponents uriComponents = uriComponentsBuilder
					.path("/api/v1/projets/{id}")
					.buildAndExpand(res.getId());
			URI location = uriComponents.toUri();
			commonResponse = CommonResponse.builder()
					.timeStamp(LocalDateTime.now())
					.data(Map.of("projet",res))
					.location(location.toString())
					.message(String.format("L'id projet %d modifié",res.getId()))
					.status(HttpStatus.ACCEPTED)
					.statusCode(HttpStatus.ACCEPTED.value())
					.build();
		} catch (Exception e) {
			commonResponse = CommonResponse.builder()
					.timeStamp(LocalDateTime.now())
					.data(Map.of("projet",projet))
					.message("Impossible de modifier le projet")
					.detailedMessage(e.getMessage())
					.status(HttpStatus.NOT_MODIFIED)
					.statusCode(HttpStatus.NOT_MODIFIED.value())
					.build();
		}
		log.info(commonResponse);
		return ResponseEntity.ok(commonResponse);
	}
	
	
	/**
	 * deleteProjet
	 * @param idProjet
	 * @return ResponseEntity<CommonResponse>
	 */
	@DeleteMapping(path = "/{idProjet}",produces = "application/json")
	public ResponseEntity<CommonResponse> deleteProjet(@PathVariable Long idProjet) {
		
		Projet res = projetService.chercherParId(idProjet);
		CommonResponse commonResponse = null;
		if(res==null) {
			commonResponse = CommonResponse.builder()
					.timeStamp(LocalDateTime.now())
					.message( String.format(" L'id projet %d non trouvé",idProjet))
					.status(HttpStatus.NOT_FOUND)
					.statusCode(HttpStatus.NOT_FOUND.value())
					.build();
		}else {
			Long resId = projetService.supprimerParId(idProjet);
			commonResponse = CommonResponse.builder()
					.timeStamp(LocalDateTime.now())
					.data(Map.of("deleted",resId))
					.message(String.format("L'id projet %d supprimé",idProjet))
					.status(HttpStatus.OK)
					.statusCode(HttpStatus.OK.value())
					.build();
		}
		log.info(commonResponse);
		return ResponseEntity.ok(commonResponse);
	}

}
