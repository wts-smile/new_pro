const vueNavBottom = new Vue({
            el: '#showusr',
            data:{
                username:"anounymous"
            }
		});
		var app = new Vue({
            el: "#olist",
            data: {
                list:[]/* [{
                    id: 1,
                    name: "草莓",
                    price: 10,
                    num: 1,
                    active: true
                }, {
                    id: 2,
                    name: "巧克力",
                    price: 30,
                    num: 2,
                    active: false
                }, {
                    id: 3,
                    name: "奶油蛋糕",
                    price: 50,
                    num: 3,
                    active: true
                }],*/
            }
        });
		$(document).ready(function(){
			//alert("good");
			if(sessionStorage.getItem("cuser"))
			{
				//var tmpuser = JSON.parse(sessionStorage.getItem("cuser")).name;
				vueNavBottom.username= JSON.parse(sessionStorage.getItem("cuser")).name;
				loadorder(0);
				//alert(tmpuser);
			}
		});
		function loadorder(st)
		{
			while(app.list.pop());
			if(sessionStorage.getItem("cuser"))
			{
				var tmpuser = JSON.parse(sessionStorage.getItem("cuser")).name;
				$.ajax({
            //几个参数需要注意一下
                type: "GET",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/order/list" ,//url
                data: {"username":tmpuser},
                success: function (result) {
					console.log(result);
					for(var i of result)
					{
						if(st === 0 || i.status === st)
						{
							var json = {};
							json["id"]=i.orderId;
							json["name"]=i.product.name;
							json["cnt"]=i.num;
							json["price"]=i.num * i.product.price;
							json["uname"]=i.username;
							json["time"]=i.startTime;
							if(i.status == 1)
							{
								json["status"]="未支付";
							}
							if(i.status == 2)
							{
								json["status"]="已支付";
							}
							if(i.status == 3)
							{
								json["status"]="已取消";
							}
							console.log(json);
							app.list.push(json);
						}
					}
					//Vue.delete();
					},
					error : function() {
                    alert("异常！");
					}
				});
				//alert(tmpuser);
			}
		}