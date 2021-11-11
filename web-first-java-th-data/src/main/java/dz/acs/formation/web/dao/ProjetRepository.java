package dz.acs.formation.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.acs.formation.web.model.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long>, JpaSpecificationExecutor<Projet>{
	
	//RAS	
	List<Projet> findByNameContainingOrderByRepoNameAsc (String patternName);
}
