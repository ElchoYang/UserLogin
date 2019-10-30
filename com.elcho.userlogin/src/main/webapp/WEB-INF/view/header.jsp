<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: GuangHaoYang
  Date: 10/29/2019
  Time: 9:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link>
    <title>Header</title>
<script type="text/javascript" src="/static/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.bundle.min.js"></script>
<link type="text/css" rel="stylesheet" href="/static/css/bootstrap.min.css"></link>
<link type="text/css" rel="stylesheet" href="/static/css/bootstrap-grid.min.css"></link>
<link type="text/css" rel="stylesheet"  href="/static/css/bootstrap-reboot.min.css"></link>
<link type="text/css" rel="stylesheet"  href="/static/css/logon.css"></link>
<link href="/static/css/header.css" type="text/css" rel="stylesheet"></link>
</head>
<body>

<header class="header header--invert headroom headroom--not-bottom headroom--pinned headroom--top">
    <div class="container">
        <a href="/" class="header__logo" id="header-logo-link">
            <img src="/static/img/logo.png" alt="Maven Logo">
        </a>

        <nav class="nav nav--invert">

          <%--  <span class="nav__close"></span>--%>

            <c:choose>
                <c:when test="${sessionScope.sessionUser != null}">
                    <c:if test="${sessionScope.sessionUser.userName == 'admin'}">
                        <a class="nav__item" href="/user/admin" data-href="Admin" id="Admin-link"><span>Admin</span></a>
                    </c:if>
                    <c:if test="${sessionScope.sessionUser.userName != ''}">
                        <a class="nav__item" href="/user/tutorials" data-href="Admin" id="Tutorials-link"><span>Tutorials</span></a>
                        <a class="nav__item" href="/user/update" data-href="Account Update" id="Account-Update-link"><span>Account Update</span></a>
                        <a class="nav__item" href="/" data-href="Logout" id="Logout-link"><span>Logout</span></a>
                    </c:if>
                </c:when>
                <c:otherwise>
                     <a class="nav__item" href="/user/logon" data-href="Login" id="Login-link"><span>Login</span></a>
                     <a class="nav__item" href="/user/regist" data-href="Regist" id="Regist-link"><span>Regist</span></a>
                </c:otherwise>
            </c:choose>

        </nav>


        <c:if test="${sessionScope.sessionUser != null && sessionScope.sessionUser.userName != ''}">
                <div class="loginbar">
                    <span class="text">Login User: </span>
                    <span class="name">${sessionScope.sessionUser.userName}</span>
                </div>
        </c:if>
        <%--<div class="nav-toggle">
            <div class="nav-toggle__inner">
                <i></i>
                <i></i>
            </div>
        </div>--%>
    </div>
</header>
</body>
</html>
