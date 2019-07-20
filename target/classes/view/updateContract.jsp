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
    <%--<script src="/static/js/select.js"></script>--%>
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
    <title>新增合同</title>
</head>

<body>



<div class="container-fluid">
    <form id="contractForm" class="form-horizontal">
        <div class="row">
            <div class="col-xs-2">合同名称:</div>
            <div class="col-xs-4">
                <input class="form-control" name="contractId" value="${contract.contractId}" id="contractId" type="hidden"/>
                <input class="form-control" name="contractName" value="${contract.contractName}" id="contractName" type="text"/>
            </div>
            <div class="col-xs-2">合同编号:</div>
            <div class="col-xs-4">
                <input class="form-control" value="${contract.contractNum}" name="contractNum" id="contractNum" type="text"/>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-2">年租金:</div>
            <div class="col-xs-4">
                <input class="form-control" contractNum name="yearRental" value="${contract.yearRental}" id="yearRental" type="text"/>
            </div>
            <div class="col-xs-2">总租金:</div>
            <div class="col-xs-4">
                <input class="form-control" name="sunRental" value="${contract.sunRental}" id="sunRental" type="text"/>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-2">合同甲方:</div>
            <div class="col-xs-4">
                <input class="form-control" name="contractFirst" value="${contract.contractFirst}" id="contractFirst" type="text"/>
            </div>
            <div class="col-xs-2">收款人:</div>
            <div class="col-xs-4">
                <input class="form-control"  name="payee" value="${contract.payee}"id="payee" type="text"/>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-2">拟租年份:</div>
            <div class="col-xs-4">
                <input class="form-control date" name="planYear" value="${contract.planYear}" id="planYear" type="text"/>
            </div>
            <div class="col-xs-2">付费截止日期:</div>
            <div class="col-xs-4">
                <input class="form-control date" name="payEndTime"value="${contract.payEndTime}"  id="payEndTime" type="text"/>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-2">开始时间:</div>
            <div class="col-xs-4">
                <input class="form-control date" name="startTime" value="${contract.startTime}" id="startTime" type="text"/>
            </div>
            <div class="col-xs-2">结束时间:</div>
            <div class="col-xs-4">
                <input class="form-control date" name="endTime" value="${contract.endTime}" id="endTime" type="text"/>
            </div>
        </div>

        <div class="row">
        <div class="col-xs-2">合同类型:</div>
        <div class="col-xs-4">
            <select id="contractUp" name="contractTypeName"  class="form-control">
                selected
            </select>
        </div>
            <div class="col-xs-2">铁栀类型:</div>
            <div class="col-xs-4">
                <select id="towerUp" name="towerTypeName"  class="form-control">
                </select>
            </div>
    </div>
        <div class="row">
            <div class="col-xs-2">机房类型:</div>
            <div class="col-xs-4">
                <select id="roomUp" name="roomTypeName" class="form-control">
                </select>
            </div>

        </div>

    </form>
</div>

</body>
<script type="text/javascript">

    $(function(){
        initCodeTypeEdit();

    })

    $('#planYear').datetimepicker({
        minView: "month",
        format: "yyyy",
        language: 'zh-CN',
        autoclose:true
    });

    function initCodeTypeEdit(){
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
                        typeHtmlcontract += '<option value="' + data[i].codeId + '" selected>' + data[i].codeName + '</option>';
                    } else if (data[i].codeType == 'room') {
                        typeHtmlRoom += '<option value="' + data[i].codeId + '" selected>' + data[i].codeName + '</option>';

                    } else if (data[i].codeType == 'tower') {
                        typeHtmlTower += '<option value="' + data[i].codeId + '" selected>' + data[i].codeName + '</option>';

                    }
                }
                $("#roomUp").html(typeHtmlRoom);
                $("#towerUp").html(typeHtmlTower);
                $("#contractUp").html(typeHtmlcontract);
                $('.selectpicker').selectpicker('refresh');
            },error:function(){
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

