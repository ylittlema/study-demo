package org.mycodegen.generator;

import java.util.HashMap;
import java.util.Map;

import org.mycodegen.ModelDefinition;

public class ServiceGenerator extends AbstractGenerator {

	public void generateOneByOne(ModelDefinition md) {
		String templateFileName = "service/ModelManager.java.ftl";
		Map propMap = new HashMap();
		propMap.put("modelDefinition", md);
		String fileName = "src/" + package2path(md.getPackageName())
				+ "/service/" + md.getClassName() + "Manager.java";
		generate(templateFileName, propMap, fileName);
		templateFileName = "service/ModelManagerImpl.java.ftl";
		fileName = "src/" + package2path(md.getPackageName()) + "/service/"
				+ md.getClassName() + "ManagerImpl.java";
		generate(templateFileName, propMap, fileName);
	}
}
