# 数据库

用户（id，用户名，密码，资料，余额，角色【系统用户1，管理员2，游客3】）

地址（id，用户名，地址， 是否默认）

商品（id，商品名称，商品类别，商品图片，商品详情，状态【上架，下架】，销量）

订单（id，用户名，商品id，状态【1-未付款，2-已付款，3-已取消】，开始时间，结束时间）

购物车（id，用户名，商品id， 数量）

mysql用户名root密码action123

# 前台功能

## 用户登录 POST /user/login

request

| 字段     | 类型   | 解释 |
| -------- | ------ | ---- |
| username | string |      |
| password | string |      |

response

|       |      |                     |
| ----- | ---- | ------------------- |
| errno | int  | 错误码，0表示无错误 |
| data  | User | 用户信息            |

## 注册 POST /user/register

request

|           |        |                       |
| --------- | ------ | --------------------- |
| username  | string |                       |
| password  | string |                       |
| checkpass | string |                       |
| detail    | string | 详细信息              |
| role      | int    | 1系统用户2管理员3游客 |

response

|       |        |                     |
| ----- | ------ | ------------------- |
| errno | int    | 错误码，0表示无错误 |
| error | string | 错误描述            |

## 修改密码 POST /user/changepass

request

| 字段     | 类型   | 解释   |
| -------- | ------ | ------ |
| username | string |        |
| password | string | 原密码 |
| newpass  | string | 新密码 |

response

|      |             |               |
| ---- | ----------- | ------------- |
| data | UserAndAddr | 用户信息+地址 |

## 个人资料 GET /user/get

request

|      |      |        |
| ---- | ---- | ------ |
| uid  | int  | 用户id |

response

|      |             |               |
| ---- | ----------- | ------------- |
| data | UserAndAddr | 用户信息+地址 |

## PS. 自定义的数据结构

user的结构

```
{
	"id": 1,
    "name": "dzy",
    "pass": "123",
    "detail": "hahahahaha",	
    "balance": 100.0,
    "role": 1
}
```

UserAndAddr的结构

```json
{
    "id": 1,
    "name": "dzy",
    "pass": "321",
    "detail": "hahahahaha",
    "balance": 97.0,
    "role": 1,
    "addr": [
        {
            "id": 1,
            "name": "dzy",
            "addr": "testaddr1",
            "isdefault": 1
        },
        {
            "id": 2,
            "name": "dzy",
            "addr": "updaddr2",
            "isdefault": 0
        }
    ]
}
```

# 商品展示

## 热销商品展示 GET /product/list

request

|          |      |        |
| -------- | ---- | ------ |
| pageSize | int  | 页大小 |
| pageNum  | int  | 第几页 |

response

|             |             |            |
| ----------- | ----------- | ---------- |
| total       | int         | 商品总数   |
| pageSize    | int         | 页大小     |
| pageNum     | int         | 第几页     |
| productList | Product数组 | 该页的商品 |

## 分类商品展示 GET /product/list/type

request

|             |        |          |
| ----------- | ------ | -------- |
| productType | string | 商品类型 |
| pageSize    | int    | 页大小   |
| pageNum     | int    | 第几页   |

response

|             |             |            |
| ----------- | ----------- | ---------- |
| total       | int         | 商品总数   |
| pageSize    | int         | 页大小     |
| pageNum     | int         | 第几页     |
| productList | Product数组 | 该页的商品 |

## 商品详情 GET /product/get

request

|           |      |        |
| --------- | ---- | ------ |
| productId | int  | 商品id |

response

|         |         |          |
| ------- | ------- | -------- |
| product | Product | 商品对象 |

## 辅助接口

### 获取所有商品分类 GET /product/types

request

|            |      |      |
| ---------- | ---- | ---- |
| 不需要参数 |      |      |

response

|          |            |            |
| -------- | ---------- | ---------- |
| typeList | string数组 | 所有的分类 |

## PS. 自定义的数据结构

Product

```
{
    "id": 3,
    "name": "name4",
    "ptype": "type2",
    "pic": "1.png, 2.png",
    "detail": "detail",
    "price": 3.0,
    "status": 1,
    "salecnt": 1
}
```

# 购物车

## 把商品添加到购物车 POST /cart/add

request

|          |        |          |
| -------- | ------ | -------- |
| username | string | 用户名   |
| pid      | int    | 商品id   |
| cnt      | int    | 商品数量 |

response

|       |      |            |
| ----- | ---- | ---------- |
| errno | int  | 0成功1失败 |

## 编辑购物车 POST /cart/edit

request

|          |        |                                  |
| -------- | ------ | -------------------------------- |
| idList   | string | 选中要删除的商品的id，用逗号隔开 |
| username | string | 用户名                           |

response

|       |      |            |
| ----- | ---- | ---------- |
| errno | int  | 0成功1失败 |

## 结算 POST /cart/pay

request

|          |        |                          |
| -------- | ------ | ------------------------ |
| username | string | 用户名                   |
| idList   | string | 选中商品的id，用逗号隔开 |

response

|       |      |            |
| ----- | ---- | ---------- |
| errno | int  | 0成功1失败 |

## 列出购物车内所有商品 GET /cart/list

request

|          |        |        |
| -------- | ------ | ------ |
| username | string | 用户名 |

response 示例

