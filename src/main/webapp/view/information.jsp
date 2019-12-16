<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
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
    <%--<script src="/static/js/select.js"></script>--%>
    <title>合同管理</title>
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
                    新增合同
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-2">机房名称:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="contractId" id="contractId" type="hidden"/>
                        <input class="form-control" name="jifangName" id="jifangName" type="text"/>
                    </div>
                    <div class="col-xs-2">合同编号:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="contractNum" id="contractNum" type="text"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-2">区县:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="county" id="county" type="text"/>
                    </div>
                    <div class="col-xs-2">地址:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="address" id="address" type="text"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-2">年租金:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="yearRental" id="yearRental" type="text"/>
                    </div>
                    <div class="col-xs-2">总租金:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="sunRental" id="sunRental" type="text"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-2">合同甲方:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="contractFirst" id="contractFirst" type="text"/>
                    </div>
                    <div class="col-xs-2">收款人:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="payee" id="payee" type="text"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-2">是否有基站:</div>
                    <div class="col-xs-4">
                        <input class="form-control date" name="towerTypeName" id="towerTypeName" type="text"/>
                    </div>
                    <div class="col-xs-2">付费截止日期:</div>
                    <div class="col-xs-4">
                        <input class="form-control date" name="payEndTime" id="payEndTime" type="text"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-2">开始时间:</div>
                    <div class="col-xs-4">
                        <input class="form-control date" name="startTime" id="startTimeAdd" type="text"/>
                    </div>
                    <div class="col-xs-2">结束时间:</div>
                    <div class="col-xs-4">
                        <input class="form-control date" name="endTime" id="endTimeAdd" type="text"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-2">合同类型:</div>
                    <div class="col-xs-4">
                        <select id="contract" name="contractType" class="form-control">
                        </select>
                    </div>
                    <div class="col-xs-2">机房类型:</div>
                    <div class="col-xs-4">
                        <select id="room" name="roomType" class="form-control">
                        </select>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="buttonAdd" class="btn btn-primary" onclick="submitContract()">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<style type="text/css">
    table {
        table-layout: auto;

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
                            <input type="text" name="contractName" id="contractName1" class="form-control"
                                   placeholder="请输入合同名称">
                        </div>
                    </div>

                </div>
                <div class="col-sm-12 "> <%--<td>--%>
                    <div class="col-sm-4 "> <%--<td>--%>
                        <div class="col-sm-3 "> <%--占3格--%>
                            <label for="roomType">机房类型</label>
                        </div>
                        <div class="col-sm-9"> <%--占9格，充满--%>
                            <select id="roomType" name="roomType" class="selectpicker form-control"
                                    data-live-search="true"></select>
                        </div>
                    </div>
                    <div class="col-sm-4 "> <%--<td>--%>
                        <div class="col-sm-3 "> <%--占4格--%>
                            <label for="towerType">是否有基站</label>
                        </div>
                        <div class="col-sm-9"> <%--占8格，充满--%>
                            <select id="towerType" class="selectpicker form-control" data-live-search="true">
                                <option value="-1">--请选择--</option>
                                <option value="是">是</option>
                                <option value="否">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-4 "> <%--<td>--%>
                        <div class="col-sm-3 "> <%--占4格--%>
                            <label for="county">地区</label>
                        </div>
                        <div class="col-sm-9"> <%--占8格，充满--%>
                            <input type="text" name="county" id="countySou" class="form-control"
                                   placeholder="请输入地区">
                        </div>
                    </div>
                </div>

                <div class="col-xs-12">
                    <button type="button" class="btn btn-primary btn-w-m" onclick="initContract()"
                            style="float: right;margin-right:20px;">
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
<button type="button" onclick="EXPmoban()" class="btn btn-danger glyphicon">模板</button>
<button type="button" id="daoru" class="btn btn-info btn-sm" style="width: 90px">导入</button>

<!-- daoruDialog弹框 -->
<div class="modal fade" id="daoruDialog" tabindex="-1" role="dialog" aria-labelledby="importModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
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
                <button type="button" class="btn btn-default" data-dismiss="modal"><span
                        class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                </button>
                <button type="button" onclick="doUpload()" class="btn btn-primary" data-dismiss="modal"><span
                        class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                </button>
            </div>
        </div>
    </div>
</div>
<table id="myTable"></table>
</body>
<script type="text/javascript">
    <!--初始化加载页面-->
    $(function () {
        initCodeType(0);
        //initCodeTypeAdd();
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

    function initContract() {
        $('#myTable').bootstrapTable('destroy');
        $("#myTable").bootstrapTable({
            url: '/contract/queryContract',//获取数据地址
            method: 'post',
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            pagination: true, //是否展示分页
            pageList: [50, 99, 500, 1000],//分页组件
            pageNumber: 1,
            pageSize: 50,//默认每页条数
            //search:true,//是否显示搜索框
            //searchText:'试试',//初始化搜索文字
            showColumns: false,//是否显示 内容列下拉框
            showToggle: false,//是否显示 切换试图（table/card）按钮
            showPaginationSwitch: false,//是否显示 数据条数选择框
            showRefresh: false,//是否显示刷新按钮
            detailView: false,//设置为 true 可以显示详细页面模式。
            showFooter: false,//是否显示列脚
            clickToSelect: true, //是否启用点击选中行
            sidePagination: 'server',//分页方式：client客户端分页，server服务端分页（*
            striped: true,
            queryParams: function () {
                return {
                    page: this.pageNumber,
                    rows: this.pageSize,
                    county:$('#countySou').val(),
                    contractName: $('#contractName1').val(),
                    startTime: $('#startTime').val(),
                    endTime: $('#endTime1').val(),
                    towerTypeName: $("#towerType option:selected").val() == "-1" ? "-1" : $("#towerType option:selected").text(),
                    contractTypeName: $('#contractType option:selected').val() == "-1" ? "-1" : $('#contractType option:selected').text(),
                    roomTypeName: $('#roomType option:selected').val() == "-1" ? "-1" : $('#roomType option:selected').text()
                }
            },

            columns: [
                {field: '333', checkbox: true, align: 'left', width: "20px", valign: 'middle'},
                {field: 'contractId', title: '合同id', align: 'center', width: "40px", valign: 'middle',visible:false},
                {field: 'jifangName', title: '机房名称', align: 'center', valign: 'middle'},
                {field: 'county', title: '--县--', align: 'center', valign: 'middle'},
                {field: 'address', title: '--地址--', align: 'center', valign: 'middle'},
                {field: 'yearRental', title: '年租金', align: 'center', valign: 'middle'},
                {field: 'sunRental', title: '总租金', align: 'center', valign: 'middle'},
                {field: 'contractNum', title: '合同编号', align: 'center', valign: 'middle'},
                {field: 'contractFirst', title: '合同甲方', align: 'center', valign: 'middle'},
                {field: 'payee', title: '收款人', align: 'center', valign: 'middle'},
                //{field: 'planYear', title: '拟租年份', align: 'center', valign: 'middle'},
                {field: 'startTime', title: '拟租合同开始时间', align: 'center', valign: 'middle'},
                {field: 'endTime', title: '拟租合同结束时间', align: 'center', valign: 'middle'},
                {field: 'payEndTime', title: '付费截止日期', align: 'center', valign: 'middle'},
                {field: 'roomTypeName', title: '机房类型', align: 'center', valign: 'middle'},
                {field: 'towerTypeName', title: '是否有基站', align: 'center', valign: 'middle'},//todo   字段调整
                {
                    field: '111',
                    title: ' 操作 ',
                    class: 'table-width',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        return ' <a href="javascript:editContract(\'' + row.contractId + '\',\'' + row.jifangName + '\',\'' + row.county + '\',\'' + row.yearRental + '\',\'' + row.sunRental + '\',\'' + row.contractNum + '\',\'' + row.contractFirst + '\',\'' + row.payee + '\',\'' + row.planYear + '\',\'' + row.payEndTime + '\',\'' + row.startTime + '\',\'' + row.endTime + '\',\'' + row.roomTypeName + '\',\'' + row.towerTypeName + '\',\'' + row.contractTypeName + '\',\'' + row.roomType + '\',\'' + row.towerType + '\',\'' + row.contractType + '\',\'' + row.address + '\')">修改</a>  ';
                    }
                }
            ]
        })
    }

    //打开新增合同的弹框
    function openAddDialog() {
        initCodeType(1);
        $("#contractId").val(""),
        $("#jifangName").val(""),
        $("#county").val(""),
        $("#yearRental").val(""),
        $("#contractNum").val(""),
        $("#sunRental").val(""),
        $("#contractFirst").val(""),
        $("#payee").val(""),
        $("#planYear").val(""),
        $("#payEndTime").val(""),
        $("#startTimeAdd").val(""),
        $("#endTimeAdd").val(""),
        $('#myModal').modal();
    }

    function editContract(contractId, jifangName,county, yearRental,sunRental,contractNum, contractFirst, payee, planYear,payEndTime,startTime,endTime,roomTypeName,towerTypeName,contractTypeName,roomType,towerType,contractType,address) {
        $.ajax({
            url: "/contract/queryType",
            success: function (data) {
                var typeHtmlcontract = '<option value="-1">--请选择--</option>';
                var typeHtmlRoom = '<option value="-1">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    if (data[i].codeType == 'contract') {
                        if(data[i].codeId==contractType){
                            typeHtmlcontract += '<option value="' + data[i].codeId + '" selected>' + data[i].codeName + '</option>';
                        }else{
                            typeHtmlcontract += '<option value="' + data[i].codeId + '">' + data[i].codeName + '</option>';
                        }
                    } else if (data[i].codeType == 'room') {
                        if(data[i].codeId==roomType){
                            typeHtmlRoom += '<option value="' + data[i].codeId + '" selected>' + data[i].codeName + '</option>';
                        }else {
                            typeHtmlRoom += '<option value="' + data[i].codeId + '">' + data[i].codeName + '</option>';
                        }
                    }
                }
                $("#room").html(typeHtmlRoom);
                $("#contract").html(typeHtmlcontract);
                $('.selectpicker').selectpicker('refresh');
            }, error: function () {
                alert("下拉回显有误,请调试 ！！！");
            }
        })
        $("#contractId").val(contractId);
        $("#jifangName").val(jifangName),
        $("#county").val(county),
        $("#address").val(address),
        $("#yearRental").val(yearRental),
        $("#contractNum").val(contractNum),
        $("#sunRental").val(sunRental),
        $("#contractFirst").val(contractFirst),
        $("#payee").val(payee),
        $("#planYear").val(planYear),
        $("#payEndTime").val(payEndTime),
        $("#startTimeAdd").val(startTime),
        $("#endTimeAdd").val(endTime),
        $("#towerTypeName").val(towerTypeName),
        $('#myModal').modal();
    }


    //提交合同
    function submitContract() {
        document.getElementById('buttonAdd').disabled = true;
        $.ajax({
            url: '/contract/addContract',
            type: "post",
            data: {
                county:$("#county").val(),
                address:$("#address").val(),
                contractId: $("#contractId").val(),
                jifangName: $("#jifangName").val(),
                contractNum: $("#contractNum").val(),
                yearRental: $("#yearRental").val(),
                sunRental: $("#sunRental").val(),
                contractFirst: $("#contractFirst").val(),
                payee: $("#payee").val(),
                planYear: $("#planYear").val(),
                payEndTime: $("#payEndTime").val(),
                startTime: $("#startTimeAdd").val(),
                endTime: $("#endTimeAdd").val(),
                contractType: $("#contract").val(),
                towerType: $("#tower").val(),
                roomType: $("#room").val(),
                towerTypeName: $("#towerTypeName").val(),
                contractTypeName: $("#contract option:selected").text(),
                roomTypeName: $("#room option:selected").text()
            },
            success: function (data) {
                initContract();
                alert(data.msg)
                $("#myModal").modal('hide');
                document.getElementById('buttonAdd').disabled = false;
            },
            error: function () {
                alert("新增用户失败");
            }
        })
    }

    function initCodeTypeAdd() {
        $(".selectpicker").selectpicker({
            noneSelectedText: '--请选择--' //默认显示内容  
        });
        $.ajax({
            url: "/contract/queryType",
            success: function (data) {
                var typeHtmlcontract = '<option value="-1">--请选择--</option>';
                var typeHtmlRoom = '<option value="-1">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    if (data[i].codeType == 'contract') {
                        typeHtmlcontract += '<option value="' + data[i].codeId + '">' + data[i].codeName + '</option>';
                    } else if (data[i].codeType == 'room') {
                        typeHtmlRoom += '<option value="' + data[i].codeId + '">' + data[i].codeName + '</option>';
                    }
                }
                $("#room").html(typeHtmlRoom);
                $("#contract").html(typeHtmlcontract);
                $('.selectpicker').selectpicker('refresh');
            }, error: function () {
                alert("指定人员下拉有误,请调试 ！！！");
            }
        })
    }

    function initCodeType(type) {
        $(".selectpicker").selectpicker({
            noneSelectedText: '--请选择--' //默认显示内容  
        });
        $.ajax({
            url: "/contract/queryType",
            success: function (data) {
                var typeHtmlcontract = '<option value="-1">--请选择--</option>';
                var typeHtmlRoom = '<option value="-1">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    if (data[i].codeType == 'contract') {
                        typeHtmlcontract += '<option value="' + data[i].codeId + '">' + data[i].codeName + '</option>';
                    } else if (data[i].codeType == 'room') {
                        typeHtmlRoom += '<option value="' + data[i].codeId + '">' + data[i].codeName + '</option>';
                    }
                }
                if(type==1){
                    $("#room").html(typeHtmlRoom);
                    $("#contract").html(typeHtmlcontract);
                }else {
                    $("#roomType").html(typeHtmlRoom);
                    $("#contractType").html(typeHtmlcontract);
                    $('.selectpicker').selectpicker('refresh');
                }
            }, error: function () {
                alert("指定人员下拉有误,请调试 ！！！");
            }
        })
    }
    //批量删除
    function delContract() {
        var arr = $('#myTable').bootstrapTable('getSelections');
        if (arr.length <= 0) {
            bootbox.alert({
                size: "small",
                title: "提示",
                message: "请选择需要删除的数据",
                callback: function () {
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
            callback: function (result) {
                if (result) {
                    var ids = "";
                    for (var i = 0; i < arr.length; i++) {
                        ids += ids == "" ? arr[i].contractId : "," + arr[i].contractId;
                    }
                    $.ajax({
                        url: "/contract/delAll",
                        data: {ids: ids},
                        success: function (result) {
                            alert(result.msg);
                            if (result.code == '0') {
                                initContract();
                            }

                        },
                        error: function (data) {
                            alert("检查后台代码")
                        }

                    })


                }
            }
        })
    }

    //导出数据
    function EXPContract() {
        var arr = $('#myTable').bootstrapTable('getSelections');
        if (arr.length <= 0) {
            bootbox.alert({
                size: "small",
                title: "提示",
                message: "请选择需要导出的数据",
                callback: function () {
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
            callback: function (result) {
                var ids = "";
                for (var i = 0; i < arr.length; i++) {
                    ids += ids == "" ? arr[i].contractId : "," + arr[i].contractId;
                }
                location.href = "/poi/createExcel?ids=" + ids + "&&type=1"
            }
        })
    }
    //导出模板
    function EXPmoban(){
        location.href="/poi/createExcelMoban?type=1"
    }
    /**
     * 文件上传
     */
    function initFile() {
        $("#fileUpload").fileinput({
            language: 'zh', //设置语言
            uploadUrl: '/addressBook/uploadBookFileData', //上传的地址
            allowedFileExtensions: ['xls', 'xlsx', 'csv'],//接收的文件后缀
            showUpload: true, //是否显示上传按钮
            showCaption: true,//是否显示标题
            showPreview: false, //是否显示预览
            dropZoneEnabled: false,//是否显示拖拽区域
            browseClass: "btn btn-primary", //按钮样式
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount: true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",

        }).on('fileuploaded', function (event, data, previewId, index) {
            if (data.response.code == "1") {
                alert(data.response.remark);
            } else {
                alert("操作成功！");
                $("#bookFileData").bootstrapTable('refresh');
            }
            // $(this).fileinput("reset").fileinput('unlock');
            $(this).fileinput('clear').fileinput('enable');
        });
    }

    //打开导入弹框
    $("#daoru").click(function () {
        $('#daoruDialog').modal();
    })

    function doUpload() {
        var formData = new FormData($("#uploadForm")[0]);
        $.ajax({
            url: '/poi/importContractFile',
            type: 'post',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                initContract();
            },
            error: function () {
                alert(result.msg);
            }
        });
    }
</script>
</html>

