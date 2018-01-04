var $!{lowerName}Edit = function() {
	var _this = {
		init : function() {
			$('#btnSave').on('click', function() {
				_this.save();
			});
			$('#btnBack').on('click', function() {
				window.parent.closeEditWin();
			});
		},
		save : function(callback) {
			var saveForm = $('#form');
			if (saveForm.form('validate')) {
				originUtils.progress();
				var param = origin.serializeObject(saveForm);
				originUtils.ajaxJsonAsync('save.html', param, function(data) {
					originUtils.closeProgress();
					if (data.success) {
						if ($.isFunction(callback)) {
							callback();
						}
						window.parent.closeEditWin(true);
					} else {
						originUtils.alert(originUtils.defaultMsg.warnmsg, data.msg, 'error');
					}
				});
			}
		}
	};
	return _this;
}();

$(function() {
	$!{lowerName}Edit.init();
});