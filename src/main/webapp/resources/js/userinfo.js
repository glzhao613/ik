/**
 * 
 */
$(function() {
	var initurl="/ik/useradmin/islogin";
	var quiturl="/ik/useradmin/quitlogin";
	
	inituserinfo();
	function inituserinfo() {
		$.getJSON(initurl,function(data){
			if(data.success){
				$("#account").html(data.user.userAccount);
				$("#name").html(data.user.userName);
				$("#tel").html(data.user.userTel);
				$("#des").html(data.user.userDes);
			}else{
				alert("没有用户登录");
				window.location.href ="/ik/user/login";
			}
		});
	}
	
	$("#quitbtn").click(function() {
		$.getJSON(quiturl,function(data){
			if(data.success){
				window.location.href ="/ik/index.html";
			}else{
				alert("退出登录失败");
			}
		});
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
});
