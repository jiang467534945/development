<%--
  Created by IntelliJ IDEA.
  User: Easy
  Date: 2017-09-25
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!-- START PAGE SIDEBAR -->
<div class="page-sidebar">
    <!-- START X-NAVIGATION -->
    <ul class="x-navigation">
        <li class="xn-logo">
            <a href="index.html">ATLANT</a>
            <a href="#" class="x-navigation-control"></a>
        </li>
        <li class="xn-profile">
            <a href="http://www.17sucai.com" class="profile-mini">
                <img src="${basePath}/static/assets/images/users/avatar.jpg" alt="John Doe"/>
            </a>
            <div class="profile">
                <div class="profile-image">
                    <img src="${basePath}/static/assets/images/users/avatar.jpg" alt="John Doe"/>
                </div>
                <div class="profile-data">
                    <div class="profile-data-name">John Doe</div>
                    <div class="profile-data-title">Web Developer/Designer</div>
                </div>
                <div class="profile-controls">
                    <a href="pages-profile.html" class="profile-control-left"><span class="fa fa-info"></span></a>
                    <a href="pages-messages.html" class="profile-control-right"><span class="fa fa-envelope"></span></a>
                </div>
            </div>
        </li>
        <li class="xn-title">Navigation</li>

        <c:forEach var="upmsMenuList" items="${menuList}" varStatus="status">
            <li class="xn-openable">
                <a href="#"><span class="${upmsMenuList.icon}"></span> <span
                        class="xn-text">${upmsMenuList.menuName} <%--<div class="lianjiexian"></div><span style="color: yellow; ">${upmsMenuList.menuContext}</span>--%></span></a>
                <ul>
                    <c:forEach var="levelList" items="${upmsMenuList.levelList}" varStatus="levelstatus">
                        <c:if test="${levelstatus.index == 0}">
                            <li class="active"><a href="javascript:Tab.addTab('${levelList.menuName}', '${levelList.url}');"<%--href="${levelList.url}"--%>><span class="${levelList.icon}"></span> ${levelList.menuName}<div class="lianjiexian"></div><span style="color: yellow; ">${levelList.menuContext}</span></a></li>
                        </c:if>
                        <c:if test="${levelstatus.index != 0}">
                            <li><a href="javascript:Tab.addTab('${levelList.menuName}', '${levelList.url}');"<%--href="${levelList.url}"--%>><span class="${levelList.icon}"></span> ${levelList.menuName}<div class="lianjiexian"></div><span style="color: yellow; ">${levelList.menuContext}</span></a></li>
                        </c:if>
                    </c:forEach>

                </ul>
            </li>
        </c:forEach>

    </ul>
    <!-- END X-NAVIGATION -->
</div>
