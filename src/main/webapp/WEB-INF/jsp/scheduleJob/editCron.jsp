<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>

<div class="row">
    <div class="col-md-12">

        <form class="form-horizontal" id="editForm">
            <div class="panel panel-default">
                <div class="panel-body">
<input type="hidden" value="${jobId}" name="jobId" id="jobId">
                    <div class="row">
                            <div class="form-group">
                                <label class="col-md-3 control-label">CRON表达式</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" required name="cronExpression"
                                               id="cronExpression" value="${cron}">
                                    </div>
                                    <span class="help-block">请输入CRON表达式</span>
                                </div>
                            </div>
                    </div>

                </div>
                <div class="panel-footer">
                    <button class="btn btn-default">取消</button>
                    <input type="button" class="btn btn-primary pull-right" onclick="update()" value="保存">
                </div>
            </div>

        </form>

    </div>
</div>
<script>

    /**
     * 修改cron表达式
     */
    function update() {
        var jobId = $("#jobId").val();
        var cronExpression = $("#cronExpression").val();
        console.log(jobId + "--------------" + cronExpression);
        $.ajax({
            type: "post",
            url: "/scheduleJob/updateCron",
            data: {"jobId": jobId, "cronExpression": cronExpression},
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    layer.msg(data.msg, {icon: 1, time: 1000}, function () {
                        layer.close(top.layer_schedule_job_edit);
                        reloadPage();
                    });
                } else {
                    layer.msg(data.msg, {icon: 2, time: 1000})
                }
            }
        });
    }


</script>
