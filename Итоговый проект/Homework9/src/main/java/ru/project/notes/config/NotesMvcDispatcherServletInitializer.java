package ru.project.notes.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;

public class NotesMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer { 

	@Override 
	protected Class<?>[] getRootConfigClasses() { 
		return null; 
	}

	@Override 
	protected Class<?>[] getServletConfigClasses() { 
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
		aContext.addFilter("hiddenHttpMethodFilter", (Filter) new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");

	}
}

