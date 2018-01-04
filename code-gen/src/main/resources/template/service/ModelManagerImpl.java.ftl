package ${modelDefinition.packageName}.service;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import ${modelDefinition.packageName}.dao.${modelDefinition.className}DAO;
import ${modelDefinition.packageName}.model.${modelDefinition.className};

public class ${modelDefinition.className}ManagerImpl extends BaseManagerImpl implements ${modelDefinition.className}Manager {

	public ${modelDefinition.className} get${modelDefinition.className}ById(String id) {
		if (StringUtils.isBlank(id))
			return null;
		return (${modelDefinition.className}) get(${modelDefinition.className}.class, id);
	}
	
	public ${modelDefinition.className} get${modelDefinition.className}ByName(String name) {
		if (StringUtils.isBlank(name))
			return null;
		return ((${modelDefinition.className}DAO) dao).get${modelDefinition.className}ByName(name);
	}
	
	public List getAll${modelDefinition.className}(){
		return ((${modelDefinition.className}DAO) dao).getAll${modelDefinition.className}();
	}
}
