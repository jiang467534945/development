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
        <button id="demo-dt-addrow-btn" class="btn btn-info" onclick="LAYER.addLay('tjcd','添加表','/gen/add')"><i
                class="fa fa-plus"></i> 添加菜单
        </button>
        <button class="btn btn-primary"><i class="fa fa-minus"></i> 批量删除</button>
    </div>
    <div class="panel-body">
        <div id="demo-dt-addrow_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
            <div class="newtoolbar">
                <div id="demo-custom-toolbar2" class="table-toolbar-left">
                    <%--
                                <button id="demo-dt-addrow-btn" class="btn btn-primary"><i class="demo-pli-plus"></i> Add row</button>
                    --%>
                </div>
            </div>
            <table id="demo-dt-addrow" class="table table-striped table-bordered dataTable no-footer dtr-inline"
                   cellspacing="0" width="100%" role="grid" aria-describedby="demo-dt-addrow_info" style="width: 100%;">
                <thead>
                <tr role="row">
                    <th>表名</th>
                    <th>描述</th>
                    <th>实体类名称</th>
                    <th>关联父表</th>
                    <th>关联父表外键</th>
                    <th>操作</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="gen" items="${genList}" varStatus="status">
                    <tr role="row" class="odd">
                        <td class="sorting_1">${gen.name}</td>
                        <td>${gen.comments}</td>
                        <td>${gen.className}</td>
                        <td>${gen.parentTable}</td>
                        <td>${gen.parentTableFk}</td>
                        <td><button class="btn btn-primary"><i class="fa fa-minus"></i> 生成</button>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<jsp:include page="../body/javascript-page.jsp" flush="true"/>
</html>
