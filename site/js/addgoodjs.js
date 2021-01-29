var app = new Vue({
            el: "#app",
            data: {
                id: 1,
                message: "暗影精灵6到飞起，全新升级10代酷睿处理器！轻薄新外观搭载酷凉风暴散热模组！",
                name: "暗影精灵6",
                bgurl: "img/computer.jpg",
                price: 7800,
            },
            methods: {
                submit: function() 
				{
					var json={};
					//json.append("productName",);
                    console.log($('#app').serialize());//$('#app').serialize()
					//alert('aaa');
					$("#app").ajaxSubmit({url: 'http://localhost:8080/product/add', /*设置post提交到的页面*/
						type: "post", /*设置表单以post方法提交*/
						/* 后端自定义头部验证，可去掉  */
						dataType: "json", /*设置返回值类型为文本*/
						success: function (data) {
							alert("添加成功！");
						},
						error: function (error) 
						{alert('异常');}
					});
					/*$.ajax({
				//几个参数需要注意一下
					type: "POST",//方法类型
					dataType: "json",//预期服务器返回的数据类型
					contentType: false, // 注意这里应设为false
					processData: false,
					url: "http://localhost:8080/product/add" ,//url
					data: $('#app').serialize(),
					success: function (result) {
						//Vue.delete();
					},
					error : function() {
						alert("异常！");
					}
					})*/

				}
			}

        })