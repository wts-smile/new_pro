function reg() {
            $.ajax({
            //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/user/register" ,//url
                data: $('#app').serialize(),
                success: function (result) {
					if(result.errno==0)
					{
                    //console.log(result.user);//打印服务端返回的数据(调试用)
					sessionStorage.setItem("cuser",JSON.stringify(result.user));
					alert("注册成功");
					window.location = "index.html";
					//alert(result.resultCode);
                    if (result.resultCode == 200) {
                        alert("SUCCESS");
                    }}
					else
					{
						alert("失败, 原因：" + result.error);
					}
                    ;
                },
                error : function() {
                    alert("异常！");
                }
            });
        }