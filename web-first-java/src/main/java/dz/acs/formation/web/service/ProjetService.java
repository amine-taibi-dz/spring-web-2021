package dz.acs.formation.web.service;

import java.util.List;

import dz.acs.formation.web.model.Projet;
/**
 * ProjetService interface
 * @author ataibi
 *
 */
public interface ProjetService {
	/**
	 * chargerProjets
	 * @return list des projets !! (TODO par page ! offset et page)
	 */
	List<Projet> chargerProjets();
	/**
	 * nouveauProjet
	 * @param projet
	 */
	public Projet nouveauProjet(Projet projet) ;
	/**
	 * chercherParId
	 * @param id projet
	 * @return projet trouvé
	 */
	Projet chercherParId(Long id);
	/**
	 * supprimerParId
	 * @param id projet 
	 * @return id supprimer
	 */
	Long supprimerParId(Long id);
	
	/**
	 * mise à jour
	 * @param projet
	 * @return Projet
	 */
	Projet mettreAjour(Projet projet);
}
