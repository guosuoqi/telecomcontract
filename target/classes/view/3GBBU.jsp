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
                    新增3GBBU
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-2">电信编码:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="dxCode" id="dxCode" type="text"/>
                    </div>
                    <div class="col-xs-2">BBU编码:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="bbuCode" id="bbuCode" type="text"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-2">BBU名字:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="bbuName" id="bbuName" type="text"/>
                    </div>
                    <div class="col-xs-2">电信网管编码:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="netCareId"id="netCareId" type="text"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-2">电信网管名称:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="netCareName" id="netCareName" type="text"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button"  class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="buttonAdd" class="btn btn-primary" onclick="submit3GBBU()">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<button type="button" onclick="openAdd3GBBU()" class="btn btn-info glyphicon glyphicon-plus">新增</button>
<button type="button" onclick="del3GBBU()" class="btn btn-danger glyphicon glyphicon-minus">删除</button>

</div>
<table id="3GBBUTable"></table>
</body>
<script type="text/javascript">
    <!--初始化加载页面-->
    $(function(){
        initBBU();
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

    function initBBU(){
        $('#3GBBUTable').bootstrapTable('destroy');
        $("#3GBBUTable").bootstrapTable({
            url:'/site/queryBBU',//获取数据地址
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
                    networkType:3,
                }
            },
            columns:[
                {field:'111',checkbox:true},
                {field:'id',title:'3GBBUId'},
                {field:'dxCode',title:'电信编码'},
                {field:'bbuCode',title:'BBU编码'},
                {field:'bbuName',title:'BBU名字'},
                {field:'netCareId',title:'电信网管编码'},
                {field:'netCareName',title:'电信网管名称'},
                {field:'sign',title:'操作' ,class:'table-width',width:'10%',formatter:function(value,row,index){
                        return  ' <a href="javascript:editBBu('+row.id+');">修改</a>  ';
                    }}
            ]
        })
    }
    var res;
    function createAddContent(url){
        $.ajax({
            url:url,
            async:false,
            success:function(data){
                res = data;
            }
        });
        return res;
    }
    //打开新增合同续约的弹框

    function openAdd3GBBU(){
        $('#myModal').modal();
    }
   /* function openAdd3GBBU(){
        bootbox.dialog({
            size:"big",
            title:"添加3GBBU",
            message:createAddContent("/page/toAdd3GBBU"),
            closeButton:true,
            buttons:{
                'success':{
                    "label" : "<i class='icon-ok'></i> 保存",
                    "className" : "btn-sm btn-success",
                    "callback" : function() {
                        var str = "&networkType="+3
                        $.ajax({
                            url:'/site/add3GBBU',
                            type:'post',
                            data: $("#3GBBUForm").serialize()+str,
                            dataType:'json',
                            success:function(data){
                                bootbox.alert({
                                    size:"small",
                                    title:"提示",
                                    message:data.msg
                                })
                                $('#3GBBUTable').bootstrapTable('refresh');
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
    }*/


    //提交用户
    function submit3GBBU(){
        document.getElementById('buttonAdd').disabled=true;
        $.ajax({
            url: '/site/add3GBBU',
            type: "post",
            data : {
                dxCode:$("#dxCode").val(),
                bbuCode:$("#bbuCode").val(),
                bbuName:$("#bbuName").val(),
                netCareId:$("#netCareId").val(),
                netCareName:$("#netCareName").val(),
                networkType:3
            },
            success:function (data){
                initBBU();
                alert(data.msg)
                $("#myModal").modal('hide');
                document.getElementById('buttonAdd').disabled=false;
            },
            error:function (){
                alert("新增用户失败");
            }
        })
    }
    //打开修改的弹框
    function editBBu(id){
        bootbox.dialog({
            size:"big",
            title:"修改BBU信息",
            message:createAddContent("/page/toUpdate3GBBU?id="+id),
            closeButton:true,
            buttons:{
                'success':{
                    "label" : "<i class='icon-ok'></i> 保存",
                    "className" : "btn-sm btn-success",
                    "callback" : function() {
                        $.ajax({
                            url:'/site/update3GBBU',
                            type:'post',
                            data:$("#3GBBUForm").serialize(),
                            dataType:'json',
                            success:function(data){
                                bootbox.alert({
                                    size:"small",
                                    title:"提示",
                                    message:"修改成功！"
                                }),
                                    $('#3GBBUTable').bootstrapTable('refresh');
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
    function del3GBBU(){
        var arr = $('#3GBBUTable').bootstrapTable('getSelections');
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
                        url:"/site/delAll",
                        data:{ids:ids},
                        success:function(result){
                            alert(result.msg);
                            if(result.code == '0'){
                                $('#3GBBUTable').bootstrapTable('refresh');
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

