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
                            <input type="text" name="userName" id="userName"  class="form-control" placeholder="请输入合同名称">
                        </div>


                        <div class="col-xs-12">
                            <button type="button" class="btn btn-primary btn-w-m" onclick="initExtension()" id="queryBtn" style="float: right;margin-right:20px;">
                                <span class="glyphicon glyphicon-search"></span> 搜索
                            </button>
                        </div>
                    </div>
        </form>
    </div>
</div>


<!-- 按钮触发模态框 -->
<%--<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    开始演示模态框
</button>--%>
<!-- 模态框（Modal） -->
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

<button type="button" onclick="openAddDialog()" class="btn btn-info glyphicon glyphicon-plus">新增</button>
<button type="button" onclick="delContract()" class="btn btn-danger glyphicon glyphicon-minus">删除</button>
<button type="button" onclick="EXPContract()" class="btn btn-danger glyphicon">导出</button>
<button type="button" onclick="RoleManger()" class="btn btn-danger glyphicon">角色查询页面</button>
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
                {field:'id',title:'用户id',align: 'center',width:"40px",valign: 'middle'},
                {field:'loginNumber',title:'用户账号',align: 'center',valign: 'middle'},
                {field:'userName',title:'用户名称',align: 'center',valign: 'middle'},
                {field:'mobile',title:'手机号',align: 'center',valign: 'middle'},
                {field:'email',title:'邮箱',align: 'center',valign: 'middle'},
                {field:'userstate',title:'用户状态',align: 'center',valign: 'middle'},
                {field:'111',title:' 操作 ' ,class:'table-width',valign: 'middle',formatter:function(value,row,index){
                        return   ' <a href="javascript:editRole('+row.id+');">修改</a>  ';
                    }}
            ]
        })
    }
    /*  //打开角色管理页面
      function initRole(){
          $('#myRole').bootstrapTable('destroy');
          $("#myRole").bootstrapTable({
              url:'/user/queryRole',//获取数据地址
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
                  {field:'id',title:'角色编号',align: 'center',width:"40px",valign: 'middle'},
                  {field:'name',title:'角色',align: 'center',valign: 'middle'},
                  {field:'111',title:' 操作 ' ,class:'table-width',valign: 'middle',formatter:function(value,row,index){
                          return   ' <a href="javascript:editRole('+row.id+');">绑定权限</a>  ';
                      }}
              ]
          })
      }*/
    //打开角色模态框
    function editRole(id){
        $.ajax({
            url: '/user/queryRoleByUserId',
            type: "get",
            data : {
                userId:id
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
                $('#role').selectpicker('refresh');

                $('#role').selectpicker('render');
            },
            error:function (){
                alert("指定人员下拉有误,请调试 ！！！");
            }
        })
    }

    //提交指定角色
    function submitRole(){
        $.ajax({
            url: '/user/saveUserRole',
            type: "post",
            data : {
                ids:$("#role").val(),
                userId:$("#hiddenUserId").val()
            },
            success:function (data){
                if(data==1){
                    initTable();
                    $("[data-dismiss='modal']").click();
                    alert("更改指定人成功")
                }else {
                    alert("指定人员下拉有误,请调试 ！！！");
                }
            }
        })

    }



    //新增修改路径
    var res;
    function createAddUser(url){
        $.ajax({
            url:url,
            async:false,
            success:function(data){
                res = data;
            }
        });
        return res;
    }
//打开新增用户的弹框
    function openAddDialog(){
        bootbox.dialog({
            size:"big",
            title:"添加用户",
            message:createAddUser("/page/toAddUser"),
            closeButton:true,
            buttons:{
                'success':{
                    "label" : "<i class='icon-ok'></i> 保存",
                    "className" : "btn-sm btn-success",
                    "callback" : function() {
                        var str = $("#role").val();
                        $.ajax({
                            url:'/user/addUser',
                            type:'post',
                            data: $("#userForm").serialize()+str,
                            dataType:'json',
                            success:function(data){
                                bootbox.alert({
                                    size:"small",
                                    title:"提示",
                                    message:data.msg
                                })
                                $('#myTable').bootstrapTable('refresh');
                            }
                        })
                    }
                },
                'cancel':{
                    "label" : "<i class='icon-info'></i> 取消",
                    "className" : "btn-sm btn-danger",
                }
            }
        })
    }
//打开修改的弹框
    function editContract(contractId,roomType,towerType,contractType){
        $("#typeHidIdOne").val(roomType);
        $("#typeHidIdTwo").val(towerType);
        $("#typeHidIdThree").val(contractType);
        bootbox.dialog({
            size:"big",
            title:"修改合同信息",
            message:createAddContent("/page/toUpdateContract?contractId="+contractId),
            closeButton:true,
            buttons:{
                'success':{
                    "label" : "<i class='icon-ok'></i> 保存",
                    "className" : "btn-sm btn-success",
                    "callback" : function() {
                        var str = "&towerTypeName="+$("#towerUp option:selected").text()+
                            "&contractTypeName="+$("#contractUp option:selected").text()+
                            "&roomTypeName="+$("#roomUp option:selected").text();
                        $.ajax({
                            url:'/contract/updateContract',
                            type:'post',
                            data:$("#contractForm").serialize()+str,
                            dataType:'json',
                            success:function(data){
                                bootbox.alert({
                                    size:"small",
                                    title:"提示",
                                    message:"修改成功！"
                                }),
                                    $('#myTable').bootstrapTable('refresh');
                            }
                        })
                    }
                },
                'cancel':{
                    "label" : "<i class='icon-info'></i> 取消",
                    "className" : "btn-sm btn-danger",
                }
            }
        })
    }
    //批量删除
    function delContract(){
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
                        ids += ids == "" ? arr[i].contractId : ","+arr[i].contractId;
                    }
                    $.ajax({
                        url:"/contract/delAll",
                        data:{ids:ids},
                        success:function(result){
                            alert(result.msg);
                            if(result.code == '0'){
                                $('#myTable').bootstrapTable('refresh');
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
    //导出数据
    function EXPContract(){
        var arr = $('#myTable').bootstrapTable('getSelections');
        if (arr.length <= 0) {
            bootbox.alert({
                size: "small",
                title: "提示",
                message: "请选择需要导出的数据",
                callback: function(){
                }
            });
            return;
        }
        bootbox.confirm({
            size: "small",
            message: "你确定要导出吗?",
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
                        ids += ids == "" ? arr[i].contractId : ","+arr[i].contractId;
                    }
                    location.href="/poi/createExcel?ids="+ids
                }
            }
        })
    }
</script>
</html>

