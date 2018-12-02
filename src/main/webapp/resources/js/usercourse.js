$(function() {
	var listUrl = '/ik/useradmin/usercourse';
	var delUrl='/ik/useradmin/delusercourse';

	// 加载第一页
	addItems();

	function addItems() {
		$.getJSON(listUrl, function(data) {
			if (data.success) {
				var htemp=''+'<tr><th>ID</th><th>课程名称</th><th>描述</th><th>价格</th><th>操作</th></tr>';
				$('#thead').html(htemp);
				var btemp='';
				data.cList.map(function(item,index) {
					btemp+='<tr><td>'+item.courseId+'</td><td>'+item.courseName+'</td><td>'+item.courseDes+'</td><td>'+item.coursePrice+'</td><td><input type="button" value="删除" class="delbtn" id="'+item.courseId+'"></td></tr>';
				});
				$('#tbody').html(btemp);
			} else {
				alert(data.errMsg);
			}
		});
	}

	
	$('#tbody').on('click','.delbtn',function() {
		var courseid=$(this).attr('id');
		var formData = new FormData();
		formData.append('courseid', courseid);
		$.ajax({
			url : delUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert('删除成功');
					window.location.href ='/ik/user/usercourse';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/user/usercourse';
				}
			}
		});

	});
	
	
	
});
