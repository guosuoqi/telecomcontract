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
                    新增3GRRU
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-2">所属站址编码:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="dxCode" id="id" type="hidden"/>
                        <input class="form-control" name="dxCode" id="dxCode" type="text"/>
                    </div>
                    <div class="col-xs-2">CI:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="rruCode" id="rruCode" type="text"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-2">小区名称:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="rruName" id="rruName" type="text"/>
                    </div>
                    <div class="col-xs-2">月理论耗电量:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="power"id="power" type="text"/>
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



<button type="button" onclick="openAdd3GRRU()" class="btn btn-info glyphicon glyphicon-plus">新增</button>
<button type="button" onclick="del3GRRU()" class="btn btn-danger glyphicon glyphicon-minus">删除</button>
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
<table id="3GRRUTable"></table>
</body>
<script type="text/javascript">
    <!--初始化加载页面-->
    $(function(){
        initRRU();
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

    function initRRU(){
        $('#3GRRUTable').bootstrapTable('destroy');
        $("#3GRRUTable").bootstrapTable({
            url:'/site/queryRRU',//获取数据地址
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
                    networkType:3,
                }
            },
            columns:[
                {field:'111',checkbox:true},
                {field:'id',title:'3GRRUId',visible:false},
                {field:'dxCode',title:'所属站址编码'},
                {field:'rruCode',title:'CI'},
                {field:'rruName',title:'小区名称'},
                {field:'power',title:'月理论耗电量'},
                {field:'sign',title:'操作' ,class:'table-width',width:'10%',formatter:function(value,row,index){
                        return  ' <a href="javascript:editRRu('+row.id+',\'' + row.dxCode + '\',\'' + row.rruCode + '\',\'' + row.rruName + '\',\'' + row.power + '\');">修改</a>  ';
                    }}
            ]
        })
    }

    function openAdd3GRRU(){
        $("#id").val("");
        $("#dxCode").val("");
        $("#rruCode").val("");
        $("#rruName").val("");
        $("#power").val("");
        $('#myModal').modal();
    }

    function editRRu(id,dxCode,rruCode,rruName,power){
        $("#id").val(id);
        $("#dxCode").val(dxCode);
        $("#rruCode").val(rruCode);
        $("#rruName").val(rruName);
        $("#power").val(power);
        $('#myModal').modal();
    }

    //提交用户
    function submit3GBBU(){
        document.getElementById('buttonAdd').disabled=true;
        $.ajax({
            url: '/site/add3GRRU',
            type: "post",
            data : {
                id:$("#id").val(),
                dxCode:$("#dxCode").val(),
                rruCode:$("#rruCode").val(),
                rruName:$("#rruName").val(),
                power:$("#power").val(),
                networkType:3
            },
            success:function (data){
                initRRU();
                alert(data.msg)
                $("#myModal").modal('hide');
                document.getElementById('buttonAdd').disabled=false;
            },
            error:function (){
                alert("新增失败");
            }
        })
    }


    //批量删除
    function del3GRRU(){
        var arr = $('#3GRRUTable').bootstrapTable('getSelections');
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
                        url:"/site/delAllRRU",
                        data:{ids:ids},
                        success:function(result){
                            alert(result.msg);
                            if(result.code == '0'){
                                initRRU();
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
        var arr = $('#3GRRUTable').bootstrapTable('getSelections');
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
                location.href="/poi/createExcel?ids="+ids+"&&type=5"
            }
        })
    }
    //打开导入弹框
    $("#daoru").click(function(){
        $('#daoruDialog').modal();
    })
    function doUpload() {
        var formData = new FormData($("#uploadForm" )[0]);
        $.ajax({
            url: '/poi/importRRUFile',
            type: 'post',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                initRRU();
            },
            error: function () {
                alert(result.msg);
            }
        });
    }

</script>
</html>

