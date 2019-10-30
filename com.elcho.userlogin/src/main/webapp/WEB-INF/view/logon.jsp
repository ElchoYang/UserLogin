<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logon</title>

</head>  

<body class="text-center">

<jsp:include page="header.jsp"></jsp:include>




    <div class="home">

        <c:choose>
            <c:when test="${empty sessionScope.sessionUser.userName}">
                <form action="/user/logon" name="userForm" method="post">

                    <h1 class="h3 mb-3 font-weight-normal">Please Login</h1>

                    <label for="inputName" class="sr-only">User name: </label>
                    <input type="text" id="inputName" name="userName" class="form-control" placeholder="User name" required autofocus>

                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>

                    <div>${error}</div>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>

                    <button class="btn btn-lg btn-danger btn-block" type="reset">Reset</button>


                </form>
            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>
        <jsp:include page="footer.jsp"></jsp:include>
    </div>

</body>

</html>  