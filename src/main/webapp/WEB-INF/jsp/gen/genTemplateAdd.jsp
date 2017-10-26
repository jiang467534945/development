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

                    <div class="row">
                        <div class="col-md-6">

                            <div class="form-group">
                                <label class="col-md-3 control-label">归属分类:</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <select class="form-control select" name="categoryList" id="tableName">
                                            <option value="">请选择分类</option>
                                            <c:forEach items="${fns:getDictList('gen_category')}" var="table" varStatus="status">
                                                <option value="${table.id}">${table.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <span class="help-block">选择分类</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">生成路径:</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" name="filePath" value="" >
                                    </div>
                                    <span class="help-block">请输入菜单名称</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">生成文件名:</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-circle"></span></span>
                                        <input type="text" class="form-control" name="fileName"  value="" >
                                    </div>
                                    <span class="help-block">示例如下：<br/>
					java：\${ClassName}Entity.jsp<br/>
					view：\${className}List.jsp</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">内容:</label>
                                <div class="col-md-9 col-xs-12">
                                    <textarea class="form-control" rows="5" name="menuContext" value="" ></textarea>
                                    <span class="help-block">请输入需要添加菜单的简介</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">


                            <div class="form-group">
                                <label class="col-md-3 control-label">菜单路径</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-retweet"></span></span>
                                        <input type="text" class="form-control" name="url" value=""  >
                                    </div>
                                    <span class="help-block">This is sample of text field</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">菜单排序</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-sort"></span></span>
                                        <input type="text" class="form-control" name="sort" value="" >
                                    </div>
                                    <span class="help-block">This is sample of text field</span>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>
                <div class="panel-footer">
                    <button class="btn btn-default">Clear Form</button>
                    <input type="button"class="btn btn-primary" onclick="save()" value="保存">
                </div>
            </div>

        </form>

    </div>
</div>
<script>
    function save() {
        var param = serializeForm("addForm");
        $.ajax({
            type: 'post',
            dataType: 'json',
            data: param,
            async: true,
            url: '/menu/save',
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
