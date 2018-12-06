/**
 * 
 */
$(function() {
	var contactsaddurl = '/ik/contactsad/contactsinsert';
	$('#contact-btn').click(function() {
		var contactTel=$('#phone').val();
		contactName=$('#name').val();
		contactEmail=$('#email').val();
		contactArticle=$('#message').val();
		
		var formData = new FormData();
		formData.append('contacttel', contactTel);
		formData.append('contactname', contactName);
		formData.append('contactemail', contactEmail);
		formData.append('contactarticle', contactArticle);
		 	  
		if (contactTel=(/^1[34578]\d{9}$/.test(contactTel))) 
 			{
 				if (contactEmail=(/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/.test(contactEmail)))
 				{ 
 					$.ajax({
 						async : false,
 						cache : false,
 						contentType : false,
 						processData : false,
 						type : 'post',
 						dataType : 'json',
 						url : contactsaddurl,
 						data :formData,
 						success : function(data) {
 							if(data.success){
 								alert("提交成功");
 								window.location.href ='/ik/contacts/contact';
 							}
 							else{
 								alert(data.errMsg);
 								window.location.href ='/ik/contacts/contact';
 							}
 					}
 				});
 				}
		    } 
		else
 			{
 				alert("信息错误，请重新输入：电话格式为：1*****（共11位），邮箱格式为：***@**.com");
 			}

	});

});
