<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>  

</head>  
<body>
Logon
<form action="/user/logon" name="userForm" method="post">
    <table>

        <tr>
            <td>User name: </td>
            <td><input type="text" name="username"  /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="text" name="password"  /></td>
        </tr>
    </table>

    <div>
        <input type="submit" value="Logon">
        <input type="reset" value="Reset">

    </div>
</form>
</body>  
</html>  