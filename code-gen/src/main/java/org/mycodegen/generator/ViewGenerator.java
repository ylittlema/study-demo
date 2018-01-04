package org.mycodegen.generator;

import java.util.HashMap;
import java.util.Map;

import org.mycodegen.ModelDefinition;

public class ViewGenerator extends AbstractGenerator {

	public void generateOneByOne(ModelDefinition md) {
		Map propMap = new HashMap();
		propMap.put("modelDefinition", md);
		String templateFileName = "view/modelForm.ftl";
		String fileName = "webapp/WEB-INF/view/" + uncapFirst(md.getClassName()) + "Form.ftl";
		generate(templateFileName, propMap, fileName);
		templateFileName = "view/modelList.ftl";
		fileName = "webapp/WEB-INF/view/" + uncapFirst(md.getClassName()) + "List.ftl";
		generate(templateFileName, propMap, fileName);
		templateFileName = "view/modelView.ftl";
		fileName = "webapp/WEB-INF/view/" + uncapFirst(md.getClassName()) + "View.ftl";
		generate(templateFileName, propMap, fileName);
		
		templateFileName = "view/modelForm.jsp.ftl";
		fileName = "webapp/WEB-INF/view/" + uncapFirst(md.getClassName()) + "Form.jsp";
		generate(templateFileName, propMap, fileName);
		templateFileName = "view/modelList.jsp.ftl";
		fileName = "webapp/WEB-INF/view/" + uncapFirst(md.getClassName()) + "List.jsp";
		generate(templateFileName, propMap, fileName);
		templateFileName = "view/modelView.jsp.ftl";
		fileName = "webapp/WEB-INF/view/" + uncapFirst(md.getClassName()) + "View.jsp";
		generate(templateFileName, propMap, fileName);
	}
}
