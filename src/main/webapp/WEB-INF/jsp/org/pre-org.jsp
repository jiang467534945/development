<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <jsp:include page="../body/jtree.jsp" flush="true"/>
</head>
<body>

    <div>
        <ul id="tree" class="ztree"></ul>
    </div>

    <script type="text/javascript">
        var zTree;
        var setting = {
            showLine: true,
            checkable: true,
            data: {simpleData: {enable: true, idKey: "id", pIdKey: "pId", rootPId: 0}}
        };

        $(document).ready(function(){
            $.ajax({
                url:"/org/getOrgList",
                data:null,
                type:"POST",
                dataType:"JSON",
                success:function(data){
                    zTree = $.fn.zTree.init($("#tree"), setting, data);
                }
            });
        })

        function getMyNodes() {
            var nodesss = zTree.getSelectedNodes();
            return nodesss;
        }

    </script>

</body>
</html>
