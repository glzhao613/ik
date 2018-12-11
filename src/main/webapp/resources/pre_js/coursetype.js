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
					btemp+='<div class="edit" id="'+item.courseTypeId+'">'+item.courseTypeName+' </div>';
					/*ctemp+='<tr><td>'+item.courseName+'</td><td>'+item.courseDes+'</td></tr>';*/
				});
				$('#coursetype').html(btemp);
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
				var atemp='';
				data.courseList.map(function(item,index) {
					atemp+='<td><img src='+item.courseImg+'></td>';
					ctemp+='<td><a class="qc" id='+item.courseId+'>'+item.courseName+'</a></td>'
				});
				$('#courseimg').html(atemp);
				$('#course').html(ctemp);
			} else {
				alert(data.errMsg);
			}
		});
	}
	
	$('#course').on('click','.qc',function() {
		var courseid=$(this).attr('id');
		var formData = new FormData();
		alert(111);
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
					window.location.href ="/ik/pre/coursedetail";
				} else {
					alert(data.errMsg);
				}
			}
		});

	});
	



	
	$('#coursetype').on('click','.edit',function() {
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
