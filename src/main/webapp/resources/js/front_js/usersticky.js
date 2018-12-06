/**
 * 
 */

$(function() {
	var initurl="/ik/useradmin/islogin";
	initsticky();
	function initsticky() {
		$.getJSON(initurl,function(data){
			if(data.success){
				var temp=""+"<img id='userimg' src='"+data.user.userImg+"' style='width:80px; height:80px;line-height:80px;text-align:center;opacity:0.5;border-radius:40px'>";
				$("#usersticky").html(temp);
			}else{
				var temp=""+"<div id='userimg' style='width:80px; height:80px;line-height:80px;text-align:center;background: rgba(0,0,0,0.2)'>点击登录</div>";
				$("#usersticky").html(temp);
			}
		});
		
	}
	
	$("#usersticky").on("click","#userimg",function(){
		$.getJSON(initurl,function(data){
			if(data.success){
				window.location.href ='/ik/user/userinfo';
			}else{
				window.location.href ='/ik/user/login';
			}
		});
	});
	

	
	
	
	
});
