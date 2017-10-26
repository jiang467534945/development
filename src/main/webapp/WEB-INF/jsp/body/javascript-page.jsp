<%--
  Created by IntelliJ IDEA.
  User: Easy
  Date: 2017-09-25
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!-- 音频引入 START PRELOADS -->
<audio id="audio-alert" src="${basePath}/static/audio/alert.mp3" preload="auto"></audio>
<audio id="audio-fail" src="${basePath}/static/audio/fail.mp3" preload="auto"></audio>
<!-- END PRELOADS -->
<!-- Jquery START PLUGINS -->
<script type="text/javascript" src="${basePath}/static/js/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/BootstrapMenu.min.js"></script>
<%--
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
--%>
<!-- END PLUGINS -->
<!-- START THIS PAGE PLUGINS-->
<script type='text/javascript' src='${basePath}/static/js/plugins/icheck/icheck.min.js'></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/scrolltotop/scrolltopcontrol.js"></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/morris/raphael-min.js"></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/morris/morris.min.js"></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/rickshaw/d3.v3.js"></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/rickshaw/rickshaw.min.js"></script>
<script type='text/javascript' src='${basePath}/static/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js'></script>
<script type='text/javascript' src='${basePath}/static/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js'></script>
<script type='text/javascript' src='${basePath}/static/js/plugins/bootstrap/bootstrap-datepicker.js'></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/owl/owl.carousel.min.js"></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/moment.min.js"></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/daterangepicker/daterangepicker.js"></script>
<%--
<script type="text/javascript" src="${basePath}/static/js/plugins/datatables/jquery.dataTables.min.js"></script>
--%>
<script type="text/javascript" src="${basePath}/static/js/plugins/bootstrap/bootstrap-file-input.js"></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/bootstrap/bootstrap-select.js"></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/tagsinput/jquery.tagsinput.min.js"></script>
<!-- END THIS PAGE PLUGINS-->
<script type="text/javascript" src="${basePath}/static/js/device.min.js"></script>
<script type="text/javascript" src="${basePath}/static/js/plugins/layer/layer.js"></script>
<!-- START TEMPLATE -->
<%--
<script type="text/javascript" src="${basePath}/static/js/settings.js"></script>
--%>

<script type="text/javascript" src="${basePath}/static/js/plugins.js"></script>
<script type="text/javascript" src="${basePath}/static/js/actions.js"></script>
<script type="text/javascript" src="${basePath}/static/js/demo_dashboard.js"></script>
<!-- END TEMPLATE -->
<script type="text/javascript" src="${basePath}/static/js/dialog-layer.js"></script>
<script type="text/javascript" src="${basePath}/static/js/tap/devel.js"></script>
<script type="text/javascript" src="${basePath}/static/js/page-plugin.js"></script>
<script type="text/javascript" src="/static/validate/jquery-validate.js"></script>
