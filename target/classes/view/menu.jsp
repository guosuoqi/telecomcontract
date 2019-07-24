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
    <title>合同及机房管理系统</title>
</head>



<body>
<style>

    .navbar{
        margin-bottom: 0px;
        border-radius: 5px;
        background: rgba(11, 73, 128, 0.77);
    }
    #imgb{
        border-radius:200px;
    }
    .imgp{
        border-radius:20px;
    }
    li{onhoverColor:"green"}
</style>
<!-- 导航条 -->
<nav class="navbar  navbar-inverse  navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand"  href="#"><font color="white">合同及机房管理系统</font></a>

        </div>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <li><a><font color="white">欢迎您,${user.userName}</font></a>
                </li>
                <li>
                    <a onclick="exit()"><font color="white">安全退出</font></a>
                </li>
            </ul>
        </div>

    </div>
</nav>
<!-- 内容布局容器 -->
<div class="container-fluid" style="margin-top: 59px;">
    <div class="row">
        <div class="col-sm-2">
            <div id="myTree"></div>
        </div>
        <div class="col-sm-10">
            <div id="myTabs">
                <!-- 新选项卡 -->
                <ul class="nav nav-tabs" id="tablist">
                    <li role="presentation" class="active">
                        <a href="#home" role="tab" data-toggle="tab">欢迎</a>
                    </li>
                </ul>

                <!-- 选项卡下内容 -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="home">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>

<script type="text/javascript">
    $(function(){
        initMyTree();
    })

/*    function initMyTree(){
        $.ajax({
            url:'/nav/toShowTree',
            type:'post',
            success:function(result){
                $('#myTree').treeview({
                    data:result,
                    onNodeSelected:function(event,node){
                        $.addtabs({iframeHeight: 650});
                        $.addtabs.add({
                            id:node.id,
                            title:node.text,
                            url:node.href
                        });
                    }
                })
            }
        })
    }*/

    function initMyTree(){
        $.ajax({
            url:'/nav/toShowTree',
            success:function(data){
                //alert(data)
                $('#myTree').treeview({

                    //设置列表树的节点在用户鼠标滑过时的背景颜色
                    onhoverColor:"#187480",
                    data:data,
                    onNodeSelected:function(event, node) {
                        $.addtabs({iframeHeight: 650});
                        //addtabs(event, node)
                        $.addtabs.add({
                            id:node.id,
                            title:node.text,
                            url:node.href
                        });
                    }
                })
                $("#myTree").treeview('collapseAll');//关闭展开
            }
        })
    }

//退出登录
    function exit(){
        location.href="/user/exitLogin"
    }
</script>
</html>

