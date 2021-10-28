package dz.acs.formation.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import lombok.extern.java.Log;
/**
 * ProjetServiceImpl clazz
 * @author ataibi
 *
 */
@Service
@Log
@Data
@Getter
@Setter
@NoArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class ProjetServiceImpl implements ProjetService {

	@Autowired
	private ProjetRepository projetRepository;
	
	@Override
	public List<Projet> chargerProjets() {
		log.info("ProjetServiceImpl.chargerProjets...");
		List<Projet> res = projetRepository.findAll();
		return res;
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	@Override
	public void addProjet(Projet projet) {
		log.info("ProjetServiceImpl.addProjet...");
		boolean res = projetRepository.add(projet);
		
		
	}

}
