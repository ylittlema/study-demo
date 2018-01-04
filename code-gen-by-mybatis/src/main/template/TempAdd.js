var $!{lowerName}Add = function() {
	var _this = {
		init : function() {
			$('#btnSave').on('click', function() {
				_this.save(function() {
					$('#form').resetForm();
					originUtils.alert(originUtils.defaultMsg.sysmsg, '保存成功');
				});
			});
			$('#btnBack').on('click', function() {
				window.parent.closeAddWin();
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
						window.parent.closeAddWin(true);
					} else {
						originUtils.alert(originUtils.defaultMsg.warnmsg, data.msg, 'error');
					}
				});
			}
		}
	};
	return _this;
}();;

$(function() {
	$!{lowerName}Add.init();
});