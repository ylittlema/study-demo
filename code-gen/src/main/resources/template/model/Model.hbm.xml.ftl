<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN" 
	"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">

<hibernate-mapping package="${modelDefinition.packageName}.model" default-lazy="true">
	
	<class name="${modelDefinition.className}" table="${modelDefinition.tableName}">
		<id name="id" unsaved-value="null">
			<generator class="uuid"/>
		</id>
		<#list modelDefinition.properties?if_exists?keys as propertyName>
    	<property name="${propertyName}" <#if modelDefinition.requireds?if_exists?seq_contains(propertyName)>not-null="true"</#if>/>
		</#list>
		<#list modelDefinition.manyToOne?if_exists?keys as mto>
		<many-to-one name="${mto}" column="${modelDefinition.manyToOne[mto]?uncap_first}Id"  fetch="join"/>
		</#list>
		<#list modelDefinition.oneToMany?if_exists?keys as otm>
   		<set name="${otm}" cascade="none" inverse="true" lazy="true">
			<key>
				<column name="${modelDefinition.oneToMany[otm]?uncap_first}Id" />
			</key>
			<one-to-many class="${modelDefinition.oneToMany[otm]}" />
		</set>
		</#list>
		<#list modelDefinition.manyToMany?if_exists?keys as mtm>
	    <set name="${mtm}" table="${modelDefinition.className?if_exists}_${modelDefinition.manyToMany[mtm]?uncap_first}" <#if modelDefinition.inverses?if_exists?seq_contains(mtm)>inverse="true"</#if> cascade="none" 
			lazy="true">
			<key column="${modelDefinition.className?if_exists}Id"/>
			<many-to-many class="${modelDefinition.manyToMany[mtm]}" column="${modelDefinition.manyToMany[mtm]?uncap_first}Id" 
				outer-join="auto" />
		</set>  
		</#list>
	</class>
</hibernate-mapping>