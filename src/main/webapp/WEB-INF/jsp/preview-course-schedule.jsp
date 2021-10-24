<%@ include file="common/header.jsp"%>

<h3>Preview Course Schedule Of ${name}! </h3>

	<object>
   		<embed id="pdfID" type="text/html" width="800" height="600" 
   		src="data:application/pdf;base64,${encodedPDF}"/>
	</object>



<%@ include file="common/footer.jsp"%>