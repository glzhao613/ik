/**
 * 
 */
$(function(){
	initNewsByDate();
	
	$('#newsbydate').on('click','.newsdetail',function() {
		var setidurl = "/ik/newsadm/setid";
		var newsid = $(this).attr("id");
		var formData = new FormData();
		formData.append('newsid', newsid);
		$.ajax({
			url : setidurl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href = "/ik/news/dnews";
				}
			}
		});
	});
});


function initNewsByDate(){
	var getnewsbydate = "/ik/newsadm/getnewsbydate";
	
	$.getJSON(getnewsbydate,function(data){
		var h_temp = '<a  class="newsdetail"  id="';
		var b_temp = '"><li>';
		var t_temp = '</li></a>';
		var temp = '';
		
		data.newslist.map(function(item,index){
			temp += h_temp + item.newsId + b_temp + item.newsTitle + t_temp;
		});
		$("#newsbydate").html(temp);
	});
}
