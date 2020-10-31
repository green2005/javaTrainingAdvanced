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
	Edit dept:  "${requestScope.dep.getName()}"
    <br/>
    <br/>

	<c:if test="${requestScope.dep != null}">
    		<form action="edit" method="POST">

    		<div class="form-group row">
                		  <label for="Name" class="col-sm-2 col-form-label" >Department Name:</label>
                            <div class="col-sm-10">
                               <input type="text" class="form-control" id="tName" name="Name"
                                    aria-describedby="deptNameHelp" placeholder="Enter deptName"
                                    value=${requestScope.dep.getName()}>
                               <small id="firstNameHelp" class="form-text text-muted">Department name</small>
                            </div>
              </div>

    			<!-- Department Name: <input type="text" name="Name"
    				value=${requestScope.dep.getName()}> <br />
                 <br />

			 <input type="submit"
				value="Submit" /> -->
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</c:if>
    <br/>
</body>
</html>