const app = new Vue({
            el: "#app",
            data: {
                imgList: [
                    "upload/l1.png",
                    "upload/l2.png",
                    "upload/l3.png",
                    "upload/l4.png",
                    "upload/l5.png",

                ],
                index: 0
            },
            methods: {
                // 上一张
                prev() {
                    this.index--;
                },
                // 下一张
                next() {
                    this.index++;
                }
            }
        });
		const app2 = new Vue({
            el: "#typenav",
            data: {
                types:[]
            }
        });
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
		function loadtype()
		{
			$.ajax({
            //几个参数需要注意一下
                type: "GET",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/product/types" ,//url
                data: {},
                success: function (result) {
					console.log(result);
					for(var i of result)
					{
						console.log(i);
						app2.types.push(i);
					}
					//Vue.delete();
                },
                error : function() {
                    alert("异常！");
                }
            });
		};
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
		function typeshow(e)
		{
			//alert($(e).text());
			sessionStorage.setItem("ctype",$(e).text());
			window.location="分类查找.html";
			
		}
		$(document).ready(function(){
			loadtype();
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