var $!{className}View = OGridClass.extend({
	init : function(opt) {
		opt = opt || {};
		// 调用父类的方法
		this._super(opt);

		this.grid_conf = opt.gridConf || {};
		// 远程排序
		this.grid_conf.remoteSort = true;
	},
	urls : {
		add_view : #literal()$basePath#end + '/$!{lowerName}/add.html',
		edit_view : #literal()$basePath#end + '/$!{lowerName}/edit.html',
		data_list : #literal()$basePath#end + '/$!{lowerName}/pageList.html',
		tools : #literal()$basePath#end + '/$!{lowerName}/getMenuInfo.html'
	},
	getGridConf : function() {
		return this.grid_conf;
	},
	getGridColumns : function(){
		this._super();
		var columns = [];
		columns.push([
		 	{field:'$!{tableKey}',checkbox:true},
		 	#foreach($item in $!{columnDatas}){field:'$!item.propertyName',title:'$!item.columnComment',width:100,halign:'center',align:'left',sortable:true}#if($velocityCount!=$columnDatas.size()),
		 	#end#end]);
		return columns;
	},
	search : function() {
		this.Grid.datagrid('load', origin.serializeObject(searchForm));
	},
	cleanSearch : function() {
		this.Grid.datagrid('load', {});
		searchForm.resetForm();
	},
	refresh : function() {
		this.Grid.datagrid('reload');
	}
});

// 关闭增加窗口
var closeAddWin = function(isRefresh) {
	$('#addWin').window('close');
	if (isRefresh != undefined && isRefresh) {
		view.refresh();
	}
};
// 关闭编辑窗口
var closeEditWin = function(isRefresh) {
	$('#editWin').window('close');
	if (isRefresh != undefined && isRefresh) {
		view.refresh();
	}
};

var searchForm = $('#searchForm');
var view;
$(function() {
	var opt = {
		idField : '$!{tableKey}'
	};
	view = new $!{className}View(opt);
	view.dataGrid();
	// 关闭页面加载等待层
	originUtils.hideLoadding();
});