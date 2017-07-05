package br.com.casadocodigo.loja.conf;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.interceptors.HistoricoAcessoInterceptor;

@Configuration
@EnableWebMvc
// @ComponentScan(basePackageClasses = { HomeController.class, ProdutoDAO.class
// })
@EnableCaching
@ComponentScan("br.com.casadocodigo")
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");

		// define que todos os beans do spring irão aparecer na jsp
		// resolver.setExposeContextBeansAsAttributes(true);

		// define o bean do spring "carrinhoCompras" irá aparecer na jsp
		resolver.setExposedContextBeanNames("carrinhoCompras");

		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("UTF-8");
		bundle.setCacheSeconds(1);
		return bundle;
	}

	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(
				true);

		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}

	@Bean
	public OpenEntityManagerInViewInterceptor getOpenEntityManagerInViewInterceptor() {
		return new OpenEntityManagerInViewInterceptor();
	}

	@Bean
	public HistoricoAcessoInterceptor getHistoricoAcessoInterceptor() {
		return new HistoricoAcessoInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addWebRequestInterceptor(getOpenEntityManagerInViewInterceptor());
		registry.addWebRequestInterceptor(getHistoricoAcessoInterceptor());
	}

	/*
	 * Habilita o RestTemplate. Caso haja alguma configuração de assinatura é
	 * aqui que devemos configurar.
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/* Habilita o uso de arquivos da pasta resources */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/*
	 * @Override public void addViewControllers(ViewControllerRegistry registry)
	 * { registry.addViewController("/login").setViewName("login");
	 * registry.setOrder(Ordered.HIGHEST_PRECEDENCE); }
	 */
	
	@Bean
	public CacheManager cacheManager(){
		return new ConcurrentMapCacheManager();
	}

}