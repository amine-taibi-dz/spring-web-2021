package dz.acs.formation.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dz.acs.formation.web.model.Projet;
import dz.acs.formation.web.validation.ProjetValidator;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
@Data
@Log
@NoArgsConstructor
@Repository
@Getter
@Setter
public class ProjetRepositorylmpl implements ProjetRepository{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
    ProjetValidator validator;

	@Override
	public List<Projet> findAll() {
		TypedQuery<Projet> query = entityManager.createQuery("SELECT p FROM Projet p", Projet.class);
		List<Projet> res = query.getResultList();
		log.info(res.toString());
		return res;
	}

	@Override
	public boolean add(Projet projet) {
		entityManager.persist(projet);
		return projet.getId()==null;
	}

}
