package dz.acs.formation.web.service;

import java.util.List;

import dz.acs.formation.web.model.Projet;

public interface ProjetService {
	List<Projet> chargerProjets();
	public void addProjet(Projet projet) ;
}
