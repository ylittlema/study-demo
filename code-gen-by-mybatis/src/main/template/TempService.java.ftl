package ${packPath}service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${packPath}entity.${className}Entity;
import ${packPath}mapper.${className}Mapper;

/**
 * <br>
 * <b>功能：</b>${codeName} Service<br>
 * <b>作者：</b>siber.xu<br>
 * <b>日期：</b>${systemDate}<br>
 * <b>详细说明：</b>无<br>
 */
@Service("$!{lowerName}Service")
public class ${className}Service extends BaseService<${className}Entity> {
	
	private final static Logger log= Logger.getLogger(${className}Service.class);

	@Autowired
    private ${className}Mapper mapper;

		
	public ${className}Mapper getMapper() {
		return mapper;
	}

}
