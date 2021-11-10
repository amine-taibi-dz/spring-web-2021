package dz.acs.formation.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.acs.formation.web.model.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long>{
}
