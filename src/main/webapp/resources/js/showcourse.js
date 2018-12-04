$(function() {
	var count = 1;
	var pageSize = 4;
	var listUrl = '/ik/courseadmin/list';
	var delUrl='/ik/courseadmin/deletecourse';
	var setUrl='/ik/courseadmin/setid';
	var pageNum = 1;

	// 加载第一页
	addItems(pageSize, pageNum);

	function addItems(pageSize, pageIndex) {

		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var htemp=''+'<tr><th>课程ID</th><th>课程名称</th><th>课程类型</th><th>任课教师</th><th>课程描述</th><th>课程操作</th></tr>';
				$('#thead').html(htemp);
				var btemp='';
				data.courseList.map(function(item,index) {
					btemp+='<tr><td>'+item.courseId+'</td><td>'+item.courseName+'</td><td>'+item.courseType.courseTypeName+'</td><td>'+item.courseTeacher.teacherName+'</td><td>'+item.courseDes+'</td><td><input type="button" value="编辑课程" class="edit" id="'+item.courseId+'">&nbsp;<input type="button" value="删除" class="delbtn" id="'+item.courseId+'"></td></tr>';
				});
				$('#tbody').html(btemp);
				$('#index').html('第'+pageNum+'页');
				$('#count').html('共'+count+'页');
			} else {
				alert(data.errMsg);
			}
		});
	}

	$('#pre').click(function() {
		if (pageNum <= 1) {
			alert("已经是第一页啦");
		} else {
			pageNum -= 1;
			addItems(pageSize, pageNum);
		}

	});

	$('#next').click(function() {
		if (pageNum >= count) {
			alert("已经是最后一页啦");
		} else {
			pageNum += 1;
			addItems(pageSize, pageNum);
		}

	});
	
	$('#tbody').on('click','.delbtn',function() {
		var id=$(this).attr('id');
		var formData = new FormData();
		formData.append('courseid', id);
		$.ajax({
			url : delUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert(data.coursename+'删除成功');
					window.location.href ='/ik/course/showcourse';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/course/showcourse';
				}
			}
		});

	});
	
	$('#tbody').on('click','.edit',function() {
		var courseid=$(this).attr('id');
		var formData = new FormData();
		formData.append('courseid', courseid);
		$.ajax({
			url : setUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href ='/ik/course/update';
					
				}
			}
		});

	});
	
	
	
});
