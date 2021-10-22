<%@ include file="common/un-auth-header.jsp"%>




<h3> Register Form </h3>
<form name="register" action="register" method="post">
	Email:<input type="email" name="email" /><br />
	<br /> 
	Password:<input type="password" name="password" /><br />
	<br /> 
	Mobile:<input type="text" name="mobileNumber" /><br />
	<br /> 
	Name:<input type="text" name="name" /><br />
	<br /> 
	<input type="submit" value="Register" />
</form>


<%@ include file="common/footer.jsp"%>