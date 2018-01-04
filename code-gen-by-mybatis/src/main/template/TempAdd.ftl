<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>$!{className} Add</title>
		#literal()<#include "../head.ftl">
		<style>
			a.l-btn span.l-btn-left{display:inline;}
		</style>
	#end</head>
	<body class="easyui-layout">
		<div data-options="region:'north'" border="false" style="overflow:hidden;">
			<div class="toolbar-content">
				<a href="#" id="btnSave" class="easyui-linkbutton" plain="true" iconCls="icon-save">保存</a>
				<a href="#" id="btnBack" class="easyui-linkbutton" plain="true" iconCls="icon-cancel">关闭</a>
			</div>
		</div>
	  	<div data-options="region:'center'" border="false" >
	  		<form id="form">
	  			<div class="form_title">基本信息</div>
			    <table class="form" cellpadding="0" cellspacing="0" border="0">
		    		<tbody>
		    			#foreach($item in $!{columnDatas})<tr>
		    				<td class="field">$!item.columnComment：</td>
		    				<td class="controls"><input name="$!item.propertyName" type="text" maxlength="30" style="width:300px" class="easyui-validatebox" data-options="required:true"></td>
		    			</tr>
		    			#end
</tbody>
		    	</table>
		    </form>
	  	</div>
	</body>
	<script type="text/javascript" src="${urls.StaticPath}/scripts/view/class/$!{lowerName}/$!{lowerName}Add.js"></script>
</html>