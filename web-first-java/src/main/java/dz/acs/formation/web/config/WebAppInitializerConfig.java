package dz.acs.formation.web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * WebAppInitializerConfig
 * @author ataibi
 *
 */
public class WebAppInitializerConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {AppConfig.class,WebMvcConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	

}
