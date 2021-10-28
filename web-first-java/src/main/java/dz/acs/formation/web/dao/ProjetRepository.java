package dz.acs.formation.web.dao;

import java.util.List;

import dz.acs.formation.web.model.Projet;

public interface ProjetRepository {

	List<Projet> findAll();
	public boolean add(Projet projet) ;
	

}
