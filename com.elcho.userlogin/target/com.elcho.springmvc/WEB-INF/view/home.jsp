<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>Insert title here</title>  

 
<script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		url: "http://localhost:8080/com.elcho.userlogin/testH2",
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
<h1>message:${message}</h1>  

this is view index
</body>  
</html>  