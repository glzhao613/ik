$(function() {
	var initurl="/ik/teacheradmin/teacherlist";
	initsticky();
	function initsticky() {
		$.getJSON(initurl,function(data){
			if(data.success){
				var temp=""+"<img src='"+data.teacherlist.teacherImg+"'>";
				var btemp=""+"<h3 class=teacher-title>'"+data.teacherlist.teacherName+"'</h3><p>'"+data.teacherlist.teacherDes+"'</p>";
				/*var ctemp=""+"<p>'"+data.teacherlist.teacherDes+"'</p>";*/
				$("#timg").html(temp);
				$("#tname").html(btemp);
			}else{
				alert(data.errMsg);
				window.location.href ="/ik/company/center ";
			}
		});
		
	}
		

	
	
	
	
});
