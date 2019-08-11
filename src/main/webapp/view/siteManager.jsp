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
    <%--<script src="/static/js/select.js"></script>--%>
    <title>信息管理</title>
</head>

<body>
<style type="text/css">
    table {
        table-layout:auto;

    }
</style>

<button type="button" onclick="openAddDialog()" class="btn btn-info glyphicon glyphicon-plus">新增</button>
<button type="button" onclick="delContract()" class="btn btn-danger glyphicon glyphicon-minus">删除</button>
<button type="button" onclick="EXPContract()" class="btn btn-danger glyphicon">导出</button>
</div>
<table id="myTable"></table>
</body>
<script type="text/javascript">
    <!--初始化加载页面-->
    $(function(){
        initSiteManager();
    })
    //时间转中文
    $('.date').datetimepicker({
        language: 'zh-CN',//显示中文
        format: 'yyyy-mm-dd',//显示格式
        minView: "month",//设置只显示到月份
        initialDate: new Date(),//初始化当前日期
        autoclose: true,//选中自动关闭
        todayBtn: true//显示今日按钮
    });

    function initSiteManager(){
        $('#myTable').bootstrapTable('destroy');
        $("#myTable").bootstrapTable({
            url:'/site/queryStieManager',//获取数据地址
            method: 'post',
            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
            pagination:true, //是否展示分页
            pageList:[10,50,100,500],//分页组件
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
                }
            },

            columns:[
                {field:'333',checkbox:true,align: 'left',width:"20px",valign: 'middle'},
                {field:'id',title:'站点id',align: 'center',width:"40px",valign: 'middle'},
                {field:'baseCode',title:'基站编码',align: 'center',valign: 'middle'},
                {field:'baseProperty',title:'基站产权',align: 'center',valign: 'middle'},
                {field:'dxCode',title:'电信站址编码',align: 'center',valign: 'middle'},
                {field:'ttCode',title:'铁塔站址编码',align: 'center',valign: 'middle'},
                {field:'threeBbuCount',title:'3GBBU个数',align: 'center',valign: 'middle'},
                {field:'fourBbuCount',title:'4GBBU个数',align: 'center',valign: 'middle'},
                {field:'fiveBbuCount',title:'5GBBU个数',align: 'center',valign: 'middle'},
                {field:'threeRruCount',title:'3GRRU个数',align: 'center',valign: 'middle'},
                {field:'fourRruCount',title:'4GRRU个数',align: 'center',valign: 'middle'},
                {field:'fiveAauCount',title:'5GAAU个数',align: 'center',valign: 'middle'},
                {field:'oltCount',title:'OLT个数',align: 'center',valign: 'middle'},
                {field:'tributaryPowerConsume',title:'直流设备耗电量',align: 'center',valign: 'middle'},
                {field:'pue',title:'PUE值',align: 'center',valign: 'middle'},
                {field:'powerConsume',title:'机房总耗电量',align: 'center',valign: 'middle'},
                {field:'111',title:' 操作 ' ,class:'table-width',valign: 'middle',formatter:function(value,row,index){
                        return  ' <a href="javascript:editContract(\'' + row.contractId + '\',\'' + row.roomType + '\',\'' + row.towerType + '\',\'' + row.contractType + '\')">修改</a>  ';
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
//打开新增合同的弹框
    function openAddDialog(){
        bootbox.dialog({
            size:"big",
            title:"添加合同",
            message:createAddContent("/page/toAddContract"),
            closeButton:true,
            buttons:{
                'success':{
                    "label" : "<i class='icon-ok'></i> 保存",
                    "className" : "btn-sm btn-success",
                    "callback" : function() {
                        var str = "&towerTypeName="+$("#tower option:selected").text()+
                            "&contractTypeName="+$("#contract option:selected").text()+
                            "&roomTypeName="+$("#room option:selected").text();
                        $.ajax({
                            url:'/contract/addContract',
                            type:'post',
                            data: $("#contractForm").serialize()+str,
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
                    var ids = "";
                    for (var i = 0; i < arr.length; i++) {
                        ids += ids == "" ? arr[i].contractId : ","+arr[i].contractId;
                    }
                    location.href="/poi/createExcel?ids="+ids+"&&type=1"
                }
        })
    }
</script>
</html>

