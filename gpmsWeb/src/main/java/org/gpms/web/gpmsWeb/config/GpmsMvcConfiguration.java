package org.gpms.web.gpmsWeb.config;

import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 
 * @author narenda.kumar
 * 
 */
@Configuration
@EnableWebMvc
public class GpmsMvcConfiguration extends WebMvcConfigurerAdapter {

	private static final Logger logger = Logger
			.getLogger(GpmsMvcConfiguration.class);

	/**
	 * 
	 * @return
	 */
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/**
	 * 
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
		registry.addResourceHandler("/images/**").addResourceLocations(
				"/images/");
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource result = new ResourceBundleMessageSource();
		String[] basenames = { "messages" };
		result.setBasenames(basenames);

		if (logger.isDebugEnabled()) {
			logger.debug("  basenames " + basenames);
		}

		return result;
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor result = new LocaleChangeInterceptor();
		result.setParamName("lang");
		if (logger.isDebugEnabled()) {
			logger.debug("  Language param Name " + result.getParamName());
		}

		return result;

	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver result = new SessionLocaleResolver();
		result.setDefaultLocale(Locale.ENGLISH);
		if (logger.isDebugEnabled()) {
			logger.debug("  SessionLocaleResolver " + result);
		}
		return result;
	}

	/**
	 * 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	/**
	 * 
	 * @return
	 */
	@Bean(name = "simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();

		Properties mappings = new Properties();

		r.setExceptionMappings(mappings);
		r.setDefaultErrorView("error");
		r.setExceptionAttribute("exception");
		r.setWarnLogCategory("example.MvcLogger");
		return r;
	}
}
