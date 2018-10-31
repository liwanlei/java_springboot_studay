<html>
<head>
    <meta charset="UTF-8">
    <title>卖家商品列表</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th> 订单id</th>
                    <th> 姓名</th>
                    <th> 手机号</th>
                    <th> 地址 </th>
                    <th>金额</th>
                    <th> 订单状态</th>
                    <th>支付状态</th>
                    <th>创建时间</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list  orderDaopage.content as orderDto>
                <tr>
                    <td>${orderDto.orderId}</td>
                    <td> ${orderDto.buyerName}</td>
                    <td> ${orderDto.buyerPhone}</td>
                    <td>${orderDto.buyerAddress}</td>
                    <td>${orderDto.orderAmount} </td>
                    <td>${orderDto.getOrderStatus()} </td>
                    <td>${orderDto.getPayStatus()}</td>
                    <td>${orderDto.createTime}</td>
                    <td>
                        <a href="/sell/order/orderdetail?orderId=${orderDto.orderId}">详情</a></td>

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

            <div class="modal fade" id="mymodal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                                提醒
                            </h4>
                        </div>
                        <div class="modal-body">
                            你有新的订单
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="location.reload()">查看详情</button>
                        </div>
                    </div>

                </div>

            </div>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
    var websocket=null;
    console.log(window);
    if("WebSocket" in window){
       websocket=new WebSocket('ws://127.0.0.1:9000/websocket');
    }else{
      alert("该浏览器不支持");
    }
    websocket.onopen=function(event){
    }
    websocket.onclose=function(event){

    }
    websocket.onmessage=function(event){
    console.log("收到消息:"+event.data);
    $('#mymodal').modal('show');
    }
    websocket.onerror=function(){
    alert("");
    }
    window.onbeforeunload=function(){
       websocket.close();
    }
</script>
</body>
</html>