<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- 引入样式文件和动态控制 -->
    <script src="/static/bootstrap/jquery-1.9.1.min.js"></script>
    <link href="/static/bootstrap/bootstrap3/css/bootstrap.css" rel="stylesheet">
    <link href="/static/bootstrap/bootstrap-select-1.13.7/dist/css/bootstrap-select.css" rel="stylesheet">
    <link href="/static/bootstrap/bootstrap-table/bootstrap-table.css" rel="stylesheet">
    <link href="/static/css/menuList.css" rel="stylesheet">
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
    <script src="/static/js/select.js"></script>
    <title>用户管理</title>
</head>

<body>
<style type="text/css">
    table {
        table-layout:auto;

    }
</style>
<div class="panel">
    <div class="panel-body" style="padding-bottom: 1px;">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-12 "> <%--<td>--%>
                    <div class="col-sm-4 "> <%--<td>--%>
                        <div class="col-sm-3 "> <%--占4格--%>
                            <label for="userName">用户名称</label>
                        </div>
                        <div class="col-sm-9"> <%--占8格，充满--%>
                            <input type="text" name="userName" id="userName"  class="form-control" placeholder="请输入用户名称">
                        </div>


                        <div class="col-xs-12">
                            <button type="button" class="btn btn-primary btn-w-m" onclick="initUser()" id="queryBtn" style="float: right;margin-right:20px;">
                                <span class="glyphicon glyphicon-search"></span> 搜索
                            </button>
                        </div>
                    </div>
        </form>
    </div>
</div>


<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" style="display: none">开始演示模态框</button>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    请更改角色
                </h4>
            </div>
            <div class="modal-body">
                <input id="hiddenUserId" type="hidden">
                <label for="role">角色分类</label>
                <select id="role" class="selectpicker form-control" multiple  data-live-search="true"  >
                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="submitRole()">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 按钮触发模态框 -->
<%--<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    开始演示模态框
</button>--%>
<!-- 模态框（Modal） -->
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myUserModal" style="display: none">开始演示模态框</button>
<div class="modal fade" id="myUserModal" tabindex="-1" role="dialog" aria-labelledby="myUserModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="buttonAdd" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" >
                    新增用户
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-2">用户姓名:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="id" id="id" type="hidden"/>
                        <input class="form-control" name="userName" id="userName1" type="text"/>
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
                    <div class="col-xs-2">负责辖区:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="county" id="county" type="text"/>
                    </div>
                </div>
                <div class="row" id="roleDiv">
                    <div class="col-xs-2">选择角色:</div>
                    <div class="col-xs-4">
                        <select id="roleAdd" name="roleAdd" class="selectpicker"  multiple  data-live-search="true" >
                        </select>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="buttonAdd" class="btn btn-primary" onclick="submitUser()">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<button type="button" onclick="openAddUser()" class="btn btn-info glyphicon glyphicon-plus">新增用户</button>
<button type="button" onclick="delUser()" class="btn btn-danger glyphicon glyphicon-minus">删除用户</button>
</div>
<table id="myTable"></table>
<table id="myRole"></table>


