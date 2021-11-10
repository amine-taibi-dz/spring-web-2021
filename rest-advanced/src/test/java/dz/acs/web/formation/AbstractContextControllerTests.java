package dz.acs.web.formation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import dz.acs.formation.web.config.AppConfig;
import dz.acs.formation.web.config.WebMvcConfig;
import dz.acs.formation.web.config.WebSecurityConfig;
import dz.acs.formation.web.config.WebSocketConfig;

@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfig.class,AppConfig.class,WebSecurityConfig.class,WebSocketConfig.class})
public class AbstractContextControllerTests {

	@Autowired
	protected WebApplicationContext wac;

}
