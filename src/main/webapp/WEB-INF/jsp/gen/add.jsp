<%--
  Created by IntelliJ IDEA.
  User: Easy
  Date: 2017-09-27
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>

<div class="row">
<div class="col-md-12">

    <form class="form-horizontal" id="addForm">
        <div class="panel panel-default">


            <div class="panel-body">


                <div class="form-group">
                    <label class="col-md-3 col-xs-12 control-label">请选择数据表</label>
                    <div class="col-md-6 col-xs-12">
                        <select class="form-control select" name="name" id="tableName">
                            <option value="">请选择数据表</option>
                            <c:forEach items="${tableList}" var="table" varStatus="status">
                                <option value="${table.name}">${table.name}：${table.comments}
                                </option>
                            </c:forEach>
                        </select>
                        <span class="help-block">选择数据表</span>
                    </div>
                </div>
            </div>
            <div class="panel-footer">
                <button class="btn btn-default">Clear Form</button>
                <input type="button"class="btn btn-primary" onclick="save()" value="下一步">
            </div>
        </div>
    </form>

</div>
</div>
<script>
    function save() {
        LAYER.addGenLay("addFa","添加方案","/gen/twoadd?name="+$("#tableName").val(),"1000px","600px")
    }

    /*        pageLoadingFrame("show");
            document.getElementById("addForm").submit();*/


</script>
