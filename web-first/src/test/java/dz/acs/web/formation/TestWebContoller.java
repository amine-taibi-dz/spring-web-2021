package dz.acs.web.formation;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
/**
 * TestWebContoller
 * @author ataibi
 *
 */
@RunWith(SpringRunner.class)
public class TestWebContoller  extends AbstractContextControllerTests{
	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
	}
	
	@Test
	public void mockMvcNotNull() throws Exception {
		assertNotNull(mockMvc);
		
	}
	@Test
	public void session1View() throws Exception {
		this.mockMvc.perform(get("/formation/session1"))
				.andExpect(view().name(containsString("session")))
				.andExpect(model().attribute("values", "1,2,3"))
				.andExpect(model().size(1));
	}
	
	@Test
	public void sessionView() throws Exception {
		this.mockMvc.perform(get("/formation/session?nb=1001"))
				.andExpect(header().longValue("Number", 1001))
				.andExpect(view().name(containsString("session")));
	}

}
