package dz.acs.formation.web.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
    private ApplicationContext applicationContext;

	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {

		var templateResolver = new SpringResourceTemplateResolver();

		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("classpath:templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);

		return templateResolver;
	}
	
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
	    return new SpringSecurityDialect();
	}

	@Bean
	public SpringTemplateEngine templateEngine() {

		var templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		
		// sinon la balise sec: dans les html ne marchera pas
		templateEngine.addDialect(springSecurityDialect());
		
		return templateEngine;
	}

	@Bean
	public ViewResolver viewResolver() {

		var resolver = new ThymeleafViewResolver();
		var registry = new ViewResolverRegistry(null, applicationContext);

		resolver.setTemplateEngine(templateEngine());
		registry.viewResolver(resolver);

		return resolver;
	}

	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		var source = new ResourceBundleMessageSource();
		source.setBasenames("i18n/messages");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.FRENCH);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}	

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/").resourceChain(false);

	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/v1/**")
			.allowedOrigins("http://localhost:3000","http://localhost:4200")
			.allowedHeaders("*")
			.allowedMethods("*");
		
	}
	
	
}
