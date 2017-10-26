<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- META SECTION -->
    <title>慧若科技</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link rel="icon" href="/static/img/hr-logo.png" type="image/x-icon"/>
    <!-- END META SECTION -->

    <!-- CSS INCLUDE -->
    <jsp:include page="body/link.jsp"/>

    <!-- EOF CSS INCLUDE -->
</head>
<body>
<!-- START PAGE CONTAINER -->
<div class="page-container">

    <!-- 左侧导航菜单  START X-NAVIGATION VERTICAL -->

    <jsp:include page="body/sidebar.jsp"/>

    <!-- END PAGE SIDEBAR -->

    <!-- PAGE CONTENT -->
    <div class="page-content" id="content">

        <!-- 个人信息  START X-NAVIGATION VERTICAL -->
        <jsp:include page="body/header.jsp"/>
        <!-- 页签  START X-NAVIGATION VERTICAL -->

        <jsp:include page="body/page-bar.jsp"/>

        <!-- PAGE CONTENT WRAPPER -->

        <div id="iframe_home" class="page-content-wrap active">

            <iframe src="/home" width="100%" class="tab_iframe" frameborder="0" scrolling="auto" onload="changeFrameHeight(this)"></iframe>
            <!-- START WIDGETS -->

            <!-- END DASHBOARD CHART -->


        </div>

        <!-- END PAGE CONTENT WRAPPER -->
    </div>
    <div style="width:100%;float: right;color: black;position: fixed;right: 0px;bottom: 0px;height: 15px;background-color: #0F192A" >本系统由锐途开发小组开发，未经授权，禁止商用!!!</div>

    <!-- END PAGE CONTENT -->
</div>
<!-- END PAGE CONTAINER -->

<!-- MESSAGE BOX-->
<div class="message-box animated fadeIn" data-sound="alert" id="mb-signout">
    <div class="mb-container">
        <div class="mb-middle">
            <div class="mb-title"><span class="fa fa-sign-out"></span> Log <strong>Out</strong> ?</div>
            <div class="mb-content">
                <p>Are you sure you want to log out?</p>
                <p>Press No if youwant to continue work. Press Yes to logout current user.</p>
            </div>
            <div class="mb-footer">
                <div class="pull-right">
                    <a href="pages-login.html" class="btn btn-success btn-lg">Yes</a>
                    <button class="btn btn-default btn-lg mb-control-close">No</button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="body/javascript.jsp"/>
</body>
</html>






