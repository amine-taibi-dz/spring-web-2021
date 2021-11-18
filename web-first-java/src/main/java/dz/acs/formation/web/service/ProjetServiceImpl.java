package dz.acs.formation.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.acs.formation.web.dao.ProjetRepository;
import dz.acs.formation.web.model.Projet;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
/**
 * ProjetServiceImpl clazz
 * @author ataibi
 *
 */
@Service
@Log4j2
@Data
@Getter
@Setter
@NoArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class ProjetServiceImpl implements ProjetService {

	@Autowired
	private ProjetRepository projetRepository;
	
	@PreAuthorize("hasAnyRole('IT','ADMIN')")
	@Override
	public List<Projet> chargerProjets() {
		log.info("ProjetServiceImpl.chargerProjets...");
		List<Projet> res = projetRepository.findAll();
		return res;
	}
	
	
	@PreAuthorize("hasRole('IT')")
	@Transactional
	@Override
	public Projet nouveauProjet(Projet projet) {
		log.info("ProjetServiceImpl.addProjet...");
		Projet res = projetRepository.add(projet);
		return res;
	}


	@PreAuthorize("hasAnyRole('IT','ADMIN')")
	@Override
	public Projet chercherParId(Long id) {
		log.info("ProjetServiceImpl.chercherParId...");
		Projet res = projetRepository.findById(id);
		return res;
	}

	@PreAuthorize("hasRole('IT')")
	@Transactional
	@Override
	public Long supprimerParId(Long id) {
		log.info("ProjetServiceImpl.supprimerParId...");
		Long res =  projetRepository.deleteById(id);
		return res;
	}

	//@PreAuthorize("hasRole('IT')")
	@Secured("ROLE_IT")
	@Transactional
	@Override
	public Projet mettreAjour(Projet projet) {
		log.info("ProjetServiceImpl.mettreAjour...");
		Projet res =  projetRepository.update(projet);
		return res;
	}

}
