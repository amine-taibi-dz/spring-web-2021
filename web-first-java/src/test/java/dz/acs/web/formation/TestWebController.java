package dz.acs.web.formation;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import dz.acs.formation.web.controller.AccessController;
import dz.acs.formation.web.model.Projet;
/**
 * TestWebContoller
 * @author ataibi
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@Rollback
//@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class TestWebController  extends AbstractContextControllerTests{

	private MockMvc mockMvc;

	//	@Autowired
	//	private FilterChainProxy springSecurityFilterChain;

	//	@BeforeClass
	//	public static void setUpBeforeClass() throws Exception {
	//	}
	//
	//	@AfterClass
	//	public static void tearDownAfterClass() throws Exception {
	//	}

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
		SecurityContextHolder.getContext().setAuthentication(
		        new UsernamePasswordAuthenticationToken("test", "password"));
		//this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain,"/*").build();

	}

	@Test
	public void mockMvcNotNull() throws Exception {
		assertNotNull(mockMvc);
	}

	@Test
	public void indexTest() throws Exception {

		mockMvc.perform(get("/index")).andDo(print())
		.andExpect(handler().handlerType(AccessController.class))
		.andExpect(handler().methodName("indexPage"))
		.andExpect(view().name("welcome"))
		.andExpect(forwardedUrl("/WEB-INF/pages/welcome.jsp"))
		.andExpect(status().isOk());
	}
	@Test
	public void session1View() throws Exception {
		/*.param("j_username", "admin").param("j_password", "password")*/
		this.mockMvc.perform(get("/formation/session1")).andDo(print())
		.andExpect(view().name(containsString("session")))
		.andExpect(model().attribute("values", "1,2,3"))
		.andExpect(model().size(1));
	}

	@Test
	public void sessionView() throws Exception {
		this.mockMvc.perform(get("/formation/session?nb=1001")).andDo(print())
		.andExpect(header().longValue("Number", 1001))
		.andExpect(view().name(containsString("session")));
	}


	@Test
	public void updateProjetGetView() throws Exception {
		this.mockMvc
		       .perform(get("/formation/projets/updateProjet/2"))
				.andDo(print())
				.andExpect(model().attribute("projet",instanceOf(Projet.class)))
				.andExpect(view().name(containsString("update_projet")));
	}
	
	@Test
	@Transactional
	@Rollback
	public void deleteProjetGetView() throws Exception {
		this.mockMvc
		       .perform(get("/formation/projets/deleteProjet/1"))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name(containsString("projets")));
	}
	
//	@Test
//	public void updateProjetPostView() throws Exception {
//		this.mockMvc
//		       .perform(post("/formation/projets/updateProjet/"))
//				.andDo(print())
//				.andExpect(model().attribute("projet",instanceOf(Projet.class)))
//				.andExpect(view().name(containsString("update_projet")));
//	}

}
