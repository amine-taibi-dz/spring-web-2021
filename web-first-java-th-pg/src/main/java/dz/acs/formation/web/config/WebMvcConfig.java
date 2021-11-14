package dz.acs.formation.web.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
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

	/*@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		//ViewResolverRegistry registry = new ViewResolverRegistry(null, applicationContext);

		resolver.setTemplateEngine(templateEngine());
		registry.viewResolver(resolver);		
	}*/
	
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
    public ThemeSource themeSource() {
    	ResourceBundleThemeSource source = new ResourceBundleThemeSource();
    	source.setBasenamePrefix("themes/");
    	return source;
    }
    @Bean 
    public ThemeResolver themeResolver(){
    	CookieThemeResolver resolver = new CookieThemeResolver();
    	resolver.setCookieMaxAge(2400);
    	resolver.setCookieName("mythemecookie");
    	resolver.setDefaultThemeName("theme1");
    	return resolver;
    }
    
    
    
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		var source = new ResourceBundleMessageSource();
		source.setBasenames("i18n/messages","i18n/formats");
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
		ThemeChangeInterceptor themeInterceptor = new ThemeChangeInterceptor();
   		themeInterceptor.setParamName("mytheme");
   		registry.addInterceptor(themeInterceptor);
	}	

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/").resourceChain(false);

	}
}
