
package ru.project.notes.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("ru.project.notes")
@EnableWebMvc
public class NotesConfig implements WebMvcConfigurer {
	
	private final ApplicationContext applicationContext;
	
	@Autowired
	public NotesConfig(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	@Bean
	public SpringResourceTemplateResolver templateResolver() {//Создаёт правила нахождения и расположения шаблонизатаров
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		return templateResolver;
	}
	
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());//получение шаблонов
		templateEngine.setEnableSpringELCompiler(true);//запуск
		return templateEngine;
	}
	public void configureViewResolvers(ViewResolverRegistry registry) {//передаём движок отображения страниц
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		registry.viewResolver(resolver);
	}

}