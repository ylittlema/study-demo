<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE validators PUBLIC "-//OpenSymphony Group//XWork Validator 1.0.2//EN" 
	"http://www.opensymphony.com/xwork/xwork-validator-1.0.2.dtd">
<validators>
	<#list modelDefinition.properties?if_exists?keys as propertyName>
	<#if !modelDefinition.notInUIs?if_exists?seq_contains(propertyName)>
	<#if propertyName?lower_case?contains('email')>
    <field name="${modelDefinition.className?uncap_first}.${propertyName}">
    	<#if modelDefinition.requireds?if_exists?seq_contains(propertyName)>
		<field-validator type="requiredstring">
			<message>enter ${propertyName}!</message>
		</field-validator>
    	</#if>
		<field-validator type="email">
			<message>${propertyName} must be a email!</message>
		</field-validator>
	</field>
	<#elseif propertyName?lower_case?contains('date')||propertyName?lower_case?contains('time')>
    <field name="${modelDefinition.className?uncap_first}.${propertyName}">
    	<#if modelDefinition.requireds?if_exists?seq_contains(propertyName)>
		<field-validator type="requiredstring">
			<message>enter ${propertyName}!</message>
		</field-validator>
    	</#if>
		<field-validator type="date">
			<message>${propertyName} must be a date!</message>
		</field-validator>
	</field>
	<#else>
	<#if modelDefinition.requireds?if_exists?seq_contains(propertyName)>
	<field name="${modelDefinition.className?uncap_first}.${propertyName}">
		<field-validator type="requiredstring">
			<message>enter ${propertyName}!</message>
		</field-validator>
	</field>
    </#if>
	</#if>
	</#if>
	</#list>
</validators>