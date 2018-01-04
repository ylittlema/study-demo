${'<@ww.form'} action="${modelDefinition.className?uncap_first}!save.action" method="post" validate="true">
	${'<#if'} ${modelDefinition.className?uncap_first}?exists&&!${modelDefinition.className?uncap_first}.new>
    ${'<@ww.hidden'}  name="${modelDefinition.className?uncap_first}.id"/>
    ${'</#if>'}
    <#list modelDefinition.properties?if_exists?keys as propertyName>
    <#if !modelDefinition.notInUIs?if_exists?seq_contains(propertyName)>
    ${'<@'}ww.<#if modelDefinition.properties[propertyName]?if_exists='boolean'>checkbox<#elseif propertyName?lower_case?contains('password')>password<#else>textfield</#if> label="${"$"+"{action.getText('"+propertyName+"')}"}" name="${modelDefinition.className?uncap_first}.${propertyName}" <#if modelDefinition.requireds?if_exists?seq_contains(propertyName)>required="true"</#if>/>
	</#if>
	</#list>
    ${'<@ww.submit'} value="Save"/>
    ${'<@ww.submit'} name="redirect-action:${modelDefinition.className?uncap_first}" value="Cancel"/>
${'</@ww.form>'}
