package dz.acs.web.formation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import dz.acs.formation.web.dao.ProjetRepository;
import dz.acs.formation.web.dao.UserRepository;
import dz.acs.formation.web.model.Projet;
import dz.acs.formation.web.model.Projet_;
import dz.acs.formation.web.model.User;
import dz.acs.formation.web.service.MyUserDetailsService;
/**
 * TestWebContoller
 * @author ataibi
 *
 */
@RunWith(SpringRunner.class)
public class TestJpaData  extends AbstractContextControllerTests{
	
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private ProjetRepository projetRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private MyUserDetailsService myUserDetailsService ;

	
	@Test
	public void testGetProjet() throws Exception {
		Projet sgc = entityManager.find(Projet.class, 1L);
		assertNotNull(sgc);
		assertEquals("bien", sgc.getRepoName(),"sgc");
	}
	@Test
	public void testGetProjets() {
		List<Projet> list = projetRepository.findAll();		
		assertNotNull(list);
		assertTrue(list.size()>0);
	}
	
	@Test
	public void testGetUsers() {
		List<User> list = userRepository.findAll();	
		//System.out.println(list);
		assertNotNull(list);
		assertTrue(list.size()>0);
	}
	
	@Test
	public void TestFindMesChosess() {
		List<User> list = userRepository.findMesChoses("PDG");	
		System.err.println(list);
		assertNotNull(list);
		assertTrue(list.size()>0);
	}
	
	@Test
	public void testGetProjetsByName() {
		List<Projet> list = projetRepository.findByNameContainingOrderByRepoNameAsc("Sig");
		//System.err.println(list);
		assertNotNull(list);
		assertTrue(list.size()>0);
	}
	
	@Test
	public void testGetProjetsByNameSpecification() {
		Specification<Projet> all = 
				Specification.where((projet, cq, cb) ->cb.equal(projet.get(Projet_.id),
						projet.get(Projet_.id)));
		Specification<Projet> repoNameCrit = all;
		repoNameCrit = (projet, cq, cb) -> cb.equal(projet.get(Projet_.repoName),"sgc");
		
		List<Projet> list = projetRepository.findAll(Specification.where(all).and(repoNameCrit));
		System.err.println(list);
		assertNotNull(list);
		assertTrue(list.size()>0);
	}
	
	
	
	@Test
	public void testGetUserByName() {
		assertThrows("NON EXISTANT", 
				UsernameNotFoundException.class, 
				(()->myUserDetailsService.loadUserByUsername("test")));
	   
	}


}
