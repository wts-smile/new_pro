function modpass()
{
	/*console.log($("#opass").val());
	console.log($("#npass").val());
	console.log($("#opass").val() === $("#npass").val());*/
	if($("#npass").val() === $("#checkpass").val())
	{
		var tmpuser = JSON.parse(sessionStorage.getItem("cuser")).name;
		var json ={};
		json["username"] = tmpuser;
		json["password"] = $("#opass").val();
		json["newpass"] = $("#npass").val();
		$.ajax({
            //几个参数需要注意一下
				async:false,
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/user/changepass" ,//url
                data: json,
                success: function (result) {
					//app3.$set(app3.cartnum,num,result.length);
					alert("成功");
					//Vue.delete();
                },
                error : function() {
                    alert("异常！");
                }
            });
		//alert("异常！");
	}
	else
	{
		alert("确认密码不一致");
	}
	//alert($("#opass").val());
};
const vueNavBottom = new Vue({
	el: '#showusr',
	data:{
		username:"anounymous"
	}
});
$(document).ready(function(){
	vueNavBottom.username= JSON.parse(sessionStorage.getItem("cuser")).name;
});