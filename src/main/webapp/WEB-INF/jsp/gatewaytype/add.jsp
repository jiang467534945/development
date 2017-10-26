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
                                <label class="col-md-3 control-label">类型名称</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" required name="name" />
                                    </div>
                                    <span class="help-block">请输入类型名称</span>
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

    function save() {
        var param = $("#addForm").serialize();
        if($("#addForm").valid()){
            $.ajax({
                type: 'post',
                dataType: 'json',
                data: param,
                async: true,
                url: '/gatewayType/save',
                success: function (data) {
                    if (data.success) {
                        layer.msg(data.msg, {icon: 1, time: 1000}, function () {
                            layer.close(top.layer_gatewayType);
                            reloadPage();
                        });
                    } else {
                        layer.msg(data.msg,{icon: 2,time:1000});
                    }
                }
            });
        }
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
    }

</script>
