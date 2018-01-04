package org.mycodegen.generator;

import org.mycodegen.ModelDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseCodeGenerator extends AbstractGenerator {

	private void mergeTemplateByPackageName(String fileName, String packageName) {
		Map map = new HashMap();
		map.put("packageName", packageName);
		generate(fileName + ".ftl", map, "src/" + package2path(packageName)
				+ "/" + fileName);
	}

	public void generateOnce(List modelDefinitions) {
		String packageName = null;
		if (modelDefinitions.size() > 0)
			packageName = ((ModelDefinition) modelDefinitions.get(0)).getPackageName();
		mergeTemplateByPackageName("model/BaseObject.java", packageName);
		mergeTemplateByPackageName("model/ResultPage.java", packageName);
		mergeTemplateByPackageName("dao/BaseDAO.java", packageName);
		mergeTemplateByPackageName("dao/BaseDAOImpl.java", packageName);
		mergeTemplateByPackageName("service/BaseManager.java", packageName);
		mergeTemplateByPackageName("service/BaseManagerImpl.java", packageName);
	}
}
