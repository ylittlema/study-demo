package ${modelDefinition.packageName}.service;

import java.util.List;
import ${modelDefinition.packageName}.model.${modelDefinition.className};

public interface ${modelDefinition.className}Manager extends BaseManager {

	public ${modelDefinition.className} get${modelDefinition.className}ById(String id);

	public ${modelDefinition.className} get${modelDefinition.className}ByName(String name);
	
	public List getAll${modelDefinition.className}();

}
