<%@page import="by.grodno.pvt.site.webappsample.service.Dept"%>

<html>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="fragments/header.jsp"%>

	<br />


	<c:if test="${requestScope.deps != null}">
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">DepName</th>
					<th scope="col">WorkerCount</th>
					<th scope="col">AvgSalary</th>
					<th scope="col">EditAction</th>
					<th scope="col">RemoveAction</th>
				</tr>
			</thead>
			<c:forEach var="i" begin="1" end="${fn:length(requestScope.deps)}">
				<c:set var="dep" scope="request"
					value="${requestScope.deps[i-1]}" />
				<tr>
					<td scope="row">${requestScope.dep.getId()}</td>
					<td>${requestScope.dep.getName()}</td>
					<td>${requestScope.dep.getWorkerCount()}</td>
                	<td>${requestScope.dep.getAvgSalary()}</td>
					<td><a class="btn btn-secondary" href="http://localhost:8080/webappsample/deps/edit?depid=${requestScope.dep.getId()}">Edit
                                        	dept</a></td>
					<td><a class="btn btn-danger"
						href="http://localhost:8080/webappsample/deps/delete?depid=${requestScope.dep.getId()}">Delete
							dept</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<br />
	<br />

</body>
</html>
