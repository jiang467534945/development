<%--
  Created by IntelliJ IDEA.
  User: Easy
  Date: 2017-09-27
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>


<div class="panel">
    <form class="form-horizontal" id="genForm">

    <div class="row">
        <div class="col-md-12">

                <div class="panel panel-default">
                    <div class="panel-body">

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">表名</label>
                                    <div class="col-md-9">
                                        <div class="input-group">
                                            <span class="input-group-addon"><span class="fa fa-eye"></span></span>
                                            <input type="text" class="form-control" name="name" value="${genTable.name}">
                                        </div>
                                        <span class="help-block">表名:</span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-3 control-label">说明:</label>
                                    <div class="col-md-9">
                                        <div class="input-group">
                                            <span class="input-group-addon"><span class="fa fa-circle"></span></span>
                                            <input type="text" class="form-control" name="comments" value="${genTable.comments}">
                                        </div>
                                        <span class="help-block">说明:</span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-3 control-label">类名</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="className" value="${genTable.className}">
                                        <span class="help-block">类名：</span>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>


        </div>
    </div>
    <div class="panel-body">
        <div id="demo-dt-addrow_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
            <div class="newtoolbar">
                <div id="demo-custom-toolbar2" class="table-toolbar-left">
                    <%--
                                <button id="demo-dt-addrow-btn" class="btn btn-primary"><i class="demo-pli-plus"></i> Add row</button>
                    --%>
                </div>
            </div>
            <table id="demo-dt-addrow" class="table table-striped table-bordered dataTable no-footer dtr-inline"
                   cellspacing="0" width="100%" role="grid" aria-describedby="demo-dt-addrow_info" style="width: 100%;">
                <thead>
                <tr role="row">
                    <th title="数据库字段名">列名</th><th title="默认读取数据库字段备注">说明</th><th title="数据库中设置的字段类型及长度">物理类型</th><th title="实体对象的属性字段类型">Java类型</th>
                    <th title="实体对象的属性字段（对象名.属性名|属性名2|属性名3，例如：用户user.id|name|loginName，属性名2和属性名3为Join时关联查询的字段）">Java属性名称 <i class="icon-question-sign"></i></th>
                    <th title="是否是数据库主键">主键</th><th title="字段是否可为空值，不可为空字段自动进行空值验证">可空</th><th title="选中后该字段被加入到insert语句里">插入</th>
                    <th title="选中后该字段被加入到update语句里">编辑</th><th title="选中后该字段被加入到查询列表里">列表</th>
                    <th title="选中后该字段被加入到查询条件里">查询</th><th title="该字段为查询字段时的查询匹配放松">查询匹配方式</th>
                    <th title="字段在表单中显示的类型">显示表单类型</th><th title="显示表单类型设置为“下拉框、复选框、点选框”时，需设置字典的类型">字典类型</th><th>排序</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${genTable.columnList}" var="column" varStatus="vs">
                    <tr calss="row">
                        <td nowrap>
                            <input type="hidden" name="columnList[${vs.index}].id" value="${column.id}"/>
                            <input type="hidden" name="columnList[${vs.index}].genTable.id" value="${column.genTable.id}"/>
                            <input type="hidden" name="columnList[${vs.index}].name" value="${column.name}"/>${column.name}
                        </td>
                        <td>
                            <input type="text" name="columnList[${vs.index}].comments" value="${column.comments}" maxlength="200" class="required" style="width:100px;"/>
                        </td>
                        <td nowrap>
                            <input type="hidden" name="columnList[${vs.index}].jdbcType" value="${column.jdbcType}"/>${column.jdbcType}
                        </td>
                        <td>
                            <select name="columnList[${vs.index}].javaType" class="required input-mini" style="width:85px;*width:75px">
                                <c:forEach items="${config.javaTypeList}" var="dict">
                                    <option value="${dict.value}" ${dict.value==column.javaType?'selected':''} title="${dict.description}">${dict.label}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="text" name="columnList[${vs.index}].javaField" value="${column.javaField}" maxlength="200" class="required input-small"/>
                        </td>
                        <td>
                            <input type="checkbox" name="columnList[${vs.index}].isPk" value="1" ${column.isPk eq '1' ? 'checked' : ''}/>
                        </td>
                        <td>
                            <input type="checkbox" name="columnList[${vs.index}].isNull" value="1" ${column.isNull eq '1' ? 'checked' : ''}/>
                        </td>
                        <td>
                            <input type="checkbox" name="columnList[${vs.index}].isInsert" value="1" ${column.isInsert eq '1' ? 'checked' : ''}/>
                        </td>
                        <td>
                            <input type="checkbox" name="columnList[${vs.index}].isEdit" value="1" ${column.isEdit eq '1' ? 'checked' : ''}/>
                        </td>
                        <td>
                            <input type="checkbox" name="columnList[${vs.index}].isList" value="1" ${column.isList eq '1' ? 'checked' : ''}/>
                        </td>
                        <td>
                            <input type="checkbox" name="columnList[${vs.index}].isQuery" value="1" ${column.isQuery eq '1' ? 'checked' : ''}/>
                        </td>
                        <td>
                            <select name="columnList[${vs.index}].queryType" class="required input-mini">
                                <c:forEach items="${config.queryTypeList}" var="dict">
                                    <option value="${fns:escapeHtml(dict.value)}" ${fns:escapeHtml(dict.value)==column.queryType?'selected':''} title="${dict.description}">${fns:escapeHtml(dict.label)}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="columnList[${vs.index}].showType" class="required" style="width:100px;">
                                <c:forEach items="${config.showTypeList}" var="dict">
                                    <option value="${dict.value}" ${dict.value==column.showType?'selected':''} title="${dict.description}">${dict.label}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="text" name="columnList[${vs.index}].dictType" value="${column.dictType}" maxlength="200" class="input-mini"/>
                        </td>
                        <td>
                            <input type="text" name="columnList[${vs.index}].sort" value="${column.sort}" maxlength="200" class="required input-min digits"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </form>

</div>

<script>


    /*        pageLoadingFrame("show");
            document.getElementById("addForm").submit();*/


</script>
