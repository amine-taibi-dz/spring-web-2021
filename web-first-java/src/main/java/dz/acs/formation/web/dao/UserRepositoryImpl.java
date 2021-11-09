package dz.acs.formation.web.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import dz.acs.formation.web.model.User;
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
public class UserRepositoryImpl implements UserRepository{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User findByUsername(String username) {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = ?1", User.class);
		query.setParameter(1, username);
		User user = query.getSingleResult();
		log.info(user.toString());
		return user;
	}

}
