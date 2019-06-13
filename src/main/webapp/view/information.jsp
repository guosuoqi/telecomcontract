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
    <title>信息管理</title>
</head>

<body>
11111111

</body>

<script type="text/javascript">
    <!--初始化加载页面-->
    $(function(){
        initIncidentQuery();
    })

    function initIncidentQuery(){
        alert(111)
        $("#incidentQuery").bootstrapTable({
            url:'<%=request.getContextPath()%>/contract/queryContract',//获取数据地址
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
            onClickRow:function (row,$element) {
                alert(row.reportId)
                queryTrack(row.reportId);
            },
            columns:[
                {field:'333',checkbox:true},
                {field:'contractId',title:'合同id'},
                {field:'contractName',title:'合同名字'},
                {field:'city',title:'省'},
                {field:'county',title:'市'},
                {field:'yearRental',title:'年租金'},
                {field:'sunRental',title:'总租金'},
                {field:'contractNum',title:'合同编号'},
                {field:'contractFirst',title:'合同甲方'},
                {field:'payee',title:'收款人'},
                {field:'planYear',title:'拟租年份'},
                {field:'startTime',title:'开始时间'},
                {field:'endTime',title:'结束时间'},
                {field:'payEndTime',title:'付费截止日期'},
                {field:'roomTypeName',title:'机房类型'},
                {field:'towerTypeName',title:'塔栀类型'},
                {field:'contractTypeName',title:'合同类型'},
                {field:'111',title:'操作',formatter:function(value,row,index){
                        return  '<a href="javascript:delOne('+row.id+')">咨询</a> | <a href="javascript:editUser('+row.id+');">改数</a> | <a href="javascript:editJieSuo('+row.id+',\''+row.password+'\');">其他</a> ';
                    }}
            ]
        })
    }
</script>
</html>

