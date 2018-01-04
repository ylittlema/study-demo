package org.mycodegen.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringGenerator extends AbstractGenerator {

	public void generateOnce(List modelDefinitions) {
		String templateFileName = "applicationContext-service.xml.ftl";
		Map propMap = new HashMap();
		propMap.put("modelDefinitions", modelDefinitions);
		String fileName = "src/applicationContext-service.xml";
		generate(templateFileName, propMap, fileName);
		templateFileName = "applicationContext-web.xml.ftl";
		fileName = "src/applicationContext-web.xml";
		generate(templateFileName, propMap, fileName);
		templateFileName = "web.xml";
		fileName = "webapp/WEB-INF/web.xml";
		generate(templateFileName, propMap, fileName);
		templateFileName = "log4j.properties";
		fileName = "webapp/WEB-INF/log4j.properties";
		generate(templateFileName, propMap, fileName);
	}
}
