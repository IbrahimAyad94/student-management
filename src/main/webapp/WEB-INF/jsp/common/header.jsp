<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
<title>Student Management System</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body >
    <div class="fixed-header">
        <div class="container">
            <nav>
                <a href="${pageContext.request.contextPath}/pages/course/view-courses">View Courses</a>           
                <a href="${pageContext.request.contextPath}/logout">Logout</a>           

             	<span class="logged-name"> welcome: ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.student.name}</span>
             
             
            </nav>
        </div>
    </div>
    <div class="container">

 