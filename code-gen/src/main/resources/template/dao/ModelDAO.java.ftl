package ${modelDefinition.packageName}.dao;

import java.util.List;
import ${modelDefinition.packageName}.model.${modelDefinition.className};

public interface ${modelDefinition.className}DAO extends BaseDAO {

	public ${modelDefinition.className} get${modelDefinition.className}ByName(String name);
	
	public List getAll${modelDefinition.className}();

}
