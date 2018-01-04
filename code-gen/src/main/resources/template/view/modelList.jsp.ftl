<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/webwork" prefix="ww" %>
<a href="${modelDefinition.className?uncap_first}!input.action">Create a new ${modelDefinition.className}</a>
<table>
    <tr>
        <#list modelDefinition.properties?if_exists?keys as propertyName>
        <#if !modelDefinition.notInUIs?if_exists?seq_contains(propertyName)>
		<th><ww:property value="getText('${propertyName}')"/></th>
		</#if>
		</#list>
        <th>Actions</th>
    </tr>
<ww:iterator value="resultPage.result">
    <tr>
        <#list modelDefinition.properties?if_exists?keys as propertyName>
        <#if !modelDefinition.notInUIs?if_exists?seq_contains(propertyName)>
		<td><ww:property value="${propertyName}"/></td>
		</#if>
		</#list>
        <td>
        <a href="${modelDefinition.className?uncap_first}!view.action?${modelDefinition.className?uncap_first}.id=<ww:property value="id"/>">view</a>
        <a href="${modelDefinition.className?uncap_first}!input.action?${modelDefinition.className?uncap_first}.id=<ww:property value="id"/>">edit</a>
        <a href="${modelDefinition.className?uncap_first}!delete.action?${modelDefinition.className?uncap_first}.id=<ww:property value="id"/>">delete</a>
        </td>
    </tr>
</ww:iterator>
</table>

<table>
	<tr>
		<td><ww:if test="%{!resultPage.isFirst()}">
			<a
				href='<ww:url action="user">
				<ww:param name="resultPage.currentPage" value="%{resultPage.currentPage-1}" />
				<ww:param name="resultPage.pageSize" value="%{resultPage.pageSize}" />
				</ww:url>'>
			Previous</a>
		</ww:if><ww:else>
				Previous
				</ww:else> <ww:if test="%{!resultPage.isLast()}">
			<a
				href='<ww:url action="user">
				<ww:param name="resultPage.currentPage" value="%{resultPage.currentPage+1}" />
				<ww:param name="resultPage.pageSize" value="%{resultPage.pageSize}" />	
				</ww:url>'>
			Next </a>
		</ww:if> <ww:else>
				Next
				</ww:else></td>
		<td>Current Page:<ww:property value="resultPage.currentPage" /> Total Page:<ww:property
			value="resultPage.totalPage" /> TotalRecord:<ww:property
			value="resultPage.totalRecord" /></td>
		<td><ww:form action="${modelDefinition.className?uncap_first}" method="GET">
			<ww:textfield label="Records per Page" name="resultPage.pageSize" size="3" />
			<ww:textfield label="Goto" name="resultPage.currentPage" size="3" />
			<ww:submit />
		</ww:form></td>
	</tr>
</table>


