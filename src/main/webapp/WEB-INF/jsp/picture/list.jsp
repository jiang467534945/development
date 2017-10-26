<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>我的图库</title>
    <jsp:include page="../body/link-page.jsp" flush="true"/>

</head>
<body>
<div class="panel">
    <div class="panel-heading">
        <button id="demo-dt-addrow-btn" class="btn btn-info" onclick="LAYER.addLay('layer_userAdd','添加','/picture/add')">
            <i class="fa fa-plus"></i> 添加
        </button>
        <button class="btn btn-success" onclick="toEdit();"><i class="fa fa-edit"></i> 修改</button>
        <button class="btn btn-primary" onclick="deleteAllCheck();"><i class="fa fa-trash-o"></i> 删除</button>
    </div>
    <form id="form" action="/picture/list" method="post" >
        <div class="panel-body">
            <div id="demo-dt-addrow_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                <div class="newtoolbar">
                    <div id="demo-custom-toolbar2" class="table-toolbar-left"></div>
                </div>
                <table id="table" class="table table-striped table-bordered dataTable no-footer dtr-inline" cellspacing="0" width="100%" role="grid" aria-describedby="demo-dt-addrow_info" style="width: 100%;" data-pn="${page.pageNum}" data-ps="${page.pageSize}" data-tp="${page.totalPage}" data-tr="${page.totalRecord}">
                    <thead>
                    <tr role="row">
                        <th style="width: 20px;"><input type="checkbox" id="check-all" /></th>
                        <th>姓名</th>
                        <th>图片</th>
                        <th>时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${not empty page.results}">
                            <c:forEach items="${page.results}" var="list" varStatus="vs">
                                <tr role="row" class="odd">
                                    <td><input type="checkbox" /></td>
                                    <td>${list.userName}</td>
                                    <td><img src="..${list.picUrl}" style="max-width:100px;height:50px;"></td>
                                    <td>${list.createTime}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr role="row" class="odd">
                                <td colspan="10" class="center">暂无数据</td>
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
</body>
<jsp:include page="../body/javascript-page.jsp" flush="true"/>
</html>