```json
[
    {
        "id": 1,	// 购物车项id
        "count": 2,
        "product": {
            "id": 3,
            "name": "name4",
            "ptype": "type2",
            "pic": "1.png, 2.png",
            "detail": "detail",
            "price": 3.0,
            "status": 1,
            "salecnt": 1
        }
    },
    {
        "id": 2,
        "count": 1,
        "product": {
            "id": 4,
            "name": "name5",
            "ptype": "type2",
            "pic": "1.png, 2.png",
            "detail": "detail",
            "price": 3.0,
            "status": 1,
            "salecnt": 1
        }
    }
]
```

# 订单

## 订单列表 GET /order/list

request

|          |        |        |
| -------- | ------ | ------ |
| username | string | 用户名 |

response 实例

```json
[
    {
        "orderId": 1,
        "username": "dzy",
        "product": {
            "id": 3,
            "name": "name4",
            "ptype": "type2",
            "pic": "1.png, 2.png",
            "detail": "detail",
            "price": 3.0,
            "status": 1,
            "salecnt": 1
        },
        "num": 2,
        "status": 2,	// 1-未支付 2-已支付 3-已取消
        "startTime": "2020-12-31 16:23:34",
        "endTime": "2020-12-31-19:57:49"
    },
    {
        "orderId": 2,
        "username": "dzy",
        "product": {
            "id": 4,
            "name": "name5",
            "ptype": "type2",
            "pic": "1.png, 2.png",
            "detail": "detail",
            "price": 3.0,
            "status": 1,
            "salecnt": 1
        },
        "num": 1,
        "status": 1,
        "startTime": "2020-12-31 16:23:34",
        "endTime": null
    },
    {
        "orderId": 5,
        "username": "dzy",
        "product": {
            "id": 5,
            "name": "name6",
            "ptype": "type3",
            "pic": "1.png, 2.png",
            "detail": "detail",
            "price": 3.0,
            "status": 1,
            "salecnt": 1
        },
        "num": 1,
        "status": 2,
        "startTime": "2020-12-31-19:26:16",
        "endTime": "2020-12-31-19:57:49"
    }
]
```

# 地址管理

## 列出用户的所有地址 GET /addr/list

request

|          |      |        |
| -------- | ---- | ------ |
| username | int  | 用户id |

response 示例

```json
[
    {
        "id": 1,
        "name": "dzy",
        "addr": "testaddr1",
        "isdefault": 1
    },
    {
        "id": 2,
        "name": "dzy",
        "addr": "updaddr2",
        "isdefault": 0
    },
    {
        "id": 3,
        "name": "dzy",
        "addr": "testaddr3",
        "isdefault": 0
    }
]
```

## 增加地址 POST /addr/add

request

|          |        |            |
| -------- | ------ | ---------- |
| username | int    | 用户id     |
| addr     | string | 新增的地址 |

response

|       |      |            |
| ----- | ---- | ---------- |
| errno | int  | 0成功1失败 |

## 删除地址 POST /addr/del

request

|      |      |        |
| ---- | ---- | ------ |
| aid  | int  | 地址id |

response

|       |      |            |
| ----- | ---- | ---------- |
| errno | int  | 0成功1失败 |

## 修改地址 POST /addr/upd

request

|      |        |        |
| ---- | ------ | ------ |
| aid  | int    | 地址id |
| addr | string | 新地址 |

response

|       |      |            |
| ----- | ---- | ---------- |
| errno | int  | 0成功1失败 |

## 设置默认地址 POST /addr/setdefault

request

|          |        |        |
| -------- | ------ | ------ |
| aid      | int    | 地址id |
| username | string | 用户名 |

response

|       |      |            |
| ----- | ---- | ---------- |
| errno | int  | 0成功1失败 |

# 支付 POST /pay

request

|           |        |          |
| --------- | ------ | -------- |
| productId | int    | 商品id   |
| username  | string | 用户名   |
| cnt       | int    | 商品个数 |

response

|       |      |            |
| ----- | ---- | ---------- |
| errno | int  | 0成功1失败 |

这里的支付是直接在商品页付款，相当于立即购买；而购物车中的结算是支付购物车中的选中项。

# 充值 POST /user/recharge

request

|          |        |          |
| -------- | ------ | -------- |
| money    | double | 充值金额 |
| username | string | 用户名   |

response 示例

```json
{
    "id": 1,
    "name": "dzy",
    "pass": "321",
    "detail": "hahahahaha",
    "balance": 107.0,
    "role": 1,
    "addr": [
        {
            "id": 1,
            "name": "dzy",
            "addr": "testaddr1",
            "isdefault": 1
        },
        {
            "id": 2,
            "name": "dzy",
            "addr": "updaddr2",
            "isdefault": 0
        }
    ]
}
```



# 商品管理

## 查询所有商品 GET /product/list

同 [商品展示 -> 热销商品展示 GET]

## 查询商品 GET /product/get

同 [商品展示 -> 商品详情 GET]

## 新增商品 POST

request

|         |         |                                       |
| ------- | ------- | ------------------------------------- |
| product | Product | 商品对象,其中的id属性为要修改的商品id |

response

|         |         |                   |
| ------- | ------- | ----------------- |
| product | Product | 新增的product对象 |

## 修改商品 POST

request

|         |         |                                       |
| ------- | ------- | ------------------------------------- |
| product | Product | 商品对象,其中的id属性为要修改的商品id |

response

|         |         |                     |
| ------- | ------- | ------------------- |
| product | Product | 更新后的product对象 |

## 上下架商品 POST /product/onstore

request

|           |      |            |
| --------- | ---- | ---------- |
| productId | int  | 商品id     |
| stat      | int  | 1上架0下架 |

response

|         |         |                     |
| ------- | ------- | ------------------- |
| product | Product | 更新后的product对象 |


