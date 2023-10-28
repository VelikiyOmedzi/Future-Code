package ru.project.notes.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class NotesMvcDispatcherServletInitializer extends  AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {//инициализация DispatcherServlet
		return null;
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {//возвращение конфигурации
		return new Class[] {NotesConfig.class};
	}
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	public void onStartup(ServletContext aServletContext) throws ServletException {
		super.onStartup(aServletContext);
		registerHiddenFieldFilter(aServletContext);
	}
	
	private void registerHiddenFieldFilter(ServletContext aContext) {
		aContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null,true,"/*");
	}

}
