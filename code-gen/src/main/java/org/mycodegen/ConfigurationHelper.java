package org.mycodegen;

import freemarker.template.Configuration;

public class ConfigurationHelper {

	private static Configuration cfg = null;

	public static Configuration getConfiguration() {
		if (null == cfg) {
			cfg = new Configuration();
			cfg.setClassForTemplateLoading(ConfigurationHelper.class,
					"/template");
			cfg.setDefaultEncoding("UTF-8");
		}
		return cfg;
	}
}
