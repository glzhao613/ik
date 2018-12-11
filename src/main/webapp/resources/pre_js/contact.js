function check(){ 
							var  phone = document.getElementById('phone').value;
							var  email=document.getElementById('email').value;
							 	  
						 if (phone=(/^1[34578]\d{9}$/.test(phone))) 
					 		{
					 			if (email=(/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/.test(email)))
							      { alert("提交成功！！！"); }
							       } 
						 else
					 		{
					 			alert("信息错误，请重新输入：电话格式为：1*****（共11位），邮箱格式为：***@**.com");
					 		 }
				  		   }