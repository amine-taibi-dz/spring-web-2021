package dz.acs.webflow.activation;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("activationService")
@Repository
public class JpaActivationService implements ActivationService, Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional
	public boolean activateUser(UserMe userMe) {
		em.persist(userMe);
		return userMe.getIdent()!=null;
	}

	

}