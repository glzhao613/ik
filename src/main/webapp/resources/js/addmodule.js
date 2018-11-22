/**
 * 
 */
$(function() {
	var moduleaddurl = '/ik/modulead/moduleadd';
	$('#add-btn').click(function() {
		alert(1111);
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : moduleaddurl,
			data : {
				modulename : $('input[name=modulename]').val()
			},
			success : function(data) {
				switch (data.success){
				case 1:
					alert('模块添加成功');
					window.location.href ='';
				case -1:
					alert(data.errMsg);
				case -2:
					alert(data.errMsg);
				}
			}
		});
	});

});
