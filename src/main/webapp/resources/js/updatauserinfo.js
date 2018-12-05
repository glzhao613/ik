/**
 * 
 */
$(function() {
	var updatainfourl = '/ik/useradmin/userupdatainfo';
	$('#btn').click(function() {
		var userName = $('#name').val();
		var userTel = $('#tel').val();
		var userDes = $('#des').val();
		var uImg = $("#uimg")[0].files[0];
		var formData = new FormData();
		formData.append('uImg', uImg);
		formData.append('name',userName);
		formData.append('tel',userTel);
		formData.append('des',userDes);
		$.ajax({
			url : updatainfourl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert("修改信息成功");
					window.location.href ='/ik/user/updatainfo';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/user/updatainfo';
				}
			}
		});
	});

});
