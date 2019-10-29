<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link>
    <title>Home</title>

</head>


<body class="text-center">

<jsp:include page="header.jsp"></jsp:include>

 <div class="home">
    <a class="btn btn-lg btn-primary btn-block" href="/user/regist">Register</a>

    <a class="btn btn-lg btn-success btn-block" href="/user/logon">Login</a>

     <jsp:include page="footer.jsp"></jsp:include>
 </div>

</body>
</html>
