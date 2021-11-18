package dz.acs.formation.web.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		var source = new ResourceBundleMessageSource();
		source.setBasenames("i18n/messages");
		source.setUseCodeAsDefaultMessage(true);
		return source;
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
   		registry.addInterceptor(themeChangeInterceptor());
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public ThemeChangeInterceptor themeChangeInterceptor() {
		ThemeChangeInterceptor themeInterceptor = new ThemeChangeInterceptor();
   		themeInterceptor.setParamName("mytheme");
		return themeInterceptor;
	}

	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/pages/", ".jsp");
		return viewResolver;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/static/**")
			.addResourceLocations("/resources/");

	}
}
