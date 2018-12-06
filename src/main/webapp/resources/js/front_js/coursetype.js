$(function() {
	var count = 1;
	var pageSize = 4;
	var listUrl = '/ik/coursetypeadmin/list';
	var setUrl='/ik/coursetypeadmin/setid';
	var setcUrl='/ik/courseadmin/setid';
	var courseUrl='/ik/courseadmin/list';
	var pageNum = 1;
	var pageSizeC=3;
	var pageIndexC=1;
	
	// 加载第一页
	addItems(pageSize, pageNum);
	showItems(pageSizeC, pageIndexC);
	function addItems(pageSize, pageIndex) {

		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var btemp='';
				var ctemp='';
				data.coursetypeList.map(function(item,index) {
					btemp+='<input type="button" value="'+item.courseTypeName+'" class="edit" id="'+item.courseTypeId+'" style=margin:20px>';
					/*ctemp+='<tr><td>'+item.courseName+'</td><td>'+item.courseDes+'</td></tr>';*/
				});
				$('#filters').html(btemp);
				/*$('#course').html(ctemp);*/
			} else {
				alert(data.errMsg);
			}
		});
	}
	
	function showItems(pageSize, pageIndex) {
		var url = courseUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var ctemp='';
				data.courseList.map(function(item,index) {
					ctemp+='<div class="port-1"><tr><td>'+item.courseName+'</td></tr><div><input type="button" value="更多" class="qc" id="'+item.courseId+'"></div></div><div class="b-wrapper"><h2 class="b-animate b-from-left    b-delay03 "></h2></div></a>'
				});
				$('#course').html(ctemp);
			} else {
				alert(data.errMsg);
			}
		});
	}
	
	$('#course').on('click','.qc',function() {
		var courseid=$(this).attr('id');
		var formData = new FormData();
		formData.append('courseid', courseid);
		$.ajax({
			url : setcUrl,
			type : 'post',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href ="/ik/course/dcours ";
				} else {
					alert(data.errMsg);
				}
			}
		});

	});
	



	
	$('#filters').on('click','.edit',function() {
		var coursetypeid=$(this).attr('id');
		var formData = new FormData();
		formData.append('coursetypeid', coursetypeid);
		$.ajax({
			url : setUrl,
			type : 'post',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					showItems(pageSizeC, pageIndexC);
				} else {
					alert(data.errMsg);
				}
			}
		});

	});
	
	
	
});
