<html>
<head>
    <meta charset="UTF-8">
    <title>商品列表</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th> 商品id</th>
                    <th> 名称</th>
                    <th> 图片</th>
                    <th> 单价 </th>
                    <th>描述</th>
                    <th> 库存</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list  productInfos.content as productinfo>
                <tr>
                    <td>${productinfo.productId}</td>
                    <td> ${productinfo.productName}</td>
                    <td> ${productinfo.productIcon}</td>
                    <td>${productinfo.productPrice}</td>
                    <td>${productinfo.productDescription} </td>
                    <td>${productinfo.productStock} </td>
                    <td>
                        <a href="/sell/product/orderdetail?orderId=${productinfo.productId}">修改</a></td>
                    <td>
                        <!--<#if orderDto.getOrderStatus().message !="新订单">-->
                        <a href="/sell/product/cancel">上架</a>
                        <!--<#else>-->

                    <!--</#if>-->

                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
            <div class="col-md-12 column">
                <ul class="pagination pull-right">
                    <#if curetPage lte 1>
                        <li class="disabled">
                            <a href="#">上一页</a>
                        </li>
                    <#else >
                    <li>
                        <a href="/sell/order/list?page=${curetPage-1}&size=10">${index}</a>
                    </li>
                    </#if>

                    <#list  1..orderDaopage.getTotalPages() as index>

                            <li>
                                <a href="/sell/order/list?page=${index}&size=10">${index}</a>
                            </li>
                    </#list>
                    <#if curetPage gte orderDaopage.getTotalPages()>
                        <li class="disabled">
                            <a href="#">下一页</a>
                        </li>
                    <#else >
                    <li >
                        <a href="/sell/order/list?page=${curetPage+1}&size=10">下一页</a>
                    </li>
                    </#if>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>