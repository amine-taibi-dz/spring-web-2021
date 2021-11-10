package dz.acs.formation.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.acs.formation.web.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	//SELECT u FROM User u WHERE u.username = ?1"
	public User findOneByUsername(String username);
}
