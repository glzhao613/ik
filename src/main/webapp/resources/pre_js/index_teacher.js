$(function() {
	var count = 1;
	var setcUrl='/ik/teacheradmin/setid';
	var teacherUrl='/ik/teacheradmin/list';
	var pageNum = 1;
	var pageSizeC=5;
	var pageIndexC=1;
	
	// 加载第一页
	showItems(pageSizeC, pageIndexC);


	
	function showItems(pageSize, pageIndex) {
		var url = teacherUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				
				var atemp='';
				data.teacherList.map(function(item,index) {
					atemp+='<div class="content"><div class="imgbox"><img class="qc" id='+item.teacherId+' src="'+item.teacherImg+'"></div><div class="textbox"><span>'+item.teacherName+'</span></div></div>';
				});
				$('#index_teacher').html(atemp);
				
			} else {
				alert(data.errMsg);
			}
		});
	}
	
	$('#index_teacher').on('click','.qc',function() {
		var teacherid=$(this).attr('id');
		var formData = new FormData();
		alert(teacherid);
		formData.append('teacherid', teacherid);
		$.ajax({
			url : setcUrl,
			type : 'post',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href ="/ik/pre/td";
				} else {
					alert(data.errMsg);
				}
			}
		});

	});
	
	
	
});
