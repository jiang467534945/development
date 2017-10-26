<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>


<div class="row">
    <div class="col-md-12">
        <form class="form-horizontal"  id="editForm">
<input type="hidden" class="form-control" required name="id" value="${data.id}" />
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-3 control-label">网关编号</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-location-arrow"></span></span>
                                        <input type="text" class="form-control" required name="number" value="${data.number}" />
                                    </div>
                                    <span class="help-block">请输入网关编号</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-3 control-label">网关类型</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-hourglass-end"></span></span>
                                        <select class="form-control" required name="typeId">
                                            <option value="">请选择网关类型</option>
                                            <c:forEach var="item" items="${gatewayTypes}">
                                            <option <c:if test="${data.typeId == item.id}">selected</c:if> value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <span class="help-block">请选择网关类型</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                    <button class="btn btn-default">关闭</button>
                        <input class="btn btn-primary pull-right" type="button" onclick="update();" value="修改" />
                </div>
            </div>
        </form>
    </div>
</div>
<script src="/static/js/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript">

   /* 获取到表单中的数据*/
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
     * 修改
     */
    function update() {
        var param= getParams("editForm");
        console.info(param);
        if($("#editForm").valid()){
            $.ajax({
                type:"post",
                url : "/gateway/update",
                data: param,
                dataType: "json",
                success : function(data){
                    if (data.success) {
                        layer.msg(data.msg,{icon: 1,time:1000}, function(){
                            layer.close(top.layer_edit);
                            reloadPage();
                        });
                    } else {
                        layer.msg(data.msg,{icon: 2,time:1000})
                    }
                }
            });
        }
     }
</script>
