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
                    新增olt
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-2">所属站址编码:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="dxCode" id="dxCode" type="text"/>
                        <input class="form-control" name="dxCode" id="id" type="hidden"/>
                    </div>
                    <div class="col-xs-2">olt标识:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="oltCode" id="oltCode" type="text"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-2">olt名称:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="oltName" id="oltName" type="text"/>
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
                <button type="button" id="buttonAdd" class="btn btn-primary" onclick="submitOlt()">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<button type="button" onclick="openAddOlt()" class="btn btn-info glyphicon glyphicon-plus">新增</button>
<button type="button" onclick="delOlt()" class="btn btn-danger glyphicon glyphicon-minus">删除</button>
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



<table id="oltTable"></table>
</body>
<script type="text/javascript">
    <!--初始化加载页面-->
    $(function(){
        initOlt();
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

    function initOlt(){
        $('#oltTable').bootstrapTable('destroy');
        $("#oltTable").bootstrapTable({
            url:'/site/queryOlt',//获取数据地址
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
                }
            },
            columns:[
                {field:'111',checkbox:true},
                {field:'id',title:'OLTId',visible:false},
                {field:'dxCode',title:'所属站址编码'},
                {field:'oltCode',title:'OLT标识'},
                {field:'oltName',title:'OLT名称'},
                {field:'power',title:'月理论耗电量'},
                {field:'sign',title:'操作' ,class:'table-width',width:'10%',formatter:function(value,row,index){
                        return  ' <a href="javascript:editOlt('+row.id+',\'' + row.dxCode + '\',\'' + row.oltCode + '\',\'' + row.oltName + '\',\'' + row.power + '\');">修改</a>  ';
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

    function openAddOlt(){
        $("#id").val("");
        $("#dxCode").val("");
        $("#oltCode").val("");
        $("#oltName").val("");
        $("#power").val("");
        $('#myModal').modal();
    }


    function editOlt(id,dxCode,oltCode,oltName,power){
        $("#id").val(id);
        $("#dxCode").val(dxCode);
        $("#oltCode").val(oltCode);
        $("#oltName").val(oltName);
        $("#netCareId").val(netCareId);
        $("#netCareName").val(netCareName);
        $("#power").val(power);
        $('#myModal').modal();
    }
    //提交用户
    function submitOlt(){
        document.getElementById('buttonAdd').disabled=true;
        $.ajax({
            url: '/site/addOlt',
            type: "post",
            data : {
                id:$("#id").val(),
                dxCode:$("#dxCode").val(),
                oltCode:$("#oltCode").val(),
                oltName:$("#oltName").val(),
                power:$("#power").val()
            },
            success:function (data){
                document.getElementById('buttonAdd').disabled=false;
                initOlt();
                alert(data.msg)
                $("#myModal").modal('hide');

            },
            error:function (){
                alert("新增失败");
            }
        })
    }


    //打开修改的弹框
   /* function editOlt(id){
        bootbox.dialog({
            size:"big",
            title:"修改BBU信息",
            message:createAddContent("/page/toUpdateOlt?id="+id),
            closeButton:true,
            buttons:{
                'success':{
                    "label" : "<i class='icon-ok'></i> 保存",
                    "className" : "btn-sm btn-success",
                    "callback" : function() {
                        $.ajax({
                            url:'/site/updateOlt',
                            type:'post',
                            data:$("#oltForm").serialize(),
                            success:function(data){
                                bootbox.alert({
                                    size:"small",
                                    title:"提示",
                                    message:"修改成功！"
                                }),
                                    initOlt();
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

    //批量删除
    function delOlt(){
        var arr = $('#oltTable').bootstrapTable('getSelections');
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
                        url:"/site/delAllOlt",
                        data:{ids:ids},
                        success:function(result){
                            alert(result.msg);
                            if(result.code == '0'){
                                initOlt();
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
        var arr = $('#oltTable').bootstrapTable('getSelections');
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
                location.href="/poi/createExcel?ids="+ids+"&&type=8"
            }
        })
    }
    /**
     * 文件上传
     */
    function initFile(){
        $("#fileUpload").fileinput({
            language: 'zh', //设置语言
            uploadUrl: '/addressBook/uploadBookFileData', //上传的地址
            allowedFileExtensions: ['xls', 'xlsx', 'csv'],//接收的文件后缀
            showUpload: true, //是否显示上传按钮
            showCaption: true,//是否显示标题
            showPreview :false, //是否显示预览
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
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",

        }).on('fileuploaded', function(event, data, previewId, index) {
            if(data.response.code == "1"){
                alert(data.response.remark);
            }else {
                alert("操作成功！");
                $("#bookFileData").bootstrapTable('refresh');
            }
            // $(this).fileinput("reset").fileinput('unlock');
            $(this).fileinput('clear').fileinput('enable');
        });
    }
    //打开导入弹框
    $("#daoru").click(function(){
        $('#daoruDialog').modal();
    })
    function doUpload() {
        var formData = new FormData($( "#uploadForm" )[0]);
        $.ajax({
            url: '/poi/importOLTFile',
            type: 'post',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                initOlt();
            },
            error: function () {
                alert(result.msg);
            }
        });
    }

</script>
</html>

