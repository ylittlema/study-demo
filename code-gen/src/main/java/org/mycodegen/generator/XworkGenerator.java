package org.mycodegen.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XworkGenerator extends AbstractGenerator {

	public void generateOnce(List modelDefinitions) {
		String templateFileName = "xwork.xml.ftl";
		Map propMap = new HashMap();
		propMap.put("modelDefinitions", modelDefinitions);
		String fileName = "src/xwork.xml";
		generate(templateFileName, propMap, fileName);
		templateFileName = "validators.xml";
		fileName = "src/validators.xml";
		generate(templateFileName, propMap, fileName);
		templateFileName = "webwork.properties";
		fileName = "src/webwork.properties";
		generate(templateFileName, propMap, fileName);
	}
}
