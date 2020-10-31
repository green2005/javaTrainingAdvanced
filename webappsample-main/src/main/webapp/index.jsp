<html>
<body>
	<jsp:useBean id="obj"
		class="by.grodno.pvt.site.webappsample.OurTestJavaBean">
		<jsp:setProperty name="obj" property="value" value="VALUE" />
	</jsp:useBean>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<%@ include file="fragments/header.jsp"%>


	<a href="http://localhost:8080/webappsample/jstl1">Users</a>
    	<br />

	<br />
	<br />
	<br />
	<a href="http://localhost:8080/webappsample/deps">Departments</a>
   <br />


</body>
</html>
