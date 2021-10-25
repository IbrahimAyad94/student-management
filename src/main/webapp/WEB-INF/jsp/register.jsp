<%@ include file="common/un-auth-header.jsp"%>




<h3> Register Form </h3>
<form name="register" action="register" method="post">
	<label>Email:</label> <input type="email" name="email" required="true" /><br />
	<br /> 
	<label>Password:</label> <input type="password" name="password" required="true" /><br />
	<br /> 
	<label>Mobile:</label> <input type="text" name="mobileNumber" required="true" /><br />
	<br /> 
	<label>Name:</label> <input type="text" name="name" required="true" /><br />
	<br /> 
	<input type="submit" value="Register" />
</form>


<%@ include file="common/footer.jsp"%>