package ${modelDefinition.packageName}.action;

import com.opensymphony.xwork.ActionSupport;
import org.hibernate.criterion.DetachedCriteria;
import org.apache.commons.lang.StringUtils;
import ${modelDefinition.packageName}.model.ResultPage;
import ${modelDefinition.packageName}.model.${modelDefinition.className};
import ${modelDefinition.packageName}.service.${modelDefinition.className}Manager;

public class ${modelDefinition.className}Action extends ActionSupport {

	${modelDefinition.className}Manager ${modelDefinition.className?uncap_first}Manager;

	${modelDefinition.className} ${modelDefinition.className?uncap_first};

	ResultPage resultPage;


	public void set${modelDefinition.className}Manager(${modelDefinition.className}Manager ${modelDefinition.className?uncap_first}Manager) {
		this.${modelDefinition.className?uncap_first}Manager = ${modelDefinition.className?uncap_first}Manager;
	}
	
	public ${modelDefinition.className}Manager get${modelDefinition.className}Manager() {
		return ${modelDefinition.className?uncap_first}Manager;
	}

	public String execute() {
		if (resultPage == null)
			resultPage = new ResultPage();
		DetachedCriteria dc = DetachedCriteria.forClass(${modelDefinition.className}.class);
		resultPage.setDetachedCriteria(dc);
		resultPage = ${modelDefinition.className?uncap_first}Manager.getResultPageByCriteria(resultPage);
		return "list";
	}
	
	public String query() {
		if (resultPage == null)
			resultPage = new ResultPage();
		DetachedCriteria dc = DetachedCriteria.forClass(${modelDefinition.className}.class);
		resultPage.setDetachedCriteria(dc);
		resultPage = ${modelDefinition.className?uncap_first}Manager.getResultPageByCriteria(resultPage);
		return "list";
	}

	public String save() {
		${modelDefinition.className?uncap_first}Manager.save(${modelDefinition.className?uncap_first});
		return SUCCESS;
	}

	public String input() {
		if (${modelDefinition.className?uncap_first} != null && StringUtils.isNotBlank(${modelDefinition.className?uncap_first}.getId()))
			${modelDefinition.className?uncap_first} = ${modelDefinition.className?uncap_first}Manager.get${modelDefinition.className}ById(${modelDefinition.className?uncap_first}.getId());
		return INPUT;
	}

	public String view() {
		${modelDefinition.className?uncap_first} = ${modelDefinition.className?uncap_first}Manager.get${modelDefinition.className}ById(${modelDefinition.className?uncap_first}.getId());
		return "view";
	}

	public String delete() {
		${modelDefinition.className?uncap_first} = ${modelDefinition.className?uncap_first}Manager.get${modelDefinition.className}ById(${modelDefinition.className?uncap_first}.getId());
		${modelDefinition.className?uncap_first}Manager.delete(${modelDefinition.className?uncap_first});
		return SUCCESS;
	}

	public ${modelDefinition.className} get${modelDefinition.className}() {
		return ${modelDefinition.className?uncap_first};
	}

	public void set${modelDefinition.className}(${modelDefinition.className} ${modelDefinition.className?uncap_first}) {
		this.${modelDefinition.className?uncap_first} = ${modelDefinition.className?uncap_first};
	}

	public ResultPage getResultPage() {
		return resultPage;
	}

	public void setResultPage(ResultPage resultPage) {
		this.resultPage = resultPage;
	}



}
