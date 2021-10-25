<%@ include file="common/header.jsp"%>


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
                <td> <a href="${pageContext.request.contextPath}/pages/course/${course.id}/export-schedule"  target="_blacnk"> View Schedule </a> </td>
                <!-- <td> <button>Download Schedule</button> </td>  -->
            </tr>
        </c:forEach>
    </table>
</c:if>

<%@ include file="common/footer.jsp"%>