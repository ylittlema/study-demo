<#macro getterAndSetter propertyName propertyType>
  	public ${propertyType} <#if propertyType="boolean">is<#else>get</#if>${propertyName?cap_first}() {
		return ${propertyName};
	}
	public void set${propertyName?cap_first}(${propertyType} ${propertyName}) {
		this.${propertyName} = ${propertyName};
	}
</#macro>  

<#macro getterAndSetterForCollection propertyName propertyType>
  	public Collection get${propertyName?cap_first}() {
		return ${propertyName};
	}
	public void set${propertyName?cap_first}(Collection ${propertyName}) {
		this.${propertyName} = ${propertyName};
	}
	public void add${propertyType}(${propertyType} object) {
		this.${propertyName}.add(object);
	}
	public void remove${propertyType}(${propertyType} object) {
		this.${propertyName}.remove(object);
	}
</#macro>  

package ${modelDefinition.packageName}.model;

import java.io.Serializable;
<#if modelDefinition.properties?if_exists?values?seq_contains("Date")>
import java.util.Date;
</#if>
<#if (modelDefinition.oneToMany?if_exists?size>0)||(modelDefinition.manyToMany?if_exists?size>0) >
import java.util.Collection;
import java.util.HashSet;
</#if>

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ${modelDefinition.className} extends BaseObject implements Serializable {

<#list modelDefinition.properties?if_exists?keys as propertyName>
    private ${modelDefinition.properties[propertyName]} ${propertyName};
    
</#list>
<#list modelDefinition.manyToOne?if_exists?keys as mto>
    private ${modelDefinition.manyToOne[mto]} ${mto};
    
</#list>
<#list modelDefinition.oneToMany?if_exists?keys as otm>
    private Collection ${otm} = new HashSet();
    
</#list>
<#list modelDefinition.manyToMany?if_exists?keys as mtm>
    private Collection ${mtm} = new HashSet();
    
</#list>
<#list modelDefinition.properties?if_exists?keys as propertyName>
    <@getterAndSetter propertyName="${propertyName}" propertyType="${modelDefinition.properties[propertyName]}"/>
</#list>

<#list modelDefinition.manyToOne?if_exists?keys as propertyName>
    <@getterAndSetter propertyName="${propertyName}" propertyType="${modelDefinition.manyToOne[propertyName]}"/>
</#list>

<#list modelDefinition.oneToMany?if_exists?keys as propertyName>
    <@getterAndSetterForCollection propertyName="${propertyName}" propertyType="${modelDefinition.oneToMany[propertyName]}"/>
</#list>

<#list modelDefinition.manyToMany?if_exists?keys as propertyName>
    <@getterAndSetterForCollection propertyName="${propertyName}" propertyType="${modelDefinition.manyToMany[propertyName]}"/>
</#list>


	public int hashCode() {
		return new HashCodeBuilder().append(this.id).toHashCode();
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof ${modelDefinition.className}))
			return false;
		${modelDefinition.className} that = (${modelDefinition.className}) obj;
		return new EqualsBuilder().append(this.id, that.id).isEquals();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this).toString();
	}

}

