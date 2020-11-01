<%@page import="by.grodno.pvt.site.webappsample.service.Dept"%>
<html>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<body>
	<%@ include file="fragments/header.jsp"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<br/>

	<c:if test="${requestScope.dep != null}">
	  Edit dept:  "${requestScope.dep.getName()}"
	</c:if>

	<c:if test="${requestScope.dep == null}">
	  Add dept
	</c:if>

    <br/>
    <br/>

	<c:if test="${requestScope.dep != null}">
    		<form action="edit" method="POST">
    </c:if>

    <c:if test="${requestScope.dep ==  null}">
        		<form action="add" method="POST">
    </c:if>

    		<div class="form-group row">
                		  <label for="Name" class="col-sm-2 col-form-label" >Department Name:</label>
                            <div class="col-sm-10">
                               <input type="text" class="form-control" id="tName" name="Name"
                                    aria-describedby="deptNameHelp" placeholder="Enter deptName"
                                    <c:if test="${requestScope.dep != null}">
                                      value=${requestScope.dep.getName()}
                                    </c:if>
                                >
                               <small id="firstNameHelp" class="form-text text-muted">Department name</small>
                            </div>
              </div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
    <br/>
</body>
</html>