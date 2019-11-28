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
                    合同续费
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-2">合同名称:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="contractId" id="contractId" type="hidden"/>
                        <input class="form-control" name="renewStatus" id="renewStatus" type="hidden"/>
                        <input class="form-control" name="contractName"  id="contractNameEdit" type="text"/>
                    </div>
                    <div class="col-xs-2">续费截止日期:</div>
                    <div class="col-xs-4">
                        <input class="form-control date" name="payEndTime"  id="payEndTime" type="text"/>
                    </div>
                </div>

                <div class="row">

                    <div class="col-xs-2">经办人:</div>
                    <div class="col-xs-4">
                        <input class="form-control" name="extenxionOperator"  id="extenxionOperator" type="text"/>
                    </div>
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
<div class="panel">
    <div class="panel-body" style="padding-bottom: 1px;">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-12 "> <%--<td>--%>
                    <div class="col-sm-4 "> <%--<td>--%>
                        <div class="col-sm-3 "> <%--占4格--%>
                            <label for="contractName">合同</label>
                        </div>
                        <div class="col-sm-9"> <%--占8格，充满--%>
                            <input type="text" name="contractName" id="contractName"  class="form-control" placeholder="请输入合同名称">
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

                    <div class="col-xs-12">
                        <button type="button" class="btn btn-primary btn-w-m" onclick="initContract()" id="queryBtn" style="float: right;margin-right:20px;">
                            <span class="glyphicon glyphicon-search"></span> 搜索
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<%--<button type="button" onclick="openAddDialogExtension()" class="btn btn-info glyphicon glyphicon-plus">新增</button>--%>
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
                {field:'contractName',title:'合同名字'},
                {field:'yearRental',title:'年租金'},
                {field:'payEndTime',title:'续费截止日期'},
                {field:'extenxionOperator',title:'经办人'},
                {field:'remark',title:'备注'},
                {field:'sign',title:'操作' ,class:'table-width',width:'10%',formatter:function(value,row,index){
                        return  ' <a href="javascript:editContract(\'' + row.contractId + '\',\'' + row.contractName + '\',\'' + row.payEndTime + '\',\'' + row.extenxionOperator + '\',\'' + row.remark + '\')">修改</a>  ';
                    }}
            ]
        })
    }



    function editContract(contractId,contractName,payEndTime,extenxionOperator,remark){
        $("#contractId").val(contractId);
        $("#contractNameEdit").val(contractName);
        $("#payEndTime").val(payEndTime);
        $("#extenxionOperator").val(extenxionOperator);
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
                contractName:$("#contractNameEdit").val(),
                payEndTime:$("#payEndTime").val(),
                extenxionOperator:$("#extenxionOperator").val(),
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

    /* var res;
        function createAddContent(url){
            $.ajax({
                url:url,
                async:false,
                success:function(data){
                    res = data;
                }
            });
            return res;
        }*/

    /*  //打开修改的弹框
      function editContractExtendion(contractId){
          bootbox.dialog({
              size:"big",
              title:"修改合同信息",
              message:createAddContent("/page/toUpdateContractExtension?contractId="+contractId),
              closeButton:true,
              buttons:{
                  'success':{
                      "label" : "<i class='icon-ok'></i> 保存",
                      "className" : "btn-sm btn-success",
                      "callback" : function() {
                          $.ajax({
                              url:'/contract/updateContract',
                              type:'post',
                              data:$("#contractFormExtension").serialize(),
                              success:function(data){
                                  bootbox.alert({
                                      size:"small",
                                      title:"提示",
                                      message:"修改成功！"
                                  }),
                                      initContract();
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
  */
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

