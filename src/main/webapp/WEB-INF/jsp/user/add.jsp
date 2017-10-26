<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>

<div class="row">
    <div class="col-md-12">

        <form action="/user/save" class="form-horizontal"  id="userAddForm">
            <div class="panel panel-default">
                <div class="panel-body">

                    <div class="row">
                        <div class="col-md-6">

                            <div class="form-group">
                                <label class="col-md-3 control-label">用户名</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" name="userName" required class="form-control">
                                    </div>
                                    <span class="help-block">请输入用户名</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">姓名</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" name="name" required class="form-control">
                                    </div>
                                    <span class="help-block">请输入姓名</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">身份证号码</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input name="idNumber" type="text" required class="form-control">
                                    </div>
                                    <span class="help-block">请输入身份证号码</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">存储空间</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input name="totalSpace" required type="text" class="form-control">
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
                                        <input name="password" type="password"  required class="form-control">
                                    </div>
                                    <span class="help-block">请输入密码</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">性别</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <select name="sex" required class="form-control">
                                            <option value="">请选择</option>
                                            <option value="1">男</option>
                                            <option value="0">女</option>
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
                                        <select name="status" class="form-control" required>
                                            <option value="">请选择</option>
                                            <option value="1" selected>可用</option>
                                            <option value="0">禁用</option>
                                        </select>
                                    </div>
                                    <span class="help-block">请选择状态</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                    <input class="btn btn-default" type="button" value="清空" onclick="clearAll()" />
                    <input class="btn btn-primary pull-right" type="button" onclick="save();" value="保存" />
                </div>
            </div>

        </form>

    </div>
</div>

<script type="text/javascript">

    /**
     * 清空所有的内容
     */
    function clearAll() {
        var t = document.getElementsByTagName("input"); //清空所有的文本框
        for (var i = 0; i < t.length; i++) {
            if (t[i].type == 'text') {
                t[i].value = "";//清空
            }
        }
        var  selects=document.getElementsByTagName("select");
        console.info(selects.length);
        for (var i = 0; i < selects.length; i++) {
                t[i].value="";//清空
        }
    }

    function save(){
        var param = $("#userAddForm").serialize();
        if($("#userAddForm").valid()){
            $.ajax({
                type:"post",
                url:"/user/save",
                data:param,
                dataType: "json",
                success:function(data){
                        if(data.success){
                            layer.msg("保存成功！！！",{icon:1,timeout:1000},function () {
                                layer.close(top.layer_userAdd);
                                reloadPage();
                            })
                        }else{
                            layer.msg(data.msg,{icon:2,timeout:1000});
                        }
                },
            });
        }

    }
</script>
