$(document).ready(function(){
			vueNavBottom.username= JSON.parse(sessionStorage.getItem("cuser")).name;
			getcartlist();
		});
		function getcartlist()
		{
			if(!sessionStorage.getItem("cuser"))
			{
				alert("login error");
				return;
			}
			var tmpuser = JSON.parse(sessionStorage.getItem("cuser")).name;
			//alert(uid);
			var $=jQuery.noConflict();
			$.ajax({
            //几个参数需要注意一下
				async:false,
                type: "GET",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/cart/list" ,//url
                data: {"username":tmpuser},
                success: function (result) {
					console.log(result);
					while(app.list.pop());
					for(var u of result)
					{
						var json = {};
						json["id"]=u.id;
						json["pid"]=u.product.id;
						json["name"]=u.product.name;
						json["price"]=u.product.price;
						json["num"]=u.count;
						json["active"]=true;
						console.log(u);
						app.list.push(json);
					}
					//Vue.delete();
                },
                error : function() {
                    alert("异常！");
                }
            });
		}
		function delcart(i)
		{
			//alert(i);
			if(!sessionStorage.getItem("cuser"))
			{
				alert("login error");
				return;
			}
			var tmpuser = JSON.parse(sessionStorage.getItem("cuser")).name;
			//alert(uid);
			jQuery.ajax({
            //几个参数需要注意一下
				async:true,
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/cart/edit" ,//url
                data: {"username":tmpuser,"idList":i},
                success: function (result) {
					console.log(result);
					if(result.errno=="1")
					alert('失败');
					//Vue.delete();
                },
                error : function() {
                    alert("异常！");
                }
            });
		}
		const vueNavBottom = new Vue({
            el: '#showusr',
            data:{
                username:"anounymous"
            }
		});
        var app = new Vue({
            el: ".b",
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
            },
            methods: {
                remove: function(index) { //移除单个
                    if (confirm('你确定要删除吗?')) {
						delcart(this.list[index].pid);
                        this.list.splice(index, 1);
                    }
                },
                removelot: function() { //多选移除
                    var list = []
                    for (let i = 0; i < this.list.length; i++) {
                        if (this.list[i].active == false)
						{
                            list.push(this.list[i])
						}
						else
						{
							delcart(this.list[i].pid);
						}
                    }
                    this.list = list
                },
                removeAll: function() {
                    if (confirm('你确定要删除吗?')) {
						for (let i = 0; i < this.list.length; i++)
						delcart(this.list[i].pid);
                        this.list = []
                    }

                },
				submit: function(){
					var str = "";
					if(this.list.length < 1)
					{
						alert("没有选择商品");
					}
					else{
						for (let i = 0; i < this.list.length; i++) {
							if (this.list[i].active)
								str += this.list[i].pid + ',';
								//sum += this.list[i].price * this.list[i].num;
						}
						if(str.length == 0)
						{
							alert("没有选择商品");
						}
						else{
							jQuery.ajax({
								//几个参数需要注意一下
								async:true,
								type: "POST",//方法类型
								dataType: "json",//预期服务器返回的数据类型
								url: "http://localhost:8080/cart/pay" ,//url
								data: {"username":JSON.parse(sessionStorage.getItem("cuser")).name,"idList":str},
								success: function (result) {
									console.log(result);
									if(result.errno=="1")
									alert('失败');
									//Vue.delete();
								},
								error : function() {
									alert("异常！");
								}
							});
						}
					}
				},
                created: function() {
                    /*var that = this
                    axios.get('待定localhost和具体地址').then(function(resp) {
                        that.list = resp.data; //先默认返回的文件中data是需要的商品数组,数组内的数据格式你根据后端返回的数据调一下
                    })*/
                }
            },
            computed: {
                total: function() { //计算总价
					//alert(this.list.length);
                    let sum = 0;
                    for (let i = 0; i < this.list.length; i++) {
                        if (this.list[i].active)
                            sum += this.list[i].price * this.list[i].num;
                    }
					//alert(sum);
                    return sum;
                },
                count: function() { //计算总数
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