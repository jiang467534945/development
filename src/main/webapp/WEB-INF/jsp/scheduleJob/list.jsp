<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fns" uri="http://java.sun.com/jsp/jstl/functionss" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>角色管理</title>
    <jsp:include page="../body/link-page.jsp" flush="true"/>

</head>

<body>
<div class="panel">
    <div class="panel-heading">
        <button id="demo-dt-addrow-btn" class="btn btn-info" onclick="addLay('layer_schedule_job','添加定时任务','/scheduleJob/add','830px','430px')">
            <i class="fa fa-plus"></i> 添加
        </button>
        <button class="btn btn-primary" onclick="deleteAllCheck();"><i class="fa fa-trash-o"></i> 删除</button>
    </div>
    <form id="form" action="/scheduleJob/list" method="post">
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
                    <th>任务名称</th>
                    <th>任务组</th>
                    <th>状态</th>
                    <th>cron表达式</th>
                    <th>类路径</th>
                    <th>方法名</th>
                    <%--<th>springName</th>--%>
                    <th>描述</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${pageList.results}" varStatus="status">
                    <tr>
                        <td><input type="checkbox" value="${item.jobId}"/></td>
                        <td>${item.jobName}</td>
                        <td>${item.jobGroup}</td>
                        <td>
                            <c:if test="${item.jobStatus == '0'}">停止</c:if>
                            <c:if test="${item.jobStatus == '1'}">运行</c:if>
                        </td>
                        <td>${item.cronExpression}</td>
                        <td>${item.beanClass}</td>
                        <td>${item.methodName}</td>
                        <%--<td>${item.springId}</td>--%>
                        <td>${item.description}</td>
                        <td>${fns:dateToStringTime(item.createTime)}</td>
                        <td>
                            <c:if test="${item.updateTime == null}">${item.updateTime}</c:if>
                            <c:if test="${item.updateTime != null}">${fns:dateToStringTime(item.updateTime)}</c:if>
                        </td>
                        <td>
                            <c:if test="${item.jobStatus == '0'}">
                                <input type="button" class="btn btn-success" onclick="changeJobStatus('${item.jobId}','1')" value="开启"/>
                            </c:if>
                            <c:if test="${item.jobStatus == '1'}">
                                <input type="button" class="btn btn-warning" onclick="changeJobStatus('${item.jobId}','0')" value="停止"/>
                            </c:if>
                                <input type="button" class="btn btn-info" onclick="editLay('layer_schedule_job_edit','修改cron表达式','/scheduleJob/editCron','${item.cronExpression}','${item.jobId}')" value="修改cron表达式"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul class="pagination"></ul>
        </div>
</form>
</div>
<jsp:include page="../body/javascript-page.jsp" flush="true"/>

<script type="text/javascript">

    /**
     * 添加页面
     * @param title
     * @param url
     */
    function addLay(id,title,url,width,height) {
        $.ajax({
            type:'get',
            dataType:'html',
            url:url,
            success:function (data) {
                top[id] = layer.open({
                    type: 1,
                    title: title,
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area : [width,height],
                    content: data
                });
            },
            error:function (data) {
                alert("没有找到此页面");
            }
        });
    }

    /**
     * 修改任务执行状态
     * 2017-10-25 闫增宝
     */
    function changeJobStatus(jobId,jobStatus) {
        console.log("任务ID："+jobId+"任务状态："+jobStatus);
        $.ajax({
            url:"/scheduleJob/changeJobStatus",
            data:{"jobId":jobId,"jobStatus":jobStatus},
            type:"post",
            dataType:"json",
            success:function (data) {
                if(data.success){
                    layer.msg(data.msg,{icon:1,time:1000});
                    reloadPage();
                }else{
                    layer.msg(data.msg,{icon: 2,time:1000});
                }
            },
            error:function () {
                console.log("111");
            }
        });
    }


    /**
     * 修改cron表达式
     * @param title
     * @param url
     */
    function editLay(id,title,url,cron,jobId) {
        $.ajax({
            type:'get',
            dataType:'html',
            url:url,
            data:{"cron":cron,"jobId":jobId},
            success:function (data) {
                top[id] = layer.open({
                    type: 1,
                    title: title,
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area : ["420px","220px"],
                    content: data
                });
            },
            error:function (data) {
                alert("没有找到此页面");
            }
        });
    }

    /**
     * 删除操作
     */
    function deleteAllCheck() {
        var ids = getAllCheck("form");
        if(ids.length == 0){
            layer.msg("至少选择一条数据");
        } else {
            layer.confirm("确定要删除这" + ids.length + "条数据？",{title:"提示",btn:["确定","取消"]}, function(){
                $.ajax({
                    url:"/scheduleJob/deletes",
                    data:{"ids":ids.join(",")},
                    type:"post",
                    dataType:"json",
                    success:function(data){
                        if(data.success){
                            layer.msg(data.msg,{icon:1,time:1000});
                            reloadPage();
                        } else {
                            layer.msg(data.msg,{icon: 2,time:1000});
                        }
                    }
                });
            });
        }
    }

</script>
</body>
</html>
