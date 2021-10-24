<%@ include file="common/header.jsp"%>

<style>
table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
  background-color: #04AA6D;
  color: white;
}
</style>

<h3>View Courses </h3>


<c:if test="${not empty userCourses}">
    <table>
    	<tr>
    		<th> Name </th>
    		<th> Start Date </th>
    		<th> End Date </th>
    		<th> View Schedule </th>
    	</tr>
        <c:forEach var="course" items="${userCourses}">
            <tr>
                <td>${course.name}</td>
                <td><fmt:formatDate value="${course.startDate}" pattern="yyyy-MM-dd" /></td>
                <td><fmt:formatDate value="${course.endDate}" pattern="yyyy-MM-dd" /></td>                
                <td> <a href="${pageContext.request.contextPath}/web/v1/course/${course.id}/export-schedule"  target="_blacnk"> View Schedule </a> </td>
                <!-- <td> <button>Download Schedule</button> </td>  -->
            </tr>
        </c:forEach>
    </table>
</c:if>

<%@ include file="common/footer.jsp"%>