<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>

<div class="row">
    <div class="col-md-12">

        <form id="user-edit" action="" class="form-horizontal" >
            <div class="panel panel-default">
                <div class="panel-body">

                    <div class="row">
                        <div class="col-md-6">

                            <div class="form-group">
                                <label class="col-md-3 control-label">用户名</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" name="userName" class="form-control" value="${user.userName}">
                                    </div>
                                    <span class="help-block">请输入用户名</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">姓名</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" name="name" class="form-control" value="${user.name}">
                                    </div>
                                    <span class="help-block">请输入姓名</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">身份证号码</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input name="idNumber" type="text" class="form-control" value="${user.idNumber}">
                                    </div>
                                    <span class="help-block">请输入身份证号码</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">存储空间</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input name="totalSpace" type="text" class="form-control" value="${user.totalSpace}">
                                    </div>
                                    <span class="help-block">请输入存储空间(单位:MB)</span>
                                </div>
                            </div>

                        </div>
                        <div class="col-md-6">

                            <div class="form-group">
                                <label class="col-md-3 control-label">密码</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input name="password" type="password" class="form-control" placeholder="不填则不修改">
                                    </div>
                                    <span class="help-block">请输入密码</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">性别</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <select name="sex" class="form-control">
                                            <option value="">请选择</option>
                                            <option <c:if test="${user.sex == 1}">selected</c:if> value="1">男</option>
                                            <option <c:if test="${user.sex == 0}">selected</c:if> value="0">女</option>
                                        </select>
                                    </div>
                                    <span class="help-block">请选择性别</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">状态</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <select name="status" class="form-control">
                                            <option value="">请选择</option>
                                            <option <c:if test="${user.status == 1}">selected</c:if> value="1">可用</option>
                                            <option <c:if test="${user.status == 0}">selected</c:if> value="0">禁用</option>
                                        </select>
                                    </div>
                                    <span class="help-block">请选择状态</span>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="panel-footer">
                    <input class="btn btn-default" type="button" value="清空" />
                    <input class="btn btn-primary pull-right" type="button" onclick="edit('${user.id}')" value="保存" />
                </div>
            </div>

        </form>

    </div>
</div>

<script type="text/javascript">
    function edit(id){
        var tableParam = getParams("user-edit");
        tableParam.id = id;
        $.ajax({
            url:"/user/edit",
            data:tableParam,
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.success){
                    layer.msg("修改成功");
                    layer.close(top.layer_edit);
                    reloadPage();
                }
            }
        })
    }
</script>
