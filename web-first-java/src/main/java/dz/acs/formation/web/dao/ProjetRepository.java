package dz.acs.formation.web.dao;

import java.util.List;

import dz.acs.formation.web.model.Projet;

public interface ProjetRepository {

	/**
	 * findAll
	 * @return
	 */
	List<Projet> findAll();
	
	/**
	 * add
	 * @param projet
	 * @return Projet
	 */
	Projet add(Projet projet) ;
	
	/**
	 * findById
	 * @param id
	 * @return
	 */
	Projet findById(Long id);
	
	/**
	 * deleteById
	 * @param id
	 * @return
	 */
	Long deleteById(Long id);
	
	/**
	 * Mise à jour du projet
	 * @param pojet
	 * @return Projet
	 */
	Projet update(Projet projet);
	
	

}
