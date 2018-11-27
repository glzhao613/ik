/**
 * 
 */
$(function() {
	var initcourseurl = "/ik/courseadmin/courselist";
	var adduserurl = "/ik/useradmin/adduser";
	initCourse();

	function initCourse() {
		$.getJSON(initcourseurl, function(data) {
			if (data.success) {
				var temp = "";
				data.courselist.map(function(item, index) {
					temp += "<option value='" + item.courseId + "'>"
							+ item.courseName + "</option>";

				});
				$("#courselist").html(temp);

			}

		});

	}

	$("#login-btn").click(function() {

		alert("进入");
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : adduserurl,
			data : {
				useraccount : $("input[name=account]").val(),
				userpwd : $("input[name=pwd]").val(),
				course : $("#courselist option:selected").val()
			},
			success : function(data) {
				if (data.success) {
					alert("添加成功");
				} else {
					alert(data.errMsg);
					window.location.href = "/ik/user/adduser";
				}
			}
		});

	});

});
