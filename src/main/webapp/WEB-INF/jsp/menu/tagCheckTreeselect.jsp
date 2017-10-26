<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <title>数据选择</title>
    <jsp:include page="../body/jtree.jsp" flush="true"/>

    <SCRIPT type="text/javascript">
        var zTree;
        var setting = {
            view: {
                selectedMulti: false
            },
            check: {enable: true,chkStyle: "checkbox",radioType: "level"},
            data: {simpleData: {enable: true, idKey: "id", pIdKey: "pId", rootPId: 0}},
            edit: {
                enable: false
            }
        };

        function getMyChecked() {
            var data = {};
            var ids = [];
            var zNodes = zTree.getCheckedNodes(true);
            for(z in zNodes){
                ids.push(zNodes[z].id);
            }
            data.orgId = "${id}";
            data.menuId = ids.join(",");
            return data;
        }

        var zNodes;
        function  zNodes1 () {
            $.ajax({
                type:'get',
                dataType:'json',
                url:'/tag/treeData',
                data:{id:${id}},
                async:false,
                success:function (data) {
                    zNodes= data;
                },
                error : function () {
                    alert("获取失败")
                }
            })
        };

        $(document).ready(function(){
            zNodes1();
            zTree = $.fn.zTree.init($("#tree"), setting, zNodes);
        });

    </SCRIPT>
</head>
<body>
<ul id="tree" class="ztree"></ul>
</body>
<script type="text/javascript" src="${ctx}/static/js/plugins/layer/layer.js"></script>

</html>