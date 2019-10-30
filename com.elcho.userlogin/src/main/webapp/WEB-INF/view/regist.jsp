<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>

</head>


<body class="text-center">

<jsp:include page="header.jsp"></jsp:include>

<div class="home">
	<form action="/user/regist" name="userForm" method="post">

		<h1 class="h3 mb-3 font-weight-normal">Registration</h1>

		<label for="inputName" class="sr-only">Name: </label>
		<input type="text" id="inputName" name="Name" class="form-control" placeholder="Name" required autofocus>

		<label for="inputEmail" class="sr-only">Email: </label>
		<input type="text" id="inputEmail" name="email" class="form-control" placeholder="Email" required>


		<label for="inputUsername" class="sr-only">username: </label>
		<input type="text" id="inputUsername" name="userName" class="form-control" placeholder="User Name" required>


		<label for="inputPassword" class="sr-only">Password</label>
		<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>

		<div class="error">${error}</div>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>

		<button class="btn btn-lg btn-danger btn-block" type="reset">Reset</button>

		<jsp:include page="footer.jsp"></jsp:include>
	</form>
</div>

</body>
</html>