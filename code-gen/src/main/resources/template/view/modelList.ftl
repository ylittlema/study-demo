<a href="${modelDefinition.className?uncap_first}!input.action">Create a new ${modelDefinition.className}</a>
<table>
    <tr>
        <#list modelDefinition.properties?if_exists?keys as propertyName>
        <#if !modelDefinition.notInUIs?if_exists?seq_contains(propertyName)>
		<th>${"$"+"{action.getText('"+propertyName+"')}"}</th>
		</#if>
		</#list>
        <th>Actions</th>
    </tr>
${'<#list resultPage.result as '+modelDefinition.className?uncap_first+'>'}
    <tr>
        <#list modelDefinition.properties?if_exists?keys as propertyName>
        <#if !modelDefinition.notInUIs?if_exists?seq_contains(propertyName)>
		<#if modelDefinition.properties[propertyName]?if_exists!='String'>
		<td>${'$'+'{'+modelDefinition.className?uncap_first+'.'+propertyName+'?if_exists?string}'}</td>
		<#else>
		<td>${'$'+'{'+modelDefinition.className?uncap_first+'.'+propertyName+'?if_exists}'}</td>
		</#if>
		</#if>
		</#list>
        <td>
        <a href="${modelDefinition.className?uncap_first}!view.action?${modelDefinition.className?uncap_first}.id=${'$'+'{'+modelDefinition.className?uncap_first+'.id}'}">view</a>
        <a href="${modelDefinition.className?uncap_first}!input.action?${modelDefinition.className?uncap_first}.id=${'$'+'{'+modelDefinition.className?uncap_first+'.id}'}">edit</a>
        <a href="${modelDefinition.className?uncap_first}!delete.action?${modelDefinition.className?uncap_first}.id=${'$'+'{'+modelDefinition.className?uncap_first+'.id}'}">delete</a>
        </td>
    </tr>
${'</#list>'}
</table>

<table>
	<tr>
		<td>${'<#if'} !resultPage.first>
			<a href='${'<@ww.url'} action="user">
				${'<@ww.param'} name="resultPage.currentPage" value="${'$'}{resultPage.currentPage-1}" />
				${'<@ww.param'} name="resultPage.pageSize" value="${'$'}{resultPage.pageSize}" />
				${'</@ww.url>'}'>Previous</a>
				${'<#else>'}
				Previous
				${'</#if>'}
				${'<#if'} !resultPage.last>
			<a href='${'<@ww.url'} action="user">
				${'<@ww.param'} name="resultPage.currentPage" value="${'$'}{resultPage.currentPage+1}" />
				${'<@ww.param'} name="resultPage.pageSize" value="${'$'}{resultPage.pageSize}" />
				${'</@ww.url>'}'>Next</a>
				${'<#else>'}
				Next
				${'</#if>'}
		</td>
		<td>
			Current Page:${'$'}{resultPage.currentPage} Total Page:${'$'}{resultPage.totalPage} TotalRecord:${'$'}{resultPage.totalRecord}</td>
		<td>
			${'<@ww.form'} action="${modelDefinition.className?uncap_first}" method="GET">
				${'<@ww.textfield'} label="Records per Page" name="resultPage.pageSize" size="3" />
				${'<@ww.textfield'} label="Goto" name="resultPage.currentPage" size="3" />
				${'<@ww.submit />'}
			${'</@ww.form>'}
		</td>
	</tr>
</table>
