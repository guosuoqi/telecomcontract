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
                    新增站点
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-2">站点名称:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="baseName" id="baseCode" type="text"/>
                    </div>
                    <div class="col-xs-2">机房产权:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="baseProperty" id="baseProperty" type="text"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-2">所属站址编码:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="id" id="id" type="hidden"/>
                        <input class="form-control" name="dxCode" id="dxCode" type="text"/>
                    </div>
                    <div class="col-xs-2">铁塔站址编码:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="ttCode"id="ttCode" type="text"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-2">电费缴纳方:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="powerMan" id="powerMan" type="text"/>
                    </div>
                    <div class="col-xs-2">租赁费缴纳方:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="rentPayer"id="rentPayer" type="text"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-2">站址经度:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="dxCode" id="longitude" type="text"/>
                    </div>
                    <div class="col-xs-2">站址纬度:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="ttCode"id="latitude" type="text"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-2">备注:</div>
                    <div class="col-xs-8">
                        <input class="form-control" name="remark" id="remark" type="text"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button"  class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="buttonAdd" class="btn btn-primary" onclick="submitSit()">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>



<button type="button" onclick="openAddDialog()" class="btn btn-info glyphicon glyphicon-plus">新增</button>
<button type="button" onclick="delContract()" class="btn btn-danger glyphicon glyphicon-minus">删除</button>
<button type="button" onclick="EXPContract()" class="btn btn-danger glyphicon">导出</button>
<button type="button" id="daoru" class="btn btn-info btn-sm" style="width: 90px">导入</button>
<!-- daoruDialog弹框 -->
<div class="modal fade" id="daoruDialog" tabindex="-1" role="dialog" aria-labelledby="importModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="importModal">导入</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <form id="uploadForm">
                        <input type="file" name="file">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                <button type="button" onclick="doUpload()" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
            </div>
        </div>
    </div>
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
            pageList:[100,500,1000],//分页组件
            pageNumber:1,
            pageSize:50,//默认每页条数
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
                {field:'id',title:'站点id',align: 'center',width:"40px",valign: 'middle',visible:false},
                {field:'baseName',title:'站点名称',align: 'center',valign: 'middle'},
                {field:'baseProperty',title:'机房产权',align: 'center',valign: 'middle'},
                {field:'dxCode',title:'所属站址编码',align: 'center',valign: 'middle'},
                {field:'ttCode',title:'铁塔站址编码',align: 'center',valign: 'middle'},
                {field:'powerMan',title:'电费缴纳方',align: 'center',valign: 'middle'},
                {field:'rentPayer',title:'租赁费缴纳方',align: 'center',valign: 'middle'},
                {field:'longitude',title:'经度',align: 'center',valign: 'middle'},
                {field:'latitude',title:'纬度',align: 'center',valign: 'middle'},
                {field:'threeBbuCount',title:'3GBBU个数',align: 'center',valign: 'middle'},
                {field:'fourBbuCount',title:'4GBBU个数',align: 'center',valign: 'middle'},
                {field:'fiveBbuCount',title:'5GBBU个数',align: 'center',valign: 'middle'},
                {field:'threeRruCount',title:'3GRRU个数',align: 'center',valign: 'middle'},
                {field:'fourRruCount',title:'4GRRU个数',align: 'center',valign: 'middle'},
                {field:'fiveAauCount',title:'5GAAU个数',align: 'center',valign: 'middle'},
                {field:'oltCount',title:'OLT个数',align: 'center',valign: 'middle'},
                {field:'ipranCount',title:'IPRAN个数',align: 'center',valign: 'middle'},
                {field:'powerConsume',title:'机房总耗电量',align: 'center',valign: 'middle'},
                {field:'remark',title:'备注',align: 'center',valign: 'middle'},
                {field:'111',title:' 操作 ' ,class:'table-width',valign: 'middle',formatter:function(value,row,index){
                        return  ' <a href="javascript:editSite(\'' + row.id + '\',\'' + row.baseName + '\',\'' + row.baseProperty + '\',\'' + row.dxCode + '\',\'' + row.ttCode + '\',\'' + row.powerMan + '\',\'' + row.rentPayer + '\',\'' + row.longitude + '\',\'' + row.latitude + '\',\'' + row.remark + '\')">修改</a>  ';
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
        $("#id").val("");
        $("#baseCode").val("");
        $("#baseProperty").val("");
        $("#dxCode").val("");
        $("#ttCode").val("");
        $("#powerMan").val("");
        $("#rentPayer").val("");
        $("#longitude").val("");
        $("#latitude").val("");
        $("#remark").val("");
        $('#myModal').modal();
    }

    //打开修改框
    function editSite(id,baseName,baseProperty,dxCode,ttCode,powerMan,rentPayer,longitude,latitude,remark) {
        $("#id").val(id);
        $("#baseName").val(baseName);
        $("#baseProperty").val(baseProperty);
        $("#dxCode").val(dxCode);
        $("#ttCode").val(ttCode);
        $("#powerMan").val(powerMan);
        $("#rentPayer").val(rentPayer);
        $("#longitude").val(longitude);
        $("#latitude").val(latitude);
        $("#remark").val(remark);
        $('#myModal').modal();
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
                        ids += ids == "" ? arr[i].id : ","+arr[i].id;
                    }
                    $.ajax({
                        url:"/site/delAllSit",
                        data:{ids:ids},
                        success:function(result){
                            alert(result.msg);
                            if(result.code == '0'){
                                initSiteManager();
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
                        ids += ids == "" ? arr[i].id : ","+arr[i].id;
                    }
                    location.href="/poi/createExcel?ids="+ids+"&&type=10"
                }
        })
    }
    //打开导入弹框
    $("#daoru").click(function(){
        $('#daoruDialog').modal();
    })
    function doUpload() {
        var formData = new FormData($( "#uploadForm" )[0]);
        $.ajax({
            url: '/poi/importSite',
            type: 'post',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                $('#myTable').bootstrapTable('refresh');
            },
            error: function () {
                alert(result.msg);
            }
        });
    }
    //提交用户
    function submitSit(){
        document.getElementById('buttonAdd').disabled=true;
        $.ajax({
            url: '/site/insertStation',
            type: "post",
            data : {
                id:$("#id").val(),
                dxCode:$("#dxCode").val(),
                baseName:$("#baseName").val(),
                baseProperty:$("#baseProperty").val(),
                ttCode:$("#ttCode").val(),
                latitude:$("#latitude").val(),
                longitude:$("#longitude").val(),
                rentPayer:$("#rentPayer").val(),
                powerMan:$("#powerMan").val(),
                remark:$("#remark").val()
            },
            success:function (data){
                initSiteManager();
                alert(data.msg)
                $("#myModal").modal('hide');
                document.getElementById('buttonAdd').disabled=false;
            },
            error:function (){
                alert("新增失败");
            }
        })
    }
</script>
</html>

