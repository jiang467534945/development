<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                                <label class="col-md-3 control-label">选择组织</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-sort"></span></span>
                                        <input type="hidden" name="orgId" id="orgId" />
                                        <input type="text" class="form-control" id="orgName" name="orgName" required onclick="tolistOrg();">
                                    </div>
                                    <span class="help-block">请选择组织</span>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-md-3 control-label">角色描述</label>
                                <div class="col-md-9 col-xs-12">
                                    <textarea class="form-control" rows="5" name="description" id="description"></textarea>
                                    <span class="help-block">请输入需要添加角色的简介</span>
                                </div>
                            </div>
                        </div>

                            <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-3 control-label">角色名称</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" name="positionName" id="positionName">
                                    </div>
                                    <span class="help-block">请输入角色名称</span>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
                <div class="panel-footer">
                    <button class="btn btn-default" onclick="clearAll()">清空</button>
                    <input type="button"class="btn btn-primary pull-right" onclick="save()" value="保存">
                </div>
            </div>

        </form>

    </div>
</div>
<script>


    function tolistOrg() {
        LAYER.addIframeWithConfirm("选择组织","/org/preOrg","300px","500px",function(index, layero){
            var orgNode = document.getElementById("layui-layer-iframe" + index).contentWindow.getMyNodes()[0];
            console.log(orgNode.id);
            console.log(orgNode.name);
            $("#orgId").val(orgNode.id);
            $("#orgName").val(orgNode.name);
            layer.close(index);
        });
    }


    function save() {
        var orgId = $("#orgId").val();
        var positionName = $("#positionName").val();
        var description = document.getElementById("description").value;
        if (orgId == null || orgId == ''){
            layer.msg("请选择组织");
            return;
        }
        if (positionName == null || positionName == ''){
            layer.msg("请输入角色名称");
            return;
        }
        if (description == null || description == ''){
            layer.msg("请输入角色描述");
            return;
        }
        $.ajax({
            type: 'post',
            dataType: 'json',
            data: {"orgId":orgId,"positionName":positionName,"description":description},
            async: true,
            url: '/position/save',
            success: function (data) {
                console.log(data.success);
                if (data.success) {
                    layer.msg("保存成功！", {icon: 1, time: 1000}, function () {
                        layer.close(top.layer_positionAdd);
                        reloadPage();
                    });
                } else {
                    layer.msg(data.msg,{icon: 2,time:1000});
                }
            }
        });
    }

    /**
     * 清空表单
     */
    function clearAll() {
        var t = document.getElementsByTagName("input"); //清空所有的文本框
        for (var i = 0; i < t.length; i++) {
            if (t[i].type == 'text') {
                t[i].value = "";//清空
            }
        }
        var t = document.getElementsByTagName("textarea"); //清空所有的文本域
        for (var i = 0; i < t.length; i++) {
            t[i].value = "";//清空
        }
    }

</script>
