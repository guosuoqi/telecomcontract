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
<input type="hidden" id="typeHidIdOne">
<input type="hidden" id="typeHidIdTwo">
<input type="hidden" id="typeHidIdThree">
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
                            <label for="contractName1">合同名称</label>
                        </div>
                        <div class="col-sm-9"> <%--占8格，充满--%>
                            <input type="text" name="contractName" id="contractName1"  class="form-control" placeholder="请输入合同名称">
                        </div>
                    </div>

                    <div class="col-sm-4 "> <%--<td>--%>
                        <div class="col-sm-3 "> <%--占4格--%>
                            <label for="startTime">开始时间</label>
                        </div>
                        <div class="col-sm-9"> <%--占8格，充满--%>
                            <label for="startTime"></label>
                            <input type="text" class="form-control date" id="startTime" placeholder="请输入开始时间">
                        </div>
                    </div>
                    <div class="col-sm-4 "> <%--<td>--%>
                        <div class="col-sm-3 "> <%--占4格--%>
                            <label for="endTime">结束时间</label>
                        </div>
                        <div class="col-sm-9"> <%--占8格，充满--%>
                            <label for="endTime"></label>
                            <input type="text" class="form-control date" id="endTime" placeholder="请输入结束时间">
                        </div>
                    </div>

                </div>
                <div class="col-sm-12 "> <%--<td>--%>
                    <div class="col-sm-4 "> <%--<td>--%>
                        <div class="col-sm-3 "> <%--占3格--%>
                            <label for="roomType">机房类型</label>
                        </div>
                        <div class="col-sm-9"> <%--占9格，充满--%>
                            <select id="roomType"name="roomType" class="selectpicker form-control"   data-live-search="true" ></select>
                        </div>
                    </div>
                    <div class="col-sm-4 "> <%--<td>--%>
                        <div class="col-sm-3 "> <%--占4格--%>
                            <label for="towerType">铁栀类型</label>
                        </div>
                        <div class="col-sm-9"> <%--占8格，充满--%>
                            <select id="towerType" class="selectpicker form-control"   data-live-search="true" ></select>
                        </div>
                    </div>
                    <div class="col-sm-4 "> <%--<td>--%>
                        <div class="col-sm-3 "> <%--占4格--%>
                            <label for="contractType">合同类型</label>
                        </div>
                        <div class="col-sm-9"> <%--占8格，充满--%>
                            <select id="contractType" class="selectpicker form-control"   data-live-search="true"></select>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12">
                    <button type="button" class="btn btn-primary btn-w-m" onclick="initContract()"  style="float: right;margin-right:20px;">
                        <span class="glyphicon glyphicon-search"></span> 搜索
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

<button type="button" onclick="openAddDialog()" class="btn btn-info glyphicon glyphicon-plus">新增</button>
<button type="button" onclick="delContract()" class="btn btn-danger glyphicon glyphicon-minus">删除</button>
<button type="button" onclick="EXPContract()" class="btn btn-danger glyphicon">导出</button>
</div>
<table id="myTable"></table>
</body>
<script type="text/javascript">
    <!--初始化加载页面-->
    $(function(){
       initCodeType();
        initContract();
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

    function initContract(){
        $('#myTable').bootstrapTable('destroy');
        $("#myTable").bootstrapTable({
            url:'/contract/queryContract',//获取数据地址
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

               /* if($("#towerType option:selected").val()==-"1"){
                    $("#towerType option:selected").text("-1");
                }
                if($("#contractType option:selected").val()==-"1"){
                    $("#contractType option:selected").text("-1");
                }
                if($("#roomType option:selected").val()==-"1"){
                    $("#roomType option:selected").text("-1");
                }*/
                return {
                    page: this.pageNumber,
                    rows: this.pageSize,
                    contractName:$('#contractName1').val(),
                    startTime:$('#startTime').val(),
                    endTime:$('#endTime1').val(),
                    towerTypeName:$("#towerType option:selected").val()=="-1"?"-1":$("#towerType option:selected").text(),
                    contractTypeName:$('#contractType option:selected').val()=="-1"?"-1":$('#contractType option:selected').text(),
                    roomTypeName:$('#roomType option:selected').val()=="-1"?"-1":$('#roomType option:selected').text()
                }
            },

            columns:[
                {field:'333',checkbox:true,align: 'left',width:"20px",valign: 'middle'},
                {field:'contractId',title:'合同id',align: 'center',width:"40px",valign: 'middle'},
                {field:'contractName',title:'合同名字',align: 'center',valign: 'middle'},
                {field:'city',title:'--省--',align: 'center',valign: 'middle'},
                {field:'county',title:'--市--',align: 'center',valign: 'middle'},
                {field:'yearRental',title:'年租金',align: 'center',valign: 'middle'},
                {field:'sunRental',title:'总租金',align: 'center',valign: 'middle'},
                {field:'contractNum',title:'合同编号',align: 'center',valign: 'middle'},
                {field:'contractFirst',title:'合同甲方',align: 'center',valign: 'middle'},
                {field:'payee',title:'收款人',align: 'center',valign: 'middle'},
                {field:'planYear',title:'拟租年份',align: 'center',valign: 'middle'},
                {field:'startTime',title:'合同开始时间',align: 'center',valign: 'middle'},
                {field:'endTime',title:'合同结束时间',align: 'center',valign: 'middle'},
                {field:'payEndTime',title:'付费截止日期',align: 'center',valign: 'middle'},
                {field:'roomTypeName',title:'机房类型',align: 'center',valign: 'middle'},
                {field:'towerTypeName',title:'塔栀类型',align: 'center',valign: 'middle'},
                {field:'contractTypeName',title:'合同类型',align: 'center',valign: 'middle'},
                {field:'111',title:' 操作 ' ,class:'table-width',valign: 'middle',formatter:function(value,row,index){
                        return  ' <a href="javascript:editContract(\'' + row.contractId + '\',\'' + row.roomType + '\',\'' + row.towerType + '\',\'' + row.contractType + '\')">修改</a>  ';
                    }}
            ]
        })
    }


    function initCodeType(){
        $(".selectpicker").selectpicker({
            noneSelectedText: '--请选择--' //默认显示内容  
        });
        $.ajax({
            url: "/contract/queryType",
            success:function (data) {
                var typeHtmlcontract = '<option value="-1">--请选择--</option>';
                var typeHtmlRoom = '<option value="-1">--请选择--</option>';
                var typeHtmlTower = '<option value="-1">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    if (data[i].codeType == 'contract') {
                        typeHtmlcontract += '<option value="' + data[i].codeId + '">' + data[i].codeName + '</option>';
                    } else if (data[i].codeType == 'room') {
                        typeHtmlRoom += '<option value="' + data[i].codeId + '">' + data[i].codeName + '</option>';

                    } else if (data[i].codeType == 'tower') {
                        typeHtmlTower += '<option value="' + data[i].codeId + '">' + data[i].codeName + '</option>';

                    }
                }
                $("#roomType").html(typeHtmlRoom);
                $("#towerType").html(typeHtmlTower);
                $("#contractType").html(typeHtmlcontract);
                $('.selectpicker').selectpicker('refresh');
            },error:function(){
                alert("指定人员下拉有误,请调试 ！！！");
            }
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

