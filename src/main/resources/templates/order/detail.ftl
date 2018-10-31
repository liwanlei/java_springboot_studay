<html>
<head>
    <meta charset="UTF-8">
    <title>卖家成功提示</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-4 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>订单id</th>
                    <th>订单总金额 </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td> ${orderDto.orderId}</td>
                    <td>${orderDto.orderAmount}</td>
                </tr>

                </tbody>
            </table>
        </div>
        <#--订单详情-->
        <div class="col-md-12 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>商品id </th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>总额</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <#list  orderDto.orderDetailList as orderdetai>
                        <td>${orderdetai.productId}</td>
                        <td>${orderdetai.productName} </td>
                        <td>${orderdetai.productPrice}</td>
                        <td>${orderdetai.productQuantity}</td>
                        <td>${orderdetai.productPrice*orderdetai.productQuantity}</td>
                    </#list>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col-md-12 column">
            <#if orderDto.getOrderStatus().message !="新订单">
            <button type="button" class="btn btn-default btn-primary">
                <a href="/sell/order/finsh?orderId=${orderDto.orderId}">完结订单</a></button>
            <button type="button" class="btn btn-default btn-danger">
                <a href="/sell/order/cancel?orderId=${orderDto.orderId}">取消订单</a></button>
            </#if>
        </div>
    </div>
</div>
</body>