</body>
<script type="text/javascript">
    <!--初始化加载页面-->
    $(function(){
        initUser();
        queryRole();
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
     //打开用户管理页面
    function initUser(){
        $('#myTable').bootstrapTable('destroy');
        $("#myTable").bootstrapTable({
            url:'/user/queryUser',//获取数据地址
            method: 'post',
            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
            pagination:true, //是否展示分页
            pageList:[5, 10, 20, 50],//分页组件
            pageNumber:1,
            pageSize:5,//默认每页条数
            //search:true,//是否显示搜索框
            //searchText:'试试',//初始化搜索文字
            showColumns:false,//是否显示 内容列下拉框
            showToggle:false,//是否显示 切换试图（table/card）按钮
            showPaginationSwitch:false,//是否显示 数据条数选择框
            showRefresh:false,//是否显示刷新按钮
            detailView:false,//设置为 true 可以显示详细页面模式。
            showFooter:false,//是否显示列脚
            clickToSelect: true, //是否启用点击选中行
            sidePagination:'server',//分页方式：client客户端分页，server服务端分页（*
            striped:true,
            queryParams:function(){
                return {
                    page: this.pageNumber,
                    rows: this.pageSize,
                    userName:$('#userName').val(),
                }
            },

            columns:[
                {field:'333',checkbox:true,align: 'left',width:"20px",valign: 'middle'},
                {field:'id',title:'用户id',align: 'center',width:"40px",valign: 'middle',visible:false},
                {field:'loginNumber',title:'用户账号',align: 'center',valign: 'middle'},
                {field:'userName',title:'用户名称',align: 'center',valign: 'middle'},
                {field:'county',title:'负责辖区',align: 'center',valign: 'middle'},
                {field:'role',title:'用户角色',align: 'center',valign: 'middle'},
                {field:'mobile',title:'手机号',align: 'center',valign: 'middle'},
                {field:'email',title:'邮箱',align: 'center',valign: 'middle'},
                {field:'111',title:' 操作 ' ,class:'table-width',valign: 'middle',formatter:function(value,row,index){
                        return   ' <a href="javascript:editUser(\''+row.id+'\',\'' + row.loginNumber + '\',\'' + row.userName + '\',\'' + row.county + '\',\'' + row.mobile + '\',\'' + row.email + '\')"> 修改用户</a>|<a href="javascript:editRole(\'' + row.id + '\');">修改角色</a> ';
                    }}
            ]
        })
    }
    function editUser(id,loginNumber,userName,county,mobile,email){
        $("#id").val(id);
        $("#loginNumber").val(loginNumber);
        $("#userName1").val(userName);
        $("#county").val(county);
        $("#mobile").val(mobile);
        $("#email").val(email);
        $("#role").val(role);
        $("#roleDiv").hide();
        $('#myUserModal').modal();
    }
    function openAddUser(){
        queryRole();
        $("#id").val("");
        $("#loginNumber").val("");
        $("#userName1").val("");
        $("#password").val("");
        $("#county").val("");
        $("#mobile").val("");
        $("#email").val("");
        $("#roleDiv").show();
        $('#myUserModal').modal();
    }

    //打开角色模态框
    function editRole(id){
        $.ajax({
            url: '/user/queryRoleByUserId',
            data : {
                userId:id,
            },
            success:function (data){
                $('#myModal').modal();
                $('#role').selectpicker('val',data);
                $('#hiddenUserId').val(id);
            }
        })
    }
    //初始化角色
    function queryRole(){
        $(".selectpicker").selectpicker({
            noneSelectedText: '--请选择--' //默认显示内容  
        });
        $.ajax({
            url: '/user/queryRole',
            data : "",
            success:function (data){
                var typeHtml = '<option value="-1">--请选择--</option>';
                for ( var i = 0; i < data.length; i++) {
                    typeHtml+='<option value="'+data[i].id+'" data="'+data[i].name+'">'+data[i].name+'</option>';
                }
                $("#role").html(typeHtml);
                $("#roleAdd").html(typeHtml);
                $('#role').selectpicker('refresh');
                $('#roleAdd').selectpicker('refresh');

                $('#role').selectpicker('render');
                $('#roleAdd').selectpicker('render');
            },
            error:function (){
                alert("指定人员下拉有误,请调试 ！！！");
            }
        })
    }
    //提交指定角色
    function submitRole(){
        var obj = document.getElementById("role");
        var roleIds='';
        var roleName="";
        for (var i = 0; i < obj.options.length; i++) {
            if (obj.options[i].selected) {
                roleIds += roleIds == '' ? obj.options[i].value : ',' + obj.options[i].value;
            }
            if (obj.options[i].selected) {
                roleName += roleName == '' ? obj.options[i].text : ',' + obj.options[i].text;
            }
        }
        $.post('/user/saveUserRole',{userId:$("#hiddenUserId").val(),roleId:roleIds,role:roleName},function(data) {
            if(data==1){
                initUser();
                alert("更改角色成功")
                $("#myModal").modal('hide');
            }else {
                alert("更改角色失败");
            }
        })
    }
    //提交用户
    function submitUser(){
        var obj = document.getElementById("roleAdd");
        var roleid="";
        var roleName="";
        for (var i = 0; i < obj.options.length; i++) {
            if (obj.options[i].selected) {
                roleName += roleName == '' ? obj.options[i].text : ',' + obj.options[i].text;
                roleid += roleid == '' ? obj.options[i].value : ',' + obj.options[i].value;
            }
        }
        var val = $("#loginNumber").val();
        var pwd = $("#password").val();
        var regex = new RegExp('(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9])(?=.*[^`~!@#$%^&*()_+<>?:"{},.]).{8,30}');
        if (!regex.test(pwd)) {
            alert("您的密码复杂度太低（密码中必须包含字母、数字、特殊字符,长度8-30），请及时改密码！");
            return;
        }
        var loginNumberregex = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z]).{8,30}');
        if (!loginNumberregex.test(val)) {
            alert("您的账号复杂度太低（密码中必须包含字母、数字,长度8-30），请及时改密码！");
            return;
        }
       document.getElementById('buttonAdd').disabled=true;
        $.ajax({
            url: '/user/addUser',
            type: "post",
            data : {
                id:$("#id").val(),
                loginNumber:$("#loginNumber").val(),
                password:$("#password").val(),
                userName:$("#userName1").val(),
                mobile:$("#mobile").val(),
                email:$("#email").val(),
                county:$("#county").val(),
                role:roleName,
                roleId:roleid
            },
            success:function (data){
                initUser();
                alert(data.msg)
                $("#myUserModal").modal('hide');
                document.getElementById('buttonAdd').disabled=false;
            },
            error:function (){
                alert("新增用户失败");
            }
        })
    }

    //批量删除
    function delUser(){
        var arr = $('#myTable').bootstrapTable('getSelections');
        if (arr.length <= 0) {
            bootbox.alert({
                size: "small",
                title: "提示",
                message: "请选择需要删除的数据",
                callback: function(){
                }
            });
            return;
        }
        bootbox.confirm({
            size: "small",
            message: "你确定要删除吗?",
            buttons: {
                confirm: {
                    label: '确定',
                    className: 'btn-success'
                },
                cancel: {
                    label: '取消',
                    className: 'btn-danger'
                }
            },
            callback: function(result){
                if (result) {
                    var ids = "";
                    for (var i = 0; i < arr.length; i++) {
                        ids += ids == "" ? arr[i].id : ","+arr[i].id;
                    }
                    $.ajax({
                        url:"/user/delUser",
                        data:{ids:ids},
                        success:function(result){
                            alert(result.msg);
                            if(result.code == '0'){
                                initUser();
                            }
                        },
                        error:function(data){
                            alert("检查后台代码")
                        }
                    })
                }
            }
        })
    }
</script>
</html>

