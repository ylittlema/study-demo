package org.mycodegen.generator;

import java.util.HashMap;
import java.util.Map;

import org.mycodegen.ModelDefinition;

public class DAOGenerator extends AbstractGenerator {

	public void generateOneByOne(ModelDefinition md) {
		String templateFileName = "dao/ModelDAO.java.ftl";
		Map propMap = new HashMap();
		propMap.put("modelDefinition", md);
		String fileName = "src/" + package2path(md.getPackageName()) + "/dao/"
				+ md.getClassName() + "DAO.java";
		generate(templateFileName, propMap, fileName);
		templateFileName = "dao/ModelDAOImpl.java.ftl";
		fileName = "src/" + package2path(md.getPackageName()) + "/dao/"
				+ md.getClassName() + "DAOImpl.java";
		generate(templateFileName, propMap, fileName);
	}
}
