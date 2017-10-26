<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>

<div class="row">
    <div class="col-md-12">

        <form class="form-horizontal" id="addForm">
            <div class="panel panel-default">
                <div class="panel-body">

                    <div class="row">
                        <div class="col-md-6">

                            <div class="form-group">
                                <label class="col-md-3 control-label">任务名称</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" required name="jobName" id="jobName">
                                    </div>
                                    <span class="help-block">请输入任务名称</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">CRON表达式</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" required name="cronExpression"
                                               id="cronExpression">
                                    </div>
                                    <span class="help-block">请输入CRON表达式</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">方法名称</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" required name="methodName"
                                               id="methodName">
                                    </div>
                                    <span class="help-block">请输入方法名称</span>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="form-group">
                                <label class="col-md-3 control-label">任务分组</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" required name="jobGroup" id="jobGroup">
                                    </div>
                                    <span class="help-block">请输入任务分组</span>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-md-3 control-label">类名称</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" required name="beanClass" id="beanClass">
                                    </div>
                                    <span class="help-block">请输入包名+类名</span>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-md-3 control-label">请输入任务描述</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" required name="description"
                                               id="description">
                                    </div>
                                    <span class="help-block">请输入任务描述</span>
                                </div>
                            </div>
<%--
                            <div class="form-group">
                                <label class="col-md-3 control-label">springName</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" name="springId" id="springId">
                                    </div>
                                    <span class="help-block">请输入springName</span>
                                </div>
                            </div>
--%>

                        </div>
                    </div>

                </div>
                <div class="panel-footer">
                    <button class="btn btn-default" onclick="clearAll()">清空</button>
                    <input type="button" class="btn btn-primary pull-right" onclick="save()" value="保存">
                </div>
            </div>

        </form>

    </div>
</div>
<script>

    //获取到表单中的数据
    function getParams(_formId) {
        var nodes = $("#" + _formId).find("input[type='text'],input[type='password']" +
            ",input[type='hidden'],input[type='radio']:checked," +
            "input[type='checkbox']:checked,textarea,select");
        var params = {};

        for (var i = 0; i < nodes.length; i++) {
            //当前表单
            var e = $(nodes[i]);
            //表单name
            var name = e.attr("name");
            //如果有name
            if(name !== undefined){
                //如果没有同名数据
                if (params[name] === undefined) {
                    params[name] = e.val();
                } else {
                    //同名表单用逗号拼接
                    params[name] += "," + e.val();
                }
            }
            //没有name属性的表单将会被忽略
        }
        return params;
    }

    /**
     * 保存
     */
    function  save() {
        var param= getParams("addForm");
        console.info(param);
            $.ajax({
                type:"post",
                url : "/scheduleJob/save",
                data: param,
                dataType: "json",
                success : function(data){
                    if (data.success) {
                        layer.msg(data.msg,{icon: 1,time:1000}, function(){
                            layer.close(top.layer_schedule_job);
                            reloadPage();
                        });
                    } else {
                        layer.msg(data.msg,{icon: 2,time:1000})
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
