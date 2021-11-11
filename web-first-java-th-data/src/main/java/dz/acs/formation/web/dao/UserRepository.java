package dz.acs.formation.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dz.acs.formation.web.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	/***SELECT u FROM User u WHERE u.username = ?1"****/
	public User findOneByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.name LIKE %?1%")
	public List<User> findMesChoses(String patternName);
	
	
}
