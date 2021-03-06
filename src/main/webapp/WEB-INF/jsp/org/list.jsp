<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fns" uri="http://java.sun.com/jsp/jstl/functionss" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>用户管理</title>
    <jsp:include page="../body/link-page.jsp" flush="true"/>

</head>
<body>
<div class="panel">
    <div class="panel-heading">
        <button id="demo-dt-addrow-btn" class="btn btn-info" onclick="LAYER.addLay('layer_orgAdd','添加','/org/add')">
            <i class="fa fa-plus"></i> 添加
        </button>
        <button class="btn btn-success" onclick="toEdit();"><i class="fa fa-edit"></i> 修改</button>
        <button class="btn btn-warning" onclick="toAuth();"><i class="fa fa-thumb-tack"></i> 授权</button>
        <button class="btn btn-primary" onclick="deleteAllCheck();"><i class="fa fa-trash-o"></i> 删除</button>
    </div>
    <form id="form" action="/org/list" method="post">
        <div class="panel-body">
            <div id="demo-dt-addrow_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                <div class="newtoolbar">
                    <div id="demo-custom-toolbar2" class="table-toolbar-left"></div>
                </div>
                <table id="table" class="table table-striped table-bordered dataTable no-footer dtr-inline" cellspacing="0" width="100%" role="grid" aria-describedby="demo-dt-addrow_info" style="width: 100%;" data-pn="${page.pageNum}" data-ps="${page.pageSize}" data-tp="${page.totalPage}" data-tr="${page.totalRecord}">
                    <thead>
                    <tr role="row">
                        <th style="width: 20px;"><input type="checkbox" id="check-all" /></th>
                        <th>组织名称</th>
                        <th>父组织名称</th>
                        <th>描述</th>
                        <th>创建时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${not empty page.results}">
                            <c:forEach items="${page.results}" var="item" varStatus="vs">
                                <tr role="row" class="odd">
                                    <td><input type="checkbox" value="${item.id}" /></td>
                                    <td>${item.orgName}</td>
                                    <td>${item.pName}</td>
                                    <td>${item.description}</td>
                                    <td>${fns:dateToStringTime(item.createTime)}</td>
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
    function deleteAllCheck() {
        var ids = getAllCheck("form");
        if(ids.length == 0){
            layer.msg("至少选择一条数据");
        } else {
            layer.confirm("确定要删除" + ids.length + "条数据？",{title:"提示",btn:["确定","取消"]}, function(){
                $.ajax({
                    url:"/org/delete",
                    data:{"ids":ids.join(",")},
                    type:"post",
                    dataType:"json",
                    success:function(data){
                        if(data.success){
                            layer.msg("删除成功");
                            reloadPage();
                        } else {
                            layer.msg("系统异常，删除失败");
                        }
                    }
                });
            })
        }
    }

    function toEdit(){
        var id = getAllCheck("form");
        if(id.length != 1){
            layer.msg("请选择一条数据");
        } else {
            LAYER.addLayerWithSize("layer_edit","修改","/org/edit?id="+id,"400px","400px");
        }
    }

    function toAuth(){
        var id = getAllCheck("form");
        if(id.length != 1){
            layer.msg("请选择一条数据");
        } else {
            LAYER.addOrgMenuIframe("layer_orgMenu","选择框","${ctx}/tag/checktreeselect?id="+id,"400px","400px");
        }
    }

</script>

</body>
<jsp:include page="../body/javascript-page.jsp" flush="true"/>
</html>
