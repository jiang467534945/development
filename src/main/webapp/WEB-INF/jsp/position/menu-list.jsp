<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <title></title>
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
            data.positionId = "${id}";
            data.menuIds = ids.join(",");
            return data;
        }

        $(document).ready(function(){
            $.ajax({
                type:'get',
                dataType:'json',
                url:'/position/listMenu',
                data:{id:'${id}'},
                async:false,
                success:function (data) {
                    zTree = $.fn.zTree.init($("#tree"), setting, data);
                },
                error : function () {
                    alert("获取失败")
                }
            })
        });

    </SCRIPT>
</head>
<body>
<ul id="tree" class="ztree"></ul>
</body>

</html>