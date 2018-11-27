$(function(){
	var querNewsurl = "/ik/newsadm/newspaging";
	$('#paging-dispaly').click(function(){
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url:querNewsurl,
			success:function(data){
				switch(data.success){
				case 1:
					alert("资讯查询成功！");
					
					$("#display").html(data.newsList);
				case -1:
					alert(data.errMsg);
				case -2:
					alert(data.errMsg);
				}
			}
		});
	});
});
