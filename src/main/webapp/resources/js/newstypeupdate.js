$(function(){
	var newstypeupdateurl = "/ik/newstypeadm/newstypeupdate";
	
	initUpdateNews();
	
	$('#update-btn').click(function() {
		newstypename = $('input[name=newstypename]').val();
		var formData = new FormData();
		formData.append('newstypename', newstypename);
		
		$.ajax({
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			type : 'post',
			dataType : 'json',
			url : newstypeupdateurl,
			data : formData,
			success : function(data){
				if(data.success){
					alert("修改成功！");
					window.location.href = "/ik/newstype/newstypeupdate";
				}else{
					alert(data.errMsg);
					window.location.href = "/ik/newstype/newstypeupdate";
				}
			}	
		});	
	});
});

function initUpdateNews(){
	var setupdateurl = "/ik/newstypeadm/setupdateid";
	$.getJSON(setupdateurl,function(data){
		$("#newstypename").attr("value",data.updatenewstype.newsTypeName);
	});
}