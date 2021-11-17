package dz.acs.webflow.activation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages="dz.acs.webflow.activation")
@Import(value={
		DataAccessConfig.class,
		WebMvcConfig.class,
		WebFlowConfig.class
	})
public class AppConfig {

}
