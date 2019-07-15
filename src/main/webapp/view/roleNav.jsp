<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	<script src="/static/js/select.js"></script>
	<title>角色权限管理</title>
</head>
<body>

<table id="myRoles"></table>
<table id="navTreeTable"></table>
</body>
<script type="text/javascript">
	$(function(){
		initRoleTable();
	})
	//查询角色
	function initRoleTable(){
		$('#myRoles').bootstrapTable('destroy');
		$("#myRoles").bootstrapTable({
			url: '/user/queryRoleAll',
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
		    columns:[[
		              {field:'11',checkbox:true},    
		              {field:'id',title:'主键'},    
		              {field:'name',title:'名称'},    
		              {field:'123',title:'操作',formatter:function(value,row,index){
							return '<a href="javascript:bindPower('+row.id+');">绑定权限</a>';
						}}
		              ]]
		})
	}

	//初始化权限树
	function bindPower(roleId){
		$("#roleHideId").val(roleId);
		$('#navTreeTable').bootstrapTable('destroy');
		$("#navTreeTable").bootstrapTable({
			url: '/user/queryRoleNav',
			checkbox:true,
			lines:true,
			parentField:'pid',
			onDblClick:function(node){
				$('#navHideId').val(node.id);
				initDetailMenu(node.id);
			}
		})
	}
	/*	//查询详细权限
        function initDetailMenu(navId){
            $('#detailMenu').datagrid({
                url:'../user/queryPowerMenuList.do?navId='+navId,
                fit:true,
                toolbar:'#detailMenuToolBar',
                //pagination:true,
                pageNumber:1,
                pageSize:5,
                pageList:[3,5,10,20,50],
                columns:[[
                    {field:'333',checkbox:true},
                    {field:'id',title:'id'},
                    {field:'name',title:'菜单名称'},
                    {field:'url',title:'请求地址'},
                    {field:'123',title:'操作',formatter:function(value,row,index){
                        return '<a href="javascript:delOneMenu('+row.id+')">删除</a> | <a href="javascript:editMenu('+row.id+')">修改</a>';
                    }}
                ]]
            })
        }
        //打开权限菜单弹框
        function openPowerMenuDialog(){
            var navHideId=$("#navHideId").val();
            if(navHideId==null || navHideId==''){
                $.messager.alert('提示','请选择需要添加菜单的权限树节点','warning')
                 return;
            }
            $('#menuForm').form('reset');
            $('#powerMenuDialog').dialog('open');
        }
        //保存权限菜单
        function savePowerMenu(){
            var navHideId = $('#navHideId').val();
            if(navHideId == null || navHideId == ''){
                $.messager.alert('提示','请选择需要添加菜单的权限树节点','warnning');
                return;
            }
            $.post('../user/addPowerMenu.do',{
                id:$('#menuId').val(),
                name:$('#menuName').val(),
                url:$('#menuUrl').val(),
                navId:navHideId
            },function(data){
                if (data) {
                    initDetailMenu(navHideId);
                    $('#powerMenuDialog').dialog('close');
                }else{
                    $.messager.alert('提示','保存失败','error')
                }
            })
        }
        //保存角色权限
        function saveRolePower(){
            var roleId=$("#roleHideId").val()
            var navAll=$("#navTreeTable").tree("getChecked")
            var navIds='';
            for (var i = 0; i < navAll.length; i++) {
                navIds += navIds == '' ? navAll[i].id:','+navAll[i].id;
            }
            $.post('../user/saveRolePower.do',{roleId:roleId,navIds:navIds},function(data){
                if(data){
                    $.messager.alert('提示','保存角色权限成功','info');
                }else{
                    $.messager.alert('提示','保存角色权限失败','error');
                }

            })
        }*/
</script>
</html>