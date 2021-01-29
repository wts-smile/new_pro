function toorder()
	{
		window.location = "查找订单1.html"
	}
	function togood()
	{
		window.location = "管理员搜索商品.html"
	}
	function addgood()
	{
		window.location = "新增商品.html"
	}
	function cpass() {
			var json = $('#app').serialize();
			json['username']=sessionStorage.getItem("cuser").name;
            $.ajax({
            //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/user/changepass" ,//url
                data: $('#app').serialize(),
                success: function (result) {
					if(result.errno==0)
					{
                    //console.log(result.user);//打印服务端返回的数据(调试用)
					window.location = "adminModifyPwd.html";
					//alert(result.resultCode);
                    if (result.resultCode == 200) {
                        alert("SUCCESS");
                    }}
					else
					{
						alert("修改失败");
					}
                    ;
                },
                error : function() {
                    alert("异常！");
                }
            });
        }