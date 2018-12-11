/**
 * 
 */
$(function() {
	var updateteacherurl = "/ik/teacheradmin/updateteacher";
	
	$('#updateteacher-btn').click(function() {
		teachername = $('input[name=teachername]').val();
		teacherdes = $('#teacherdes').val();
		var timg = $('input[name=timg]')[0].files[0];
		var formData = new FormData();
		formData.append('teachername', teachername);
		formData.append('teacherdes',teacherdes);
		formData.append('timg',timg);
		alert(teacherdes);
		$.ajax({
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			type : 'post',
			dataType : 'json',
			url : updateteacherurl,
			data : 	formData,
			success : function(data) {
				if (data.success) {
					alert("教师修改成功");
					window.location.href ='/ik/teacher/update';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/teacher/update';
				}
			}
		});
	});

});
