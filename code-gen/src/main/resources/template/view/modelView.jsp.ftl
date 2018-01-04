<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/webwork" prefix="ww" %>
<table>
<#list modelDefinition.properties?if_exists?keys as propertyName>
<#if !modelDefinition.notInUIs?if_exists?seq_contains(propertyName)>
<tr>
<td><ww:property value="getText('${propertyName}')"/></td>
<td><ww:property value="${modelDefinition.className?uncap_first}.${propertyName}"/></td>
</tr>
</#if>
</#list>
</table>