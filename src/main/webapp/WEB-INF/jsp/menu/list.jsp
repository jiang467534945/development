<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
    <jsp:include page="../body/link-page.jsp" flush="true"/>

</head>
<body>
<div class="panel">
    <div class="panel-heading">
        <button id="demo-dt-addrow-btn" class="btn btn-info" onclick="LAYER.addLayerWithSize('layer_menu','添加菜单','/menu/add','800px','420px')">
            <i class="fa fa-plus"></i> 添加
        </button>
        <button class="btn btn-success" onclick="toEdit();"><i class="fa fa-edit"></i>修改</button>
        <button class="btn btn-primary" onclick="deleteAll();"><i class="fa fa-trash-o"></i>删除</button>
    </div>

    <form id="form" action="/menu/list" method="post">
        <div class="panel-body">
            <div id="demo-dt-addrow_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                <div class="newtoolbar">
                    <div id="demo-custom-toolbar2" class="table-toolbar-left">
                        <%--
                                    <button id="demo-dt-addrow-btn" class="btn btn-primary"><i class="demo-pli-plus"></i> Add row</button>
                        --%>
                    </div>
                </div>
            </div>
            <table id="table" class="table table-striped table-bordered dataTable no-footer dtr-inline"
                   cellspacing="0" width="100%" role="grid" aria-describedby="demo-dt-addrow_info"
                   style="width: 100%;"
                   data-pn="${menuList.pageNum}" data-ps="${menuList.pageSize}" data-tp="${menuList.totalPage}"
                   data-tr="${menuList.totalRecord}">
                <thead>
                <tr role="row">
                    <th style="width: 20px;"><input type="checkbox" id="check-all"/></th>
                    <th>按钮编号</th>
                    <th>按钮名称</th>
                    <th>上级按钮</th>
                    <th>按钮图标</th>
                    <th>按钮路径</th>
                    <th>按钮简介</th>
                    <th>按钮排序</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="menu" items="${menuList.results}" varStatus="status">
                    <tr role="row" class="odd">
                        <td><input type="checkbox" value="${menu.id}"/></td>
                        <td class="sorting_1">${menu.id}</td>
                        <td>${menu.menuName}</td>
                        <td>${menu.parent.menuName}</td>
                        <td>${menu.icon}</td>
                        <td>${menu.url}</td>
                        <td>${menu.menuContext}</td>
                        <td>${menu.sort}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul class="pagination"></ul>
        </div>

    </form>
</div>
</body>
<jsp:include page="../body/javascript-page.jsp" flush="true"/>
<script type="application/javascript">
    function toEdit(){
        var id = getAllCheck("form");
        if(id.length != 1){
            layer.msg("请选择一条数据",{icon:0,timeout:1000});
        } else {
            LAYER.addLayerWithSize("layer_edit","修改菜单","/menu/edit?id="+id,"800px","420px");
        }
    }
    function  deleteAll() {
        var  ids=getAllCheck("form");
        if(ids.length <=0){
            layer.msg("请选择删除的数据",{icon:0,timeout:1000});
        }else{
            layer.confirm("确定要删除" + ids.length + "条数据？",{title:"提示",btn:["确定","取消"]},function () {
                $.ajax({
                    type:"POST",
                    url:"/menu/delete",
                    dataType:"json",
                    data:{"ids":ids.join(",")},
                    success:function (data) {
                        if(data.success){
                            layer.msg("删除成功",{icon:1,timeout:1000});
                            reloadPage();
                        } else {
                            layer.msg("删除失败",{icon:2,timeout:1000});
                        }
                    }
                    });
            });

        }

     }
</script>
</html>
