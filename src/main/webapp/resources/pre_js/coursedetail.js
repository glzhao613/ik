$(function() {
	var count = 1;
	var pageSize = 4;
	var listUrl = '/ik/courseadmin/predetaillist';
	var pageNum = 1;

	// 加载第一页
	addItems(pageSize, pageNum);

	function addItems(pageSize, pageIndex) {

		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var btemp='';
				data.predetailcourseList.map(function(item,index) {
					var temp=""+"<img src='"+item.courseImg+"'>";
					var btemp=""+"<li>课程名称：'"+item.courseName+"'</li><li>授课老师：'"+item.courseTeacher.teacherName+"'</li><li>课程类型：'"+item.courseType.courseTypeName+"'</li><li>课程时长：'"+item.courseHour+"'</li><li>课程价格：'"+item.coursePrice+"'</li><li>'"+item.courseDes+"'</li>";
					$("#cd_img").html(temp);
					$("#c_detail").html(btemp);
				});

			} else {
				alert(data.errMsg);
			}
		});
	}


	
	
});
