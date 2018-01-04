<table>
<#list modelDefinition.properties?if_exists?keys as propertyName>
<#if !modelDefinition.notInUIs?if_exists?seq_contains(propertyName)>
<tr>
<td>${"$"+"{action.getText('"+propertyName+"')}"}</td>
<#if modelDefinition.properties[propertyName]?if_exists!='String'>
<td>${'$'+'{'+modelDefinition.className?uncap_first+'.'+propertyName+'?if_exists?string}'}</td>
<#else>
<td>${'$'+'{'+modelDefinition.className?uncap_first+'.'+propertyName+'?if_exists}'}</td>
</#if>
</tr>
</#if>
</#list>
</table>
