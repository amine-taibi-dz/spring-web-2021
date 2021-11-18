package dz.acs.web.formation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import dz.acs.formation.web.dao.ProjetRepository;
import dz.acs.formation.web.model.Projet;
import dz.acs.formation.web.service.ProjetService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * TestWebContoller
 * @author ataibi
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@Setter
@Getter
public class TestJpaData  extends AbstractContextControllerTests{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ProjetRepository projetRepository;
	@Autowired
	private ProjetService projetService;

	@Before
	public void setup() {
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("test", "password"));
	}
	
	@Test
	public void testGetProjet() throws Exception {
		Projet sgc = entityManager.find(Projet.class, 1L);
		assertNotNull(sgc);
		assertEquals("bien", sgc.getRepoName(),"sgc");
	}
	
	@Transactional
	@Test
	public void testUpdateProjetRepo() throws Exception {
		Projet sgc = entityManager.find(Projet.class, 1L);
		sgc.setRepoName("scg31");
		Projet sgc1 = projetRepository.update(sgc);
		assertNotNull(sgc1);
		assertEquals("bien", sgc.getRepoName(),"scg31");
	}
	
	@Transactional
	//@Commit
	@Test
	public void testUpdateProjetService() throws Exception {
		Projet sgc = entityManager.find(Projet.class, 1L);
		sgc.setRepoName("sgc-310");
		Projet sgc1 = projetService.mettreAjour(sgc);
		assertNotNull(sgc1);
		assertEquals("bien", sgc.getRepoName(),"sgc-310");
	}
	

	@Transactional
	//@Commit
	@Test
	public void testDeleteProjetService() throws Exception {
		Projet sgc = entityManager.find(Projet.class, 1L);		
		Long sgc1Id = projetService.supprimerParId(sgc.getId());
		assertNotNull(sgc1Id);		
	}
	
	

}
