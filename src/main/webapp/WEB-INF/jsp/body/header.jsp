<%--
  Created by IntelliJ IDEA.
  User: Easy
  Date: 2017-09-25
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="x-navigation x-navigation-horizontal x-navigation-panel" >
    <!-- TOGGLE NAVIGATION -->
    <li class="xn-icon-button">
        <a href="#" class="x-navigation-minimize"><span class="fa fa-dedent"></span></a>
    </li>
    <!-- END TOGGLE NAVIGATION -->
    <!-- SEARCH -->
    <li class="xn-search">
        <form role="form">
            <input type="text" name="search" placeholder="Search...">
        </form>
    </li>
    <!-- END SEARCH -->
    <!-- POWER OFF -->
    <li class="xn-icon-button pull-right last">
        <a href="#"><span class="fa fa-power-off"></span></a>
        <ul class="xn-drop-left animated zoomIn">
            <li><a href="pages-lock-screen.html"><span class="fa fa-lock"></span><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 锁屏</font></font></a></li>
            <li><a href="/login/logout" ><span class="fa fa-sign-out"></span><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 登出</font></font></a></li>
        </ul>
    </li>
    <!-- END POWER OFF -->
    <!-- MESSAGES -->
    <li class="xn-icon-button pull-right">
        <a href="#"><span class="fa fa-comments"></span></a>
        <div class="informer informer-danger"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">4</font></font></div>
        <div class="panel panel-primary animated zoomIn xn-drop-left xn-panel-dragging">
            <div class="panel-heading">
                <h3 class="panel-title"><span class="fa fa-comments"></span><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 消息</font></font></h3>
                <div class="pull-right">
                    <span class="label label-danger"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">4新</font></font></span>
                </div>
            </div>
            <div class="panel-body list-group list-group-contacts scroll mCustomScrollbar _mCS_2 mCS-autoHide mCS_no_scrollbar" style="height: 200px;"><div id="mCSB_2" class="mCustomScrollBox mCS-light mCSB_vertical mCSB_inside" style="max-height: 200px;" tabindex="0"><div id="mCSB_2_container" class="mCSB_container mCS_y_hidden mCS_no_scrollbar_y" style="position:relative; top:0; left:0;" dir="ltr">
                <a href="#" class="list-group-item">
                    <div class="list-group-status status-online"></div>
                    <img src="/static/assets/images/users/user2.jpg" class="pull-left mCS_img_loaded" alt="约翰·多">
                    <span class="contacts-title"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">约翰·多</font></font></span>
                    <p><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Praesent placerat tellus id augue condimentum</font></font></p>
                </a>
                <a href="#" class="list-group-item">
                    <div class="list-group-status status-away"></div>
                    <img src="/static/assets/images/users/user.jpg" class="pull-left mCS_img_loaded" alt="德米特里·伊瓦尼乌克">
                    <span class="contacts-title"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">德米特里·伊瓦尼乌克</font></font></span>
                    <p><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Don &lt;/s&gt;。。。。。。。。。。</font></font></p>
                </a>
                <a href="#" class="list-group-item">
                    <div class="list-group-status status-away"></div>
                    <img src="/static/assets/images/users/user3.jpg" class="pull-left mCS_img_loaded" alt="纳迪亚·阿里">
                    <span class="contacts-title"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">纳迪亚·阿里</font></font></span>
                    <p><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">毛利斯牛皮癣</font></font></p>
                </a>
                <a href="#" class="list-group-item">
                    <div class="list-group-status status-offline"></div>
                    <img src="/static/assets/images/users/user6.jpg" class="pull-left mCS_img_loaded" alt="达斯·维德">
                    <span class="contacts-title"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">达斯·维德</font></font></span>
                    <p><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">我想把我的钱拿回来！</font></font></p>
                </a>
            </div><div id="mCSB_2_scrollbar_vertical" class="mCSB_scrollTools mCSB_2_scrollbar mCS-light mCSB_scrollTools_vertical" style="display: none;"><div class="mCSB_draggerContainer"><div id="mCSB_2_dragger_vertical" class="mCSB_dragger" style="position: absolute; min-height: 30px; top: 0px;" oncontextmenu="return false;"><div class="mCSB_dragger_bar" style="line-height: 30px;"></div></div><div class="mCSB_draggerRail"></div></div></div></div></div>
            <div class="panel-footer text-center">
                <a href="pages-messages.html"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">显示所有消息</font></font></a>
            </div>
        </div>
    </li>
    <!-- END MESSAGES -->
    <!-- TASKS -->
    <li class="xn-icon-button pull-right">
        <a href="#"><span class="fa fa-tasks"></span></a>
        <div class="informer informer-warning"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">3</font></font></div>
        <div class="panel panel-primary animated zoomIn xn-drop-left xn-panel-dragging">
            <div class="panel-heading">
                <h3 class="panel-title"><span class="fa fa-tasks"></span><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 任务</font></font></h3>
                <div class="pull-right">
                    <span class="label label-warning"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">3活跃</font></font></span>
                </div>
            </div>
            <div class="panel-body list-group scroll mCustomScrollbar _mCS_3 mCS-autoHide mCS_no_scrollbar" style="height: 200px;"><div id="mCSB_3" class="mCustomScrollBox mCS-light mCSB_vertical mCSB_inside" style="max-height: 200px;" tabindex="0"><div id="mCSB_3_container" class="mCSB_container mCS_y_hidden mCS_no_scrollbar_y" style="position:relative; top:0; left:0;" dir="ltr">
                <a class="list-group-item" href="#">
                    <strong><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Ph us ue ue。</font></font></strong>
                    <div class="progress progress-small progress-striped active">
                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%;"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">50％
                        </font></font></div>
                    </div>
                    <small class="text-muted"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">John Doe，2015年9月25日/ 50％</font></font></small>
                </a>
                <font style="vertical-align: inherit;"><a class="list-group-item" href="#"><strong><font style="vertical-align: inherit;">Aenean ac cursus</font></strong></a></font><a class="list-group-item" href="#">
                <strong><font style="vertical-align: inherit;"></font></strong>
                <div class="progress progress-small progress-striped active">
                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%;"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">80％
                    </font></font></div>
                </div>
                <small class="text-muted"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Dmitry Ivaniuk，2015年9月24日/ 80％</font></font></small>
            </a>
                <font style="vertical-align: inherit;"><a class="list-group-item" href="#"><strong><font style="vertical-align: inherit;">Lorem ipsum dolor</font></strong></a></font><a class="list-group-item" href="#">
                <strong><font style="vertical-align: inherit;"></font></strong>
                <div class="progress progress-small progress-striped active">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="95" aria-valuemin="0" aria-valuemax="100" style="width: 95%;"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">95％
                    </font></font></div>
                </div>
                <small class="text-muted"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">John Doe，23 Sep 2015/95％</font></font></small>
            </a>
                <font style="vertical-align: inherit;"><a class="list-group-item" href="#"><strong><font style="vertical-align: inherit;">Cras suscipit ac quam at tincidunt。</font></strong></a></font><a class="list-group-item" href="#">
                <strong><font style="vertical-align: inherit;"></font></strong>
                <div class="progress progress-small">
                    <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">100％
                    </font></font></div>
                </div>
                <small class="text-muted"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">John Doe，2015年9月21日/ </font></font></small>
                <small class="text-success"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">完成</font></font></small>
            </a>
            </div><div id="mCSB_3_scrollbar_vertical" class="mCSB_scrollTools mCSB_3_scrollbar mCS-light mCSB_scrollTools_vertical" style="display: none;"><div class="mCSB_draggerContainer"><div id="mCSB_3_dragger_vertical" class="mCSB_dragger" style="position: absolute; min-height: 30px; top: 0px;" oncontextmenu="return false;"><div class="mCSB_dragger_bar" style="line-height: 30px;"></div></div><div class="mCSB_draggerRail"></div></div></div></div></div>
            <div class="panel-footer text-center">
                <a href="pages-tasks.html"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">显示所有任务</font></font></a>
            </div>
        </div>
    </li>
    <!-- END TASKS -->
    <!-- LANG BAR -->
    <li class="xn-icon-button pull-right">
        <a href="#"><span class="flag flag-gb"></span></a>
        <ul class="xn-drop-left xn-drop-white animated zoomIn">
            <li><a href="#"><span class="flag flag-gb"></span><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 英语</font></font></a></li>
            <li><a href="#"><span class="flag flag-de"></span><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 德语</font></font></a></li>
            <li><a href="#"><span class="flag flag-cn"></span><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 中文</font></font></a></li>
        </ul>
    </li>
    <!-- END LANG BAR -->
</ul>
