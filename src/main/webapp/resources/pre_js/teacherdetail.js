$(function() {
	var initurl="/ik/teacheradmin/teacherlist";
	initsticky();
	function initsticky() {
		$.getJSON(initurl,function(data){
			if(data.success){
				var atemp='<h1>教师详情</h1>';
				data.teacherlist.map(function(item,index) {
					var btemp='<img src="'+item.teacherImg+'" />';
					var ctemp='<p>姓名：'+item.teacherName+'</p>';
					var dtemp='<p>'+item.teacherDes+'</p>';
					var temp=atemp+btemp+ctemp+dtemp;
					$("#td").html(temp);
				});
			}else{
				alert(data.errMsg);
				window.location.href ="/ik/company/center ";
			}
		});
		
	}
			
});
		
