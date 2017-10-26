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
        <form class="form-horizontal"  id="familyTypeEditForm">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-3 control-label">类型名称</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="hidden" class="form-control" required name="id" value="${familyType.id}" />
                                        <input type="text" class="form-control" required name="name" value="${familyType.name}" />
                                    </div>
                                    <span class="help-block">请输入类型名称</span>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">

                            <div class="form-group">
                                <label class="col-md-3 control-label">识别码</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" required name="number" value="${familyType.number}" />
                                    </div>
                                    <span class="help-block">请输入识别码</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                        <input class="btn btn-default" type="button" value="清空" />
                        <input class="btn btn-primary pull-right" type="button"  onclick="update();" value="保存" />
                </div>
            </div>
        </form>

    </div>
</div>
<script src="/static/js/plugins/jquery-validation/jquery.validate.js"></script>
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
     }
    /**
     * 保存
     */
    function  update() {
        var param = $("#familyTypeEditForm").serialize();
        if($("#familyTypeEditForm").valid()){
            $.ajax({
                type:"post",
                url : "/familyType/update",
                data:param,
                dataType: "json",
                success : function(data){
                    if (data.success) {
                        layer.msg('修改成功！',{icon: 1,time:1000}, function(){
                            layer.close(top.layer_typeEdit);
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
