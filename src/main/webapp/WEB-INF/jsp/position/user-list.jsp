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
    <form id="form" action="/positionUser/userList" method="post">
        <div class="panel-body">
            <div id="demo-dt-addrow_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                <div class="newtoolbar">
                    <div id="demo-custom-toolbar2" class="table-toolbar-left"></div>
                </div>
                <table id="table" class="table table-striped table-bordered dataTable no-footer dtr-inline" cellspacing="0" width="100%" role="grid" aria-describedby="demo-dt-addrow_info" style="width: 100%;" data-pn="${page.pageNum}" data-ps="${page.pageSize}" data-tp="${page.totalPage}" data-tr="${page.totalRecord}">
                    <thead>
                    <tr role="row">
                        <th style="width: 20px;"><input type="checkbox" id="check-all" /></th>
                        <th>用户名</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>身份证号码</th>
                        <th>最后一次登录时间</th>
                        <th>状态</th>
                        <th>容量</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${not empty page.results}">
                            <c:forEach items="${page.results}" var="list" varStatus="vs">
                                <tr role="row" class="odd">
                                    <td><input type="checkbox" value="${list.id}" /></td>
                                    <td>${list.userName}</td>
                                    <td>${list.name}</td>
                                    <td>${list.sex == 1 ? "男" : "女"}</td>
                                    <td>${list.idNumber}</td>
                                    <td>${fns:dateToStringTime(list.lastLogin)}</td>
                                    <td>${list.status == "1" ? "可用" : "禁用"}</td>
                                    <c:if test="${list.availableSpace !=null && list.totalSpace !=null}">
                                        <td>${list.availableSpace}MB/${list.totalSpace}MB</td>
                                    </c:if>
                                    <c:if test="${list.availableSpace ==null || list.totalSpace ==null}">
                                        <td> </td>
                                    </c:if>

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
            <ul class="pagination"></ul>
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
                    url:"/user/delete",
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
            layer.msg("请选择一条数据",{icon:0,timeout:1000});
        } else {
            LAYER.addLay("layer_edit","修改用户","/user/edit?id="+id);
        }
    }
</script>

</body>
<jsp:include page="../body/javascript-page.jsp" flush="true"/>
</html>
