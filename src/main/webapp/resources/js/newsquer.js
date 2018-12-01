$(function(){
	var querOneNewsurl = "/ik/newsadm/newsinfo";
	$('#quer-btn').click(function(){
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url:querOneNewsurl,
			data:{newstitle:$('input[name=newstitle]').val()},
			success:function(data){
				switch(data.success){
				case 1:
					alert("资讯查询成功！");
					window.location.href ='';
				case -1:
					alert(data.errMsg);
				case -2:
					alert(data.errMsg);
				}
			}
		});
	});
});
