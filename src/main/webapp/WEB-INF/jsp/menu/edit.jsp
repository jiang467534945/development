<%@ taglib prefix="maxlength" uri="http://www.springframework.org/tags/form" %>
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

        <form  class="form-horizontal" id="editForm" >
            <div class="panel panel-default">
                <div class="panel-body">

                    <div class="row">
                        <div class="col-md-6">

                            <div class="form-group">
                                <label class="col-md-3 control-label">上级菜单</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <upms:treeselect id="menu"
                                                         name="pId"
                                                         value="${upmsMenu.parent.id}"
                                                         labelName="parent.menuName"
                                                         labelValue="${upmsMenu.parent.menuName}"
                                                         title="菜单"
                                                         url="/menu/treeData"
                                                         extId="${upmsMenu.id}"/> <%--cssClass="required"--%>
                                    </div>
                                    <%--<span class="help-block">请输入菜单名称</span>--%>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">菜单名称</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                        <input type="hidden" class="form-control" name="id"  value="${upmsMenu.id}" >
                                        <input type="text" class="form-control required" name="menuName" data-tip="请输入菜单名称"  data-valid="isNonEmpty||onlyZh" data-error="菜单不能为空||菜单只能为中文"  value="${upmsMenu.menuName}" >
                                    </div>
                                    <%--<span class="help-block">请输入菜单名称</span>--%>
                                </div>
                            </div>

                            <div class="form-group">
                            <label class="col-md-3 control-label">菜单图标</label>
                            <div class="col-md-9">
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="fa fa-circle"></span></span>
                                    <input type="text" class="form-control required" name="icon"  data-tip="请输入菜单图标"  data-valid="isNonEmpty"   data-error="菜单图标不能为空" value="${upmsMenu.icon}" >
                                </div>
                                <%--<span class="help-block">请输入需要添加菜单的图标</span>--%>
                            </div>
                        </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">菜单简介</label>
                                <div class="col-md-9 col-xs-12">
                                    <textarea class="form-control"  rows="5" data-tip="请输入菜单的简介" name="menuContext"   id="context" >${upmsMenu.menuContext}</textarea>
                                    <%--<span class="help-block">请输入需要添加菜单的简介</span>--%>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-6">


                            <div class="form-group">
                                <label class="col-md-3 control-label">菜单路径</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-retweet"></span></span>
                                        <input type="text" class="form-control "   name="url" value="${upmsMenu.url}"  >
                                    </div>
                                    <%--<span class="help-block">请输入菜单路径</span>--%>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">菜单排序</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="fa fa-sort"></span></span>
                                        <input type="text" class="form-control required" data-tip="请输入菜单排序" data-valid="isNonEmpty||onlyInt"  data-error="菜单排序不允许为空||菜单排序只能是整数" name="sort" value="${upmsMenu.sort}" >
                                    </div>
                                    <%--<span class="help-block">请输入菜单排序</span>--%>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>
                <div class="panel-footer">
                    <button class="btn btn-default">清除</button>
                    <input type="button"class="btn btn-primary" onclick="edit()" value="修改">
                </div>
            </div>

        </form>

    </div>
</div>
<script>





    function edit() {
        var param = serializeForm("editForm");
        var menuContext=$("#context").val();
        event.preventDefault();
       var bool=$(this).validate('submitValidate');
       if($(this).validate('submitValidate')) {
           $.ajax({
            type: 'post',
            dataType: 'json',
            data: param +"&menuContext=" + menuContext,
            async: true,
            url: '/menu/update',
            success: function (data) {
                console.info(data);
                if (data.success){
                    layer.msg("修改成功！",{icon:1,time:1000},function () {
                       layer.close(top.layer_edit);
                       reloadPage();
                    });
                }else{
                    layer.msg(data.msg,{icon: 2,time:1000});
                }
            },
            error: function () {
                layer.msg("修改未成功",{icon: 2,time:1000});
            }
        })
       }
    }
    /*        pageLoadingFrame("show");
            document.getElementById("addForm").submit();*/



</script>
