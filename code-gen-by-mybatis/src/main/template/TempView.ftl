<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>$!{className} View</title>
		#literal()<#include "../head.ftl">
		#end<!--<link rel="stylesheet" type="text/css" href="${urls.StaticPath}/themes/css/index.css" />-->
	</head>
	<body class="easyui-layout">
		#literal()<#include "../loading.ftl">
		#end<div data-options="region:'north',iconCls:'icon-find',title:'查询条件'" border="false" style="border-bottom: 1px solid #D3D3D3;background:#F6F6F6;overflow:hidden;height:100px;">
			<!-- 条件查询模板内容  -->
			<form id="searchForm">
				#foreach($item in $!{columnDatas})<dl class="searchBox">
					<dt>$!item.columnComment：</dt>
					<dd><input name="$!item.propertyName" type="text"/></dd>
				</dl>
				#end<dl class="searchBox">
					<dt>是否禁用：</dt>
					<dd><select name="disabled"><option value="">-----请选择-----</option><option value="0">否</option><option value="1">是</option></select></dd>
				</dl>
			</form>
			<dl class="searchBox">
				<dd><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="view.search();" style="margin-right:5px;">查询</a></dd>
				<dd><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="view.cleanSearch();">取消</a></dd>
			</dl>
		</div>
	  	<div data-options="region:'center'" border="false" >
	  		<table id="data-list"></table>
	  	</div>
	  	<div id="addWin"></div>
	  	<div id="editWin"></div>
	</body>
	<script type="text/javascript" src="${urls.StaticPath}/scripts/view/class/$!{lowerName}/$!{lowerName}View.js"></script>
</html>