<%@page import="by.grodno.pvt.site.webappsample.service.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<html>
<body>
	<%@ include file="fragments/header.jsp"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<br/>
	Edit user:  "${requestScope.user.getFirstName()}"
    <br/>
    <br/>

	<c:if test="${requestScope.user != null}">
		<form action="edit" method="POST">
			First Name: <input type="text" name="firstName"
				value=${requestScope.user.getFirstName()}> <br /> Last
			Name: <input type="text" name="lastName"
				value=${requestScope.user.getLastName()}> <br /> BirthDate:
			<input type="text" name="birthdate" value = ${
			 requestScope.user.getBirthDateString()}
				> <br />
				
				<input type="radio" id="male" name="male" value="true"
				    <c:if test="${requestScope.user.isMale()}">
				        checked
                     </c:if>>
            	<label for="male">Male</label><br>

             <input type="radio" id="female" name="male" value="false"
               <c:if test="${!requestScope.user.isMale()}">
             				        checked
                </c:if>>
             <label for="female">Female</label><br>

			<input type="submit"
				value="Submit" />
		</form>
	</c:if>
    <br/>
</body>
</html>

