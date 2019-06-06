package org.springframework.web.servlet.view.freemarker;

import java.io.Writer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerExceptionHandler implements TemplateExceptionHandler{
	 private static final Logger log =LogManager.getLogger(FreemarkerExceptionHandler.class);
	 public void handleTemplateException(TemplateException te, Environment env,Writer out) throws TemplateException  {
		 	log.warn("[Freemarker Error: " + te.getMessage() + "]");
		 		throw new ViewException("freemarker error",env);
	 	}

}
