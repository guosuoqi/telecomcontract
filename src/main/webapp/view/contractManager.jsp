<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- 引入样式文件和动态控制 -->
    <link href="/static/bootstrap/bootstrap3/css/bootstrap.css" rel="stylesheet">
    <link href="/static/bootstrap/bootstrap-select-1.13.7/dist/css/bootstrap-select.css" rel="stylesheet">
    <link href="/static/bootstrap/bootstrap-table/bootstrap-table.css" rel="stylesheet">
    <link href="/static/css/menuList.css" rel="stylesheet">
    <script src="/static/bootstrap/jquery-1.9.1.min.js"></script>
    <script src="/static/js/chat.js"></script>
    <script src="/static/bootstrap/bootstrap3/js/bootstrap.js"></script>
    <script src="/static/bootstrap/bootstrap-table/bootstrap-table.js"></script>
    <script src="/static/bootstrap/bootstrap-select-1.13.7/js/bootstrap-select.js"></script>
    <script src="/static/bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <link href="/static/bootstrap/bootstrap-treeview/bootstrap-treeview.min.css" rel="stylesheet">
    <script src="/static/bootstrap/bootstrap-treeview/bootstrap-treeview.min.js"></script>
    <link href="/static/bootstrap/bootStrap-addTabs/bootstrap.addtabs.css" rel="stylesheet">
    <script src="/static/bootstrap/bootStrap-addTabs/bootstrap.addtabs.min.js"></script>
    <script src="/static/bootstrap/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script src="/static/bootstrap/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <link rel="stylesheet" href="/static/bootstrap/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css">
    <script src="/static/bootstrap/bootstrap-bootbox/bootbox.js"></script>
    <title>合同续约</title>
</head>

<body>
<!--左1-->
<div style="width:25%;height:100px;background-color:#ccffff;float:left;margin-left:10%;margin-top:20px">
    <div style="width:50%;height:50%;margin-top:10px;margin-left:6%;">
        <div style="width:80%;height:50%;margin-top:10px;margin-left:50%;">
            <font size='3'>合同总数</font>
        </div>
    </div>
    <div style="width:80%;height:50%;margin-top:10px;margin-left:40%"id="contractCount">
    </div>
</div>
<div style="width:25%;height:100px;background-color:#ccffff;float:left;margin-left:10%;margin-top:20px">
    <%--<a href="合同续约.html" target="rightFrame">--%><div style="width:80%;height:50%;margin-top:10px;margin-left:25%;">
        <font size='3'>即将到期合同数 </font>
    </div>
        <div style="width:80%;height:50%;margin-top:10px;margin-left:45%;" id="extensionNum">
        </div><%--</a>--%>
</div>
<div style="width:25%;height:100px;background-color:#ccffff;float:left;margin-left:10%;margin-top:20px">
    <div style="width:50%;height:50%;margin-top:10px;margin-left:20%;">
        <center> <font size='3'>现有站点数量</font></center>
    </div>
    <div style="width:50%;height:50%;margin-top:1px;margin-left:40%;" id="siteCount">
    </div>
</div>
<div style="width:25%;height:100px;background-color:#ccffff;float:left;margin-left:10%;margin-top:20px">
    <div style="width:80%;height:50%;margin-top:10px;margin-left:5%;">
         <font size='3'><center>待续费合同数</center></font>
    </div>
    <div style="width:40%;height:50%;margin-top:10px;margin-left:45%;" id="renewNum">
    </div>
</div>
<div style="width:25%;height:100px;background-color:#ccffff;float:left;margin-left:10%;margin-top:20px">
    <div style="width:50%;height:50%;margin-top:10px;margin-left:20%;">
        <center> <font size='3'>现有设备数量</font></center>
    </div>
    <div  style="width:40%;height:50%;margin-top:10px;margin-left:45%;"id="shebeiSum">
    </div>
</div>
<%--<div style="width:25%;height:100px;background-color:#ff0000;float:left;margin-left:10%;margin-top:20px">
    <a href="合同续约.html" target="rightFrame">
        <div style="width:80%;height:40%;margin-top:10px;margin-left:10%;">
            <center><font size='3'>租赁费代缴费站点数</font></center>
        </div>
        <div style="width:40%;height:40%;margin-top:10px;margin-left:30%;">
        </div>
    </a>
</div>--%>
</div>

<table id="contractRenew"></table>
</body>
<script type="text/javascript">
    <!--初始化加载页面-->
    $(function(){
        initContractManager();
    })
    //事件转中文
    $('.date').datetimepicker({
        language: 'zh-CN',//显示中文
        format: 'yyyy-mm-dd',//显示格式
        minView: "month",//设置只显示到月份
        initialDate: new Date(),//初始化当前日期
        autoclose: true,//选中自动关闭
        todayBtn: true//显示今日按钮
    });

function initContractManager(){
    $.ajax({
        url:"/contract/queryContractCount",
        data:{},
        success:function(result){
           $("#contractCount").html(result.countNum);
           $("#siteCount").html(result.siteCountNum);
           $("#extensionNum").html(result.extensionNum);
           $("#renewNum").html(result.renewNum);
           $("#shebeiSum").html(result.shebeiSum);
        },
        error:function(data){
            alert("检查后台代码")
        }
    })

}

</script>
</html>

