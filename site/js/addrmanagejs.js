function popBox(op) {
			sessionStorage.setItem("popboxstatus",op);
            var popBox = document.getElementById("popBox");
            var popLayer = document.getElementById("popLayer");
            popBox.style.display = "block";
            popLayer.style.display = "block";
        };

        /*点击关闭按钮*/
        function closeBox() {
            var popBox = document.getElementById("popBox");
            var popLayer = document.getElementById("popLayer");
            popBox.style.display = "none";
            popLayer.style.display = "none";
        }
        //地址管理
		function getaddlist()
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
                type: "GET",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/addr/list" ,//url
                data: {"username":tmpuser},
                success: function (result) {
					console.log(result);
					while(vueAddress.addressList.pop());
					for(var u of result)
					{
						var json = {};
						json["aid"]=u.id;
						json["name"]=u.name;
						json["detailAddress"]=u.addr;
						json["isDefault"]=u.isdefault;
						vueAddress.addressList.push(json);
					}
					//Vue.delete();
                },
                error : function() {
                    alert("异常！");
                }
            });
		}
		function defaultadd(aid)
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
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/addr/setdefault" ,//url
                data: {"username":tmpuser,"aid":aid},
                success: function (result) {
					//Vue.delete();
                },
                error : function() {
                    alert("异常！");
                }
            });
		};
		function addbutton()
		{
			sessionStorage.removeItem("changingaid");
			popBox();
		}
		function addadd()
		{
			var tmpuser = JSON.parse(sessionStorage.getItem("cuser")).name;
			var addd = showAddr();
			var aid = sessionStorage.getItem("changingaid");
			console.log(aid);
			if(aid)
			{
				alert(aid);
				sessionStorage.removeItem("changingaid");
				var $=jQuery.noConflict();
				var finaladd = addd + $("#dinput").val();
				closeBox();
				$.ajax({
				//几个参数需要注意一下
					async: false, //等待请求结束后刷新列表……
					type: "POST",//方法类型
					dataType: "json",//预期服务器返回的数据类型
					url: "http://localhost:8080/addr/upd" ,//url
					data: {"aid":aid,"addr":finaladd},
					success: function (result) {
						//Vue.delete();
					},
					error : function() {
						alert("异常！");
					}
				});
				getaddlist();
				return;
			}
			else{
				var $=jQuery.noConflict();
				var finaladd = addd + $("#dinput").val();
				closeBox();
				$.ajax({
				//几个参数需要注意一下
					async: false, //等待请求结束后刷新列表……
					type: "POST",//方法类型
					dataType: "json",//预期服务器返回的数据类型
					url: "http://localhost:8080/addr/add" ,//url
					data: {"username":tmpuser,"addr":finaladd},
					success: function (result) {
						//Vue.delete();
					},
					error : function() {
						alert("异常！");
					}
				});
				getaddlist();
			//alert(finaladd);
			}
			
		};
		function deladd(aid)
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
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8080/addr/del" ,//url
                data: {"aid":aid},
                success: function (result) {
					//Vue.delete();
                },
                error : function() {
                    alert("异常！");
                }
            });
		};
		var $=jQuery.noConflict();
		$(document).ready(function(){
			vueNavBottom.username= JSON.parse(sessionStorage.getItem("cuser")).name;
		});
		const vueNavBottom = new Vue({
            el: '#showusr',
            data:{
                username:"anounymous"
            }
		});
        const vueAddress = new Vue({
            el: '#vue-address',
            data() {
                return {
                    addressList: [], //地址列表
                }
            },
            created() {
                this.getAddressJson();
				//this.topDefault();
            },
            methods: {
                //获取地址列表数据
                getAddressJson() {
				getaddlist();
                    /*let url = 'json/addressTest.json';
                    axios.get(url)
                        .then(response => {
                            this.addressList = response.data.list;
                            console.log(this.addressList);
                        })
                        .catch(error => {
                            console.log(error)
                        })*/
					
                },

                //设置默认地址
                setDefault(i) {
                    const addressList = this.addressList;
					var aid = addressList[i].aid;
					defaultadd(aid);
                    addressList.forEach((item, index) => {
                        item.isDefault = index == i;
						
						//alert(i);
                    });
                    addressList.splice(0, 0, ...addressList.splice(i, 1));
                },
				
				topDefault()
				{
					const addressList = this.addressList;
					var t=0;
					addressList.forEach((item, index) => {
						console.log(item);
						alert(item);
                        if(item.isDefault)
							t=index;
						
						//alert(i);
                    });
					alert(t);
					addressList.splice(0, 0, ...addressList.splice(t, 1));
				},
				deladd(i) 
				{
					const addressList = this.addressList;
					if(window.confirm('你确定要删除？'))
					{
						var aid = addressList[i].aid;
						deladd(aid);
						addressList.splice(i,1);
					}
                },
				modadd(i)
				{
					const addressList = this.addressList;
					var aid = addressList[i].aid;
					sessionStorage.setItem("changingaid",aid);
					popBox();
				}
            }
        });