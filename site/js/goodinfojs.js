var app = new Vue({
            el: "#app",
            data: {
                id: 1,
                message: "暗影精灵6到飞起，全新升级10代酷睿处理器！轻薄新外观搭载酷凉风暴散热模组！",
                name: "暗影精灵6",
                bgurl: "img/computer.jpg",
                num: 1,
                price: 7800,
                address: "home",
            },
            methods: {
                submit: function () {
                    //console.log(this)
					var uname = JSON.parse(sessionStorage.getItem("cuser")).name;
					var pid = sessionStorage.getItem("cgoodid");
					$.ajax({
						//几个参数需要注意一下
						type: "POST",//方法类型
						dataType: "json",//预期服务器返回的数据类型
						url: "http://localhost:8080/cart/add" ,//url
						data: {"username":uname,"pid":pid,"cnt":this.num},
						success: function (result) {
							console.log(result);
							if(result.errno ==  0)
							{
								alert("添加成功");
							}
							else
							{
								alert("添加失败");
							}
						//Vue.delete();
						},
						error : function() {
							alert("异常！");
						}
					});
                }
            },

        });
		const app4 = new Vue({
            el: "#loginnav",
            data: {
                seen:{
					visible : true
				},
				user: {
				}
            }
        });
		function getinfo()
		{
			var id = sessionStorage.getItem("cgoodid");
			$.ajax({
            //几个参数需要注意一下
                type: "GET",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/product/get" ,//url
                data: {"productId":id},
                success: function (result) {
					console.log(result);
					app.id=result.id;
					app.message=result.detail;
					app.name=result.name;
					app.bgurl='upload/' + result.pic;
					app.num = 1;
					app.price = result.price;
					//Vue.delete();
                },
                error : function() {
                    alert("异常！");
                }
            });
		}
		const app3 = new Vue({
            el: "#shopcart",
            data: {
                seen:{
					visible : true
				},
				cartnum: {
					num : 0
				}
            }
        });
		function getcartnum(username)
		{
			var str = '{"username":"' + username + '"}';
			console.log(str);
			console.log(typeof str);
			var tjson = JSON.parse(str);
			$.ajax({
            //几个参数需要注意一下
                type: "GET",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/cart/list" ,//url
                data: tjson,
                success: function (result) {
					console.log(username);
					console.log(result);
					//app3.$set(app3.cartnum,num,result.length);
					app3.cartnum.num=result.length;
					//Vue.delete();
                },
                error : function() {
                    alert("异常！");
                }
            });
		};
		$(document).ready(function(){
			getinfo();
			if(sessionStorage.getItem("cuser"))
			{
				var tmpuser = JSON.parse(sessionStorage.getItem("cuser")).name;
				//alert(tmpuser);
				getcartnum(tmpuser);
				app4.$set(app4.user,"name",tmpuser);
			}
			else
			{
				app3.seen.visible=false;
				app4.seen.visible=false;
				//alert("login needed");
			}
			
		});