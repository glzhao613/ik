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
	
	$("#lilist").on("click","#indexpage",function(){
		var temp="<li id='indexpage' class='active'><a class='active'>首页</a></li><li id='center'>中心简介</li><li id='news'>新闻资讯</li><li id='file'>教学资源</li><li id='course'>培训课程</li><li id='contact'>联系我们</li>";
		$("#lilist").html(temp);
		var url="/ik/pre/indexpage";
		$("#ifm").attr("src",url);
	});
	
	$("#lilist").on("click","#center",function(){
		var temp="<li id='indexpage'>首页</li><li id='center' class='active'><a class='active'>中心简介</a></li><li id='news'>新闻资讯</li><li id='file'>教学资源</li><li id='course'>培训课程</li><li id='contact'>联系我们</li>";
		$("#lilist").html(temp);
		var url="/ik/pre/center";
		$("#ifm").attr("src",url);
	});
	
	$("#lilist").on("click","#news",function(){
		var temp="<li id='indexpage'>首页</li><li id='center'>中心简介</li><li id='news' class='active'><a class='active'>新闻资讯</a></li><li id='file'>教学资源</li><li id='course'>培训课程</li><li id='contact'>联系我们</li>";
		$("#lilist").html(temp);
		var url="/ik/pre/news";
		$("#ifm").attr("src",url);
	});
	
	$("#lilist").on("click","#file",function(){
		var temp="<li id='indexpage'>首页</li><li id='center'>中心简介</li><li id='news'>新闻资讯</li><li id='file' class='active'><a class='active'>教学资源</a></li><li id='course'>培训课程</li><li id='contact'>联系我们</li>";
		$("#lilist").html(temp);
		var url="/ik/pre/file";
		$("#ifm").attr("src",url);
	});
	
	$("#lilist").on("click","#course",function(){
		var temp="<li id='indexpage'>首页</li><li id='center'>中心简介</li><li id='news'>新闻资讯</li><li id='file'>教学资源</li><li id='course' class='active'><a class='active'>培训课程</a></li><li id='contact'>联系我们</li>";
		$("#lilist").html(temp);
		var url="/ik/pre/course";
		$("#ifm").attr("src",url);
	});
	
	$("#lilist").on("click","#contact",function(){
		var temp="<li id='indexpage'>首页</li><li id='center'>中心简介</li><li id='news'>新闻资讯</li><li id='file'>教学资源</li><li id='course'>培训课程</li><li id='contact' class='active'><a class='active'>联系我们</a></li>";
		$("#lilist").html(temp);
		var url="/ik/pre/contact";
		$("#ifm").attr("src",url);
	});
	
	
	
	
	
	
});
