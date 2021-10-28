package dz.acs.web.formation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import dz.acs.formation.web.model.Projet;
import lombok.Data;
/**
 * TestWebContoller
 * @author ataibi
 *
 */
@RunWith(SpringRunner.class)
@Data
public class TestJpaData  extends AbstractContextControllerTests{
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@Test
	public void testGetProjet() throws Exception {
		Projet sgc = entityManager.find(Projet.class, 1L);
		assertNotNull(sgc);
		assertEquals("bien", sgc.getRepoName(),"sgc");
		

		
	}

}
