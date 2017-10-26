<%--
  Created by IntelliJ IDEA.
  User: Easy
  Date: 2017-09-27
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>

<div class="row">
    <div class="col-md-12">

        <form  class="form-horizontal" id="addForm" >
            <div class="panel panel-default">
                <div class="panel-body">
                    <input type="hidden" class="form-control" id="flag" name="flag" value="" >

                    <div class="row">
                        <div class="col-md-6">

                            <div class="form-group">
                                <label class="col-md-3 control-label">模板分类</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <%--<span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" readonly="true">--%>
                                            <select class="form-control select" name="category" id="tableName">
                                                <option value="">请选择模板分类</option>
                                                <c:forEach items="${config.categoryList}" var="table" varStatus="status">
                                                    <option value="${table.value}">${table.label}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                    </div>
                                    <span class="help-block">请选择模板分类</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">方案名称:</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" name="name" value="" >
                                    </div>
                                    <span class="help-block">请输入方案名称</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">生成包路径:</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-circle"></span></span>
                                        <input type="text" class="form-control" name="packageName"  value="" >
                                    </div>
                                    <span class="help-block">建议模块包：com.plaform</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">生成模块名:</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-circle"></span></span>
                                        <input type="text" class="form-control" name="moduleName"  value="" >
                                    </div>
                                    <span class="help-block">可理解为子系统名，例如 upms</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">子模块名:</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-circle"></span></span>
                                        <input type="text" class="form-control" name="subModuleName"  value="" >
                                    </div>
                                    <span class="help-block">可选，分层下的文件夹，例如</span>
                                </div>
                            </div>



                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-3 control-label">功能描述:</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-circle"></span></span>
                                        <input type="text" class="form-control" name="functionName"  value="" >
                                    </div>
                                    <span class="help-block">将设置到类描述</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">生成功能名:</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-circle"></span></span>
                                        <input type="text" class="form-control" name="functionNameSimple"  value="" >
                                    </div>
                                    <span class="help-block">用作功能提示，如：保存“某某”成功</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">功能作者:</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-circle"></span></span>
                                        <input type="text" class="form-control" name="functionAuthor"  value="" >
                                    </div>
                                    <span class="help-block">功能开发者</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">业务表名</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <%--<span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" readonly="true">--%>
                                        <select class="form-control select" name="genTable.id" >
                                            <option value="">请选择业务表名</option>
                                            <c:forEach items="${tableList}" var="tableName" varStatus="status">
                                                <option value="${tableName.id}">${tableName.nameAndComments}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <span class="help-block">请选择业务表名</span>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>
                <div class="panel-footer">
                    <button class="btn btn-default">Clear Form</button>
                    <input type="button"class="btn btn-primary" onclick="save()" value="保存并生成代码">
                </div>
            </div>

        </form>

    </div>
</div>
<script>
    function save() {
        $('#flag').val('1');
        var param = $("#addForm").serialize();
            $.ajax({
            type: 'post',
            dataType: 'json',
            data: param,
            async: true,
            url: '/gen/genScheme/save',
            success: function (data) {
                alert(data);
            },error: function () {
                alert("保存未成功")
            }

        })
    }
    /*        pageLoadingFrame("show");
            document.getElementById("addForm").submit();*/



</script>
