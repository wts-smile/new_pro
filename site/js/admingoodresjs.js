$(document).ready(function(){
			//alert("good");
			loadpro();
		});
		function loadpro()
		{
			while(app.list.pop());
			$.ajax({
            //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/product/list" ,//url
                data: {"pageSize":255,"pageNum":1},
                success: function (result) {
					//app3.$set(app3.cartnum,num,result.length);
					//console.log(result);
					console.log(result.productList);
					for(var ee of result.productList)
					{
						var json = {};
						json["id"]=ee.id;
						json["name"]=ee.name;
						json["num"]=(ee.status==1?"已上架":"已下架");
						json["active"]=(ee.status==1?true:false)
						json["price"]=ee.price;
						app.list.push(json);
					}
					//Vue.delete();
                },
                error : function() {
                    alert("异常！");
                }
            });
		}
        var app = new Vue({
            el: " .b ",
            data: {
                list: []/*{
                    id: 1,
                    name: " airpodspro ",
                    
                    active: true
                }, {
                    id: 2,
                    name: " macbookair ",
                    price: 15999,
                    num: 2,
                    active: false
                }, {
                    id: 3,
                    name: " ipadmini ",
                    price: 3999,
                    num: 3,
                    active: true
                }],*/
            },
            methods: {
                remove: function (i) { //移除单个
					$.ajax({
            //几个参数需要注意一下
				async:false,
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/product/onstore" ,//url
                data: {"productId":this.list[i].id,"stat":0},
                success: function (result) {
					//app3.$set(app3.cartnum,num,result.length);
					//console.log(result);
					//Vue.delete();
                },
                error : function() {
                    alert("异常！");
                }
				});
				loadpro();
                },
				instock: function(i)
				{
					$.ajax({
            //几个参数需要注意一下
				async:false,
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/product/onstore" ,//url
                data: {"productId":this.list[i].id,"stat":1},
                success: function (result) {
					//app3.$set(app3.cartnum,num,result.length);
					//console.log(result);
					//Vue.delete();
                },
                error : function() {
                    alert("异常！");
                }
				});
				loadpro();
				},
                created: function () {
                }
            },
            computed: {
                total: function () { //计算总价
                    let sum = 0;
                    for (let i = 0; i < this.list.length; i++) {
                        if (this.list[i].active)
                            sum += this.list[i].price * this.list[i].num;
                    }
                    return sum;
                },
                count: function () { //计算总数
                    let sum = 0;
                    for (let i = 0;
                        (i < this.list.length); i++) {
                        if (this.list[i].active)
                            sum += this.list[i].num;
                    }
                    return sum;
                }
            },
        })