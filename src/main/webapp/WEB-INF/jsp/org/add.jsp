<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>

<div class="row">
    <div class="col-md-12">
        <form class="form-horizontal"  id="orgForm">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-6">

                            <div class="form-group">
                                <label class="col-md-3 control-label">上级组织</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="hidden" name="pId" id="pId" value="0" /><%-- 不选择默认最高级 --%>
                                        <input type="text" class="form-control" id="pName" required onclick="tolistOrg();">
                                    </div>
                                    <span class="help-block">请输入选择组织</span>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-md-3 control-label">组织描述</label>
                                <div class="col-md-9 col-xs-12">
                                    <textarea class="form-control" rows="5" name="description" style="margin: 0px -379.742px 0px 0px; width: 627px; height: 101px;"></textarea>
                                <span class="help-block">请输入描述信息</span>
                            </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-3 control-label">组织名称</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="text" class="form-control" name="orgName">
                                    </div>
                                    <span class="help-block">请输入组织名称</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                        <input class="btn btn-default" type="button" value="清空" />
                        <input class="btn btn-primary pull-right" type="button" onclick="save();" value="保存" />
                </div>
            </div>
        </form>

    </div>
</div>

<script type="text/javascript">

    function tolistOrg() {
        LAYER.addIframeWithConfirm("选择组织","/org/preOrg","300px","500px",function(index, layero){
            var orgNode = document.getElementById("layui-layer-iframe" + index).contentWindow.getMyNodes()[0];
            $("#pId").val(orgNode.id);
            $("#pName").val(orgNode.name);
            layer.close(index);
        });
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
     * 保存
     */
    function  save() {
        var param = $("#orgForm").serialize();
        $.ajax({
            type:"POST",
            url : "/org/save",
            data:param,
            dataType: "json",
            success : function(data){
                if (data.success) {
                    layer.msg('保存成功！',{icon: 1,time:1000}, function(){
                        layer.close(top.layer_orgAdd);
                        reloadPage();
                    });
                } else {
                    layer.msg(data.msg,{icon: 2,time:1000})
                }
            }
        });

     }
</script>
