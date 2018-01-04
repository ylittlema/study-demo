<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/webwork" prefix="ww" %>
<ww:form action="${modelDefinition.className?uncap_first}!save.action" method="post" validate="true">
	<ww:if test="%{${modelDefinition.className?uncap_first}!=null&&!${modelDefinition.className?uncap_first}.isNew()}">
    <ww:hidden  name="${modelDefinition.className?uncap_first}.id"/>
    </ww:if>
    <#list modelDefinition.properties?if_exists?keys as propertyName>
    <#if !modelDefinition.notInUIs?if_exists?seq_contains(propertyName)>
    <ww:<#if modelDefinition.properties[propertyName]?if_exists='boolean'>checkbox<#elseif propertyName?lower_case?contains('password')>password<#else>textfield</#if> label="${"%{getText('"+propertyName+"')}"}" name="${modelDefinition.className?uncap_first}.${propertyName}" <#if modelDefinition.requireds?if_exists?seq_contains(propertyName)>required="true"</#if>/>
	</#if>
	</#list>
    <ww:submit value="Save"/>
    <ww:submit name="redirect-action:${modelDefinition.className?uncap_first}" value="Cancel"/>
</ww:form>


