package dz.acs.formation.web.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * sprng security filter 'springSecurityFilterChain'
 * @author ataibi
 *
 */
public class SecurityWebAppInitializerConfig extends AbstractSecurityWebApplicationInitializer{

	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}
}
