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
                    合同续约
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-2">机房名称:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="contractId" id="contractId" type="hidden"/>
                        <input class="form-control" name="renewStatus" id="renewStatus" type="hidden"/>
                        <input class="form-control" name="jifangName"  id="contractNameEdit" type="text"/>
                    </div>
                    <div class="col-xs-2">合同编码:</div>
                    <div class="col-xs-4">
                        <input class="form-control date" name="contractNum"  id="contractNum" type="text"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-2">续约截止日期:</div>
                    <div class="col-xs-4">
                        <input class="form-control date" name="endTime"  id="endTime" type="text"/>
                    </div>
                    <div class="col-xs-2">经办人:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="renewOperator"  id="renewOperator" type="text"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-2">备注:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="remark"  id="remark" type="text"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button"  class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="buttonAdd" class="btn btn-primary" onclick="submitContractExtension()">
                        提交更改
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>

<button type="button" onclick="delContractExtension()" class="btn btn-danger glyphicon glyphicon-minus">删除</button>

</div>
<table id="myTable"></table>
</body>
<script type="text/javascript">
    <!--初始化加载页面-->
    $(function(){
        initContract();
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

    function initContract(){
        $('#myTable').bootstrapTable('destroy');
        $("#myTable").bootstrapTable({
            url:'/contract/queryContract',//获取数据地址
            method: 'post',
            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
            pagination:true, //是否展示分页
            pageList:[50,100,500,1000],//分页组件
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
                    contractName:$('#contractName').val(),
                    startTime:$('#startTime').val(),
                    endTime:$('#endTime1').val(),
                    towerTypeName:$('#towerType1').text(),
                    contractTypeName:$('#contractType1').text(),
                    roomTypeName:$('#contractType1').text(),
                    extenxionStatus:1
                }
            },
            columns:[
                {field:'111',checkbox:true},
                {field:'contractId',title:'合同id',visible:false},
                {field:'contractNum',title:'合同編碼'},
                {field:'jifangName',title:'机房名称'},
                {field:'yearRental',title:'年租金'},
                {field:'endTime',title:'续约截止日期'},
                {field:'renewOperator',title:'经办人'},
                {field:'remark',title:'备注'},
                {field:'sign',title:'操作' ,class:'table-width',width:'10%',formatter:function(value,row,index){
                        return  ' <a href="javascript:editContractExtendion(\'' + row.contractId + '\',\'' + row.contractNum + '\',\'' + row.jifangName + '\',\'' + row.endTime + '\',\'' + row.renewOperator + '\',\'' + row.remark + '\')">修改</a>  ';
                    }}
            ]
        })
    }

    function editContractExtendion(contractId,contractNum,contractName,payEndTime,renewOperator,remark){
        $("#contractId").val(contractId);
        $("#contractNum").val(contractNum);
        $("#contractNameEdit").val(contractName);
        $("#endTime").val(payEndTime);
        if(renewOperator==null || renewOperator==""){
            alert(1)
            $("#renewOperator").val("-");
        }else{
            $("#renewOperator").val(renewOperator);
        }
        if(remark==null){
            $("#remark").val("-");
        }else{
            $("#remark").val(remark);
        }
        $("#remark").val(remark);
        $('#myModal').modal();
    }
    //提交用户
    function submitContractExtension(){
        document.getElementById('buttonAdd').disabled=true;
        $.ajax({
            url: '/contract/addContract',
            type: "post",
            data : {
                contractId:$("#contractId").val(),
                contractNum:$("#contractNum").val(),
                jifangName:$("#contractNameEdit").val(),
                endTime:$("#endTime").val(),
                renewOperator:$("#renewOperator").val(),
                remark:$("#remark").val(),
            },
            success:function (data){
                initContract();
                alert(data.msg)
                $("#myModal").modal('hide');
                document.getElementById('buttonAdd').disabled=false;
            },
            error:function (){
                alert("修改失败");
            }
        })
    }




    //批量删除
    function delContractExtension(){
        var arr = $('#contractExtension').bootstrapTable('getSelections');
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
                                initContract();
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

