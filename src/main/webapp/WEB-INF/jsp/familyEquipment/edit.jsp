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
        <form class="form-horizontal"  id="familyEquipmenEditForm">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-3 control-label">设备编号</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="hidden" class="form-control" required name="id" value="${familyEquipment.id}" />
                                        <input type="text" class="form-control" required name="number" value="${familyEquipment.number}" />
                                    </div>
                                    <span class="help-block">请输入设备编号</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">设备类型</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <select class="form-control" name="typeId" >
                                            <c:forEach var="item" items="${familyTypes}">
                                                <option value="${item.id}" <c:if test="${item.id == familyEquipment.typeId}">selected</c:if>>${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <span class="help-block">请选择设备类型</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">备注信息</label>
                                 <div class="col-md-9 col-xs-12">
                                    <textarea  class="form-control"  name="intro"  rows="6">${familyEquipment.number}</textarea>
                                    <span class="help-block">请输入备注</span>
                                </div>
                            </div>


                        </div>

                        <div class="col-md-6">

                            <div class="form-group">
                                <label class="col-md-3 control-label">设备名称</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" required name="name" value="${familyEquipment.name}" />
                                    </div>
                                    <span class="help-block">请输入设备名称</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">网关编号</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <select class="form-control" name="gatewayId" >
                                            <c:forEach items="${gateways}" var="item">
                                                <option value="${item.id}" <c:if test="${item.id == familyEquipment.gatewayId}"></c:if>>${item.number}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <span class="help-block">请选择设备类型</span>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                        <input class="btn btn-default" type="button" onclick="clearAll()" value="清空" />
                        <input class="btn btn-primary pull-right" type="button"  onclick="update();" value="保存" />
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
     * 清空所有的内容
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
    /**
     * 修改
     */
    function  update() {
        var params= getParams("familyEquipmenEditForm");
        if($("#familyEquipmenEditForm").valid()){
            $.ajax({
                type:"post",
                url : "/familyEquipment/update",
                data:params,
                dataType: "json",
                success : function(data){
                    if (data.success) {
                        layer.msg('修改成功！',{icon: 1,time:1000}, function(){
                            layer.close(top.layer_equipmentEdit);
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
