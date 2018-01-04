package org.mycodegen.generator;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.mycodegen.BaseDirHelper;
import org.mycodegen.ConfigurationHelper;
import org.mycodegen.ModelDefinition;

import java.io.*;
import java.util.List;
import java.util.Map;

public abstract class AbstractGenerator implements Generator {

	protected void generate(String templateFileName, Map propMap, String fileName) {
		try {
			Template t = ConfigurationHelper.getConfiguration().getTemplate(templateFileName);
			fileName = BaseDirHelper.getBaseDir() + fileName;
			new File(fileName.substring(0, fileName.lastIndexOf("/"))).mkdirs();
			File afile = new File(fileName);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(afile), "UTF-8"));
			t.process(propMap, out);
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String package2path(String packageName) {
		return packageName.replace('.', '/');
	}

	protected String capFirst(String string) {
		String s = String.valueOf(string.charAt(0)).toUpperCase();
		s = s + string.substring(1);
		return s;
	}

	protected String uncapFirst(String string) {
		String s = String.valueOf(string.charAt(0)).toLowerCase();
		s = s + string.substring(1);
		return s;
	}

	public void generate(List modelDefinitions) {
		for (int i = 0; i < modelDefinitions.size(); i++)
			generateOneByOne((ModelDefinition) modelDefinitions.get(i));
		generateOnce(modelDefinitions);
	}

	public void generateOneByOne(ModelDefinition modelDefinition) {
	}

	public void generateOnce(List modelDefinitions) {
	}
}
