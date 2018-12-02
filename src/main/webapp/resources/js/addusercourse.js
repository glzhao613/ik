/**
 * 
 */
$(function() {
	var initcourseurl = "/ik/courseadmin/courselist";
	var addurl = "/ik/useradmin/addusercourse";
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

		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : addurl,
			data : {
				courseid : $("#courselist option:selected").val()
			},
			success : function(data) {
				if (data.success) {
					alert("添加成功");
					window.location.href = "/ik/user/usercourse";
				} else {
					alert(data.errMsg);
					window.location.href = "/ik/user/addusercourse";
				}
			}
		});

	});

});
