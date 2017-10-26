<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fns" uri="http://java.sun.com/jsp/jstl/functionss" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>设备类型</title>
    <jsp:include page="../body/link-page.jsp" flush="true"/>

</head>
<body>
<div class="panel">
    <div class="panel-heading">
        <button id="demo-dt-addrow-btn" class="btn btn-info" onclick="LAYER.addLay('layer_typeAdd','添加设备类型','/familyType/add')">
            <i class="fa fa-plus"></i> 添加
        </button>
        <%--<button class="btn btn-success"onclick="LAYER.addLay('layer_typeEdit','修改设备类型','/familyType/edit')"><i class="fa fa-edit"></i> 修改</button>--%>
        <button class="btn btn-success" onclick="toEdit();"><i class="fa fa-edit"></i> 修改</button>
        <button class="btn btn-primary" onclick="toDelete();"><i class="fa fa-trash-o"></i> 删除</button>
    </div>
    <form id="form" action="/familyType/list" method="post">
        <div class="panel-body">
            <div id="demo-dt-addrow_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                <div class="newtoolbar">
                    <div id="demo-custom-toolbar2" class="table-toolbar-left"></div>
                </div>
                <table id="table" class="table table-striped table-bordered dataTable no-footer dtr-inline" cellspacing="0" width="100%" role="grid" aria-describedby="demo-dt-addrow_info" style="width: 100%;" data-pn="${page.pageNum}" data-ps="${page.pageSize}" data-tp="${page.totalPage}" data-tr="${page.totalRecord}">
                    <thead>
                    <tr role="row">
                        <th style="width: 20px;"><input type="checkbox" id="check-all" /></th>
                        <th>类型名称</th>
                        <th>识别码</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${not empty pageList.results}">
                            <c:forEach items="${pageList.results}" var="item" varStatus="vs">
                                <tr role="row" class="odd">
                                    <td><input type="checkbox" value="${item.id}" /></td>
                                    <td>${item.name}</td>
                                    <td>${item.number}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr role="row" class="odd">
                                <td colspan="10" style="text-align: center">暂无数据</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
            <ul class="pagination">
            </ul>
        </div>
    </form>
</div>

<script type="text/javascript">
    function toDelete() {
        var ids = getAllCheck("form");//获取所有被选中的复选框
        console.info(ids);
        if(ids.length == 0){
            layer.msg("至少选择一条数据",{icon:0,timeout:1000});
        } else {
            layer.confirm("确定要删除" + ids.length + "条数据？",{title:"提示信息",btn:["确定","取消"]}, function(){
                $.ajax({
                    url:"/familyType/deletes",
                    data:{"ids":ids.join(",")},
                    type:"post",
                    dataType:"json",
                    success:function(data){
                        if(data.success){
                            layer.msg("删除成功",{icon:1,timeout:1000});
                            reloadPage();
                        } else {
                            layer.msg("系统异常，删除失败",{icon:2,timeout:1000});
                        }
                    }
                });
            })
        }
    }

    function toEdit(){
        var id = getAllCheck("form");
        if(id.length != 1){
            layer.msg("请选择一条数据",{icon:0,timeout:1000});
        } else {
            LAYER.addLay("layer_typeEdit","修改设备类型","/familyType/edit?id="+id);
        }
    }
</script>

</body>
<jsp:include page="../body/javascript-page.jsp" flush="true"/>
</html>
