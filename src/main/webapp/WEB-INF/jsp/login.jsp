<%@ include file="common/un-auth-header.jsp"%>


<h3> Login Form </h3>


<c:if test="${param.error ne null }" >
	<h5 class="danger">Please Enter Valid Email And Password !</h5>
</c:if> 

<c:if test="${param.logout ne null }" >
	<h5 class="success">You Logged out Successfuly !</h5>
</c:if>

<form name="login" action="login" method="post">
	<label>Email:</label> <input type="email" name="username" required="true" /><br />
	<br /> 
	<label>Password:</label> <input type="password" name="password" required="true" /><br />
	<br /> 
	<input type="submit" value="login" />
</form>

<br/>


<%@ include file="common/footer.jsp"%>