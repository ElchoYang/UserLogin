<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>



	<script type="text/javascript" src="/static/js/jquery-1.7.1.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function(){
			$.ajax({
				url: "http://localhost:9090/testH2",
				type:"post",
				success: function(res) {
					console.log(res);
				},
				error: function(error) {}
			});

		});
	</script>
</head>
<body>


<form action="">
	<table>
		<tr>
		 <td>Name: </td>
		 <td><input type="text" name="name" /></td>
		</tr>
		<tr>
		 <td>Email:</td>
		 <td><input type="text" name="email"  /></td>
		</tr>	
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
		<button value="Register">Register</button>
		<button value="Reset" type="reset">Reset</button>
	</div>
</form>
</body>
</html>