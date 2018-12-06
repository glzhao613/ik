/**
 * 
 */
$(function() {
	var addteacherurl = "/ik/teacheradmin/insertteacher";
	
	$('#teacher-btn').click(function() {
		teachername = $('input[name=teachername]').val();
		teacherdes = $('#teacherdes').val();
		var timg = $('input[name=timg]')[0].files[0];
		var formData = new FormData();
		formData.append('teachername', teachername);
		formData.append('teacherdes',teacherdes);
		formData.append('timg',timg);
		$.ajax({
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			type : 'post',
			dataType : 'json',
			url : addteacherurl,
			data : 	formData,
			success : function(data) {
				if (data.success) {
					alert("教师添加成功");
					window.location.href ='/ik/teacher/insert';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/teacher/insert';
				}
			}
		});
	});

});
