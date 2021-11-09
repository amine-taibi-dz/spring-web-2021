package dz.acs.formation.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dz.acs.formation.web.model.Projet;
import dz.acs.formation.web.validation.ProjetValidator;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
@Data
@Log4j2
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
	public Projet add(@NotNull Projet projet) {
		entityManager.persist(projet);
		return projet;
	}

	@Override
	public Projet findById(@NotNull Long id) {
		Projet res = entityManager.find(Projet.class, id);
		log.info(res!=null?res.toString():"No content");
		return res;
	}

	@Override
	public Long deleteById(@NotNull Long id) {
		Projet prj = findById(id);
		log.info("deleting projet : "+id);		
		entityManager.remove(prj);
		return id;
	}

}
