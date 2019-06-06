package org.springframework.web.servlet.view.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateException;

public class ViewException extends TemplateException {

	public ViewException(String description, Environment env) {
		super(description, env);
	}
}
