<#list modelDefinition.labels?if_exists?keys as propertyName>
${propertyName}=${modelDefinition.labels[propertyName]}
</#list>