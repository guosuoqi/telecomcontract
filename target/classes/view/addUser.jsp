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
    <script src="/static/js/select.js"></script>
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
    <title>新增用户</title>
</head>

<body>

<div class="container-fluid">
    <form id="userForm" class="form-horizontal">
        <div class="row">
            <div class="col-xs-2">用户姓名:</div>
            <div class="col-xs-4">
                <input class="form-control" name="userName" id="userName" type="text"/>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-2">账号:</div>
            <div class="col-xs-4">
                <input class="form-control" name="loginNumber" id="loginNumber" type="text"/>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-2">密码:</div>
            <div class="col-xs-4">
                <input class="form-control" name="password" id="password" type="password"/>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-2">手机号:</div>
            <div class="col-xs-4">
                <input class="form-control" name="mobile" id="mobile" type="text"/>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-2">联系邮箱:</div>
            <div class="col-xs-4">
                <input class="form-control" name="email" id="email" type="text"/>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-2">选择角色:</div>
            <div class="col-xs-4">
                <select id="roleAdd" name="roleAdd" class="form-control">
                </select>
            </div>
        </div>

    </form>
</div>

</body>
<script type="text/javascript">
    $('#planYear').datetimepicker({
        minView: "month",
        format: "yyyy",
        language: 'zh-CN',
        autoclose:true
    });
    $(function(){
        queryRoleAdd();
    })

    //初始化角色
    function queryRoleAdd(){
        $.ajax({
            url: '/user/queryRole',
            data : "",
            success:function (data){
                var typeHtmlAdd = '<option value="-1">--请选择--</option>';
                for ( var i = 0; i < data.length; i++) {
                    typeHtmlAdd+='<option value="'+data[i].id+'" >'+data[i].name+'</option>';
                }
                $("#roleAdd").html(typeHtmlAdd);
            },
            error:function (){
                alert("指定人员下拉有误,请调试 ！！！");
            }
        })
    }

    //事件转中文
    $('.date').datetimepicker({
        language: 'zh-CN',//显示中文
        format: 'yyyy-mm-dd',//显示格式
        minView: "month",//设置只显示到月份
        initialDate: new Date(),//初始化当前日期
        autoclose: true,//选中自动关闭
        todayBtn: true//显示今日按钮
    });



</script>
</html>

