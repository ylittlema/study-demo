package org.mycodegen;

import java.util.Map;
import java.util.Set;

public class ModelDefinition {
	private String packageName;

	private String className;

	private String tableName;

	private Map properties;// key为属性名字,value为属性类型

	private Map oneToMany;// key为属性名字,value为many方的类型

	private Map manyToOne;

	private Map manyToMany;

	private Map labels;

	private Set requireds;

	private Set inverses;

	private Set notInUIs;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Set getNotInUIs() {
		return notInUIs;
	}

	public void setNotInUIs(Set notInUIs) {
		this.notInUIs = notInUIs;
	}

	public Set getInverses() {
		return inverses;
	}

	public void setInverses(Set inverses) {
		this.inverses = inverses;
	}

	public Map getLabels() {
		return labels;
	}

	public void setLabels(Map labels) {
		this.labels = labels;
	}

	public Map getManyToMany() {
		return manyToMany;
	}

	public void setManyToMany(Map manyToMany) {
		this.manyToMany = manyToMany;
	}

	public Map getManyToOne() {
		return manyToOne;
	}

	public void setManyToOne(Map manyToOne) {
		this.manyToOne = manyToOne;
	}

	public Map getOneToMany() {
		return oneToMany;
	}

	public void setOneToMany(Map oneToMany) {
		this.oneToMany = oneToMany;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Map getProperties() {
		return properties;
	}

	public void setProperties(Map properties) {
		this.properties = properties;
	}

	public Set getRequireds() {
		return requireds;
	}

	public void setRequireds(Set requireds) {
		this.requireds = requireds;
	}

	public String getTableName() {
		if (tableName == null || "".equals(tableName))
			tableName = className.toLowerCase() + "s";
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
