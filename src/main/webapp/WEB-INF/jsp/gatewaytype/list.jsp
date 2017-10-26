<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fns" uri="http://java.sun.com/jsp/jstl/functionss" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>网关类型</title>
    <jsp:include page="../body/link-page.jsp" flush="true"/>

</head>

<body>
<div class="panel">
    <div class="panel-heading">
        <button id="demo-dt-addrow-btn" class="btn btn-info" onclick="addLay('layer_gatewayType','添加网关类型','/gatewayType/add','670px','233px')"><i
                class="fa fa-plus"></i> 添加
        </button>
        <button class="btn btn-primary" onclick="deleteAllCheck();"><i class="fa fa-trash-o"></i> 删除</button>
    </div>
    <form id="form" action="/gatewayType/list" method="post">
        <div class="panel-body">
            <div id="demo-dt-addrow_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                <div class="newtoolbar">
                    <div id="demo-custom-toolbar2" class="table-toolbar-left"></div>
                </div>
            </div>

            <table id="table" class="table table-striped table-bordered dataTable no-footer dtr-inline" cellspacing="0"
                   width="100%" role="grid" aria-describedby="demo-dt-addrow_info" style="width: 100%;"
                   data-pn="${pageList.pageNum}" data-ps="${pageList.pageSize}" data-tp="${pageList.totalPage}"
                   data-tr="${pageList.totalRecord}">
                <thead>
                <tr role="row">
                    <th style="width: 20px;"><input type="checkbox" id="check-all"/></th>
                    <th>类型名称</th>
                    <th>创建时间</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty pageList.results}">
                <c:forEach var="item" items="${pageList.results}" varStatus="status">
                    <tr>
                        <td><input type="checkbox" value="${item.id}"/></td>
                        <td>${item.name}</td>
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
            <ul class="pagination"></ul>
        </div>
</form>
</div>
<jsp:include page="../body/javascript-page.jsp" flush="true"/>

<script type="text/javascript">

    /**
     * 弹出添加页面
     * @param title
     * @param url
     */
    function addLay(id, title, url, width, height) {
        $.ajax({
            type: 'get',
            dataType: 'html',
            url: url,
            success: function (data) {
                top[id] = layer.open({
                    type: 1,
                    title: title,
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: [width, height],
                    content: data
                });
            },
            error: function (data) {
                alert("没有找到此页面");
            }
        });
    }

    /**
     * 删除
     */
    function deleteAllCheck() {
        var ids = getAllCheck("form");
        if (ids.length == 0) {
            layer.msg("至少选择一条数据");
        } else {
            layer.confirm("确定要删除" + ids.length + "条数据吗？", {title: "提示", btn: ["确定", "取消"]}, function () {
                $.ajax({
                    url: "/gatewayType/delete",
                    data: {"ids": ids.join(",")},
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            layer.msg(data.msg);
                            reloadPage();
                        } else {
                            layer.msg(data.msg);
                        }
                    }
                });
            });
        }
    }

</script>
</body>
</html>
