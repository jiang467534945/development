<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>


<div class="row">
    <div class="col-md-12">
        <form class="form-horizontal"  id="addForm">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-3 control-label">网关编号</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-location-arrow"></span></span>
                                        <input type="text" class="form-control" required name="number" />
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
                                                <option value="${item.id}">${item.name}</option>
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
                        <input class="btn btn-default" type="button" onclick="clearAll()" value="清空" />
                        <input class="btn btn-primary pull-right" type="button"  onclick="save();" value="保存" />
                </div>
            </div>
        </form>
    </div>
</div>
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
     * 清空所有的内容
     */
     function clearAll() {
         var t = document.getElementsByTagName("input"); //清空所有的文本框
         for (var i = 0; i < t.length; i++) {
             if (t[i].type == 'text') {
                 t[i].value = "";//清空
             }
         }
     }

    /**
     * 保存
     */
    function  save() {
        var param= getParams("addForm");
        console.info(param);
        if($("#addForm").valid()){
            $.ajax({
                type:"post",
                url : "/gateway/save",
                data: param,
                dataType: "json",
                success : function(data){
                    if (data.success) {
                        layer.msg(data.msg,{icon: 1,time:1000}, function(){
                            layer.close(top.layer_gateway);
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
