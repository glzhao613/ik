$(function() {
	var count = 1;
	var setcUrl='/ik/courseadmin/setid';
	var courseUrl='/ik/courseadmin/prelist';
	var pageNum = 1;
	var pageSizeC=5;
	var pageIndexC=1;
	
	// 加载第一页
	showItems(pageSizeC, pageIndexC);


	
	function showItems(pageSize, pageIndex) {
		var url = courseUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				
				var atemp='';
				data.precourseList.map(function(item,index) {
					atemp+='<div class="content"><div class="imgbox"><img class="qc" id='+item.courseId+' src="'+item.courseImg+'"></div><div class="textbox"><span>'+item.courseName+'</span></div></div>';
				});
				$('#index_course').html(atemp);
				
			} else {
				alert(data.errMsg);
			}
		});
	}
	
	$('#index_course').on('click','.qc',function() {
		var courseid=$(this).attr('id');
		var formData = new FormData();
		alert(courseid);
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
	
	
	
});
