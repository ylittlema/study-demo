<#list modelDefinition.labels?if_exists?keys as propertyName>
${propertyName}=${propertyName?cap_first}
</#list>