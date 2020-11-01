<%@page import="by.grodno.pvt.site.webappsample.service.User"%>
<%@page import="by.grodno.pvt.site.webappsample.service.Dept"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
	<c:if test="${requestScope.user != null}">
	  Edit user:  "${requestScope.user.getFirstName()}"
	</c:if>
	<c:if test="${requestScope.user == null}">
      Add new user
    </c:if>
    <br/>
    <br/>
            <c:if test="${requestScope.user == null}">
			    <form action="add" method="POST">
            </c:if>

            <c:if test="${requestScope.user != null}">
                <form action="edit" method="POST">
            </c:if>

    		<div class="form-group row">
    		  <label for="firstName" class="col-sm-2 col-form-label" >First Name:</label>
                <div class="col-sm-10">
                   <input type="text" class="form-control" id="firstName" name="firstName" aria-describedby="firstNameHelp" placeholder="Enter firstName"
                   <c:if test="${requestScope.user != null}">
                       value=${requestScope.user.getFirstName()}>
                   </c:if>
                   <small id="firstNameHelp" class="form-text text-muted">First name of a worker</small>
                </div>
            </div>

            <div class="form-group row">
               <label for="lastName" class="col-sm-2 col-form-label" >Last Name:</label>
                 <div class="col-sm-10">
                    <input type="text" class="form-control" id="lastName" name="lastName" aria-describedby="lastNameHelp" placeholder="Enter lastName"
                     <c:if test="${requestScope.user != null}">
                       value=${requestScope.user.getLastName()}>
                     </c:if>
                    <small id="lastNameHelp" class="form-text text-muted">Last name of a worker</small>
                 </div>
            </div>

            <div class="form-group row">
               <label for="bday" class="col-sm-2 col-form-label" >BirthDate:</label>
                 <div class="col-sm-10">
                    <input type="text" class="form-control" id="bday" name="bday" aria-describedby="birthdateHelp"
                        placeholder="Enter birthdate"
                                                    <c:if test="${requestScope.user != null}">
                                                                    value=<%
                                                                 			 Date d = ((User) request.getAttribute("user")).getBirthdate();
                                                                 			 if (d!=null){
                                                                 			    out.println(new SimpleDateFormat("yyyy-MM-dd").format(d));
                                                                 			    } else
                                                                 			    {out.println("");}
                                                                 			 %>

                                                                 	>
                                                    </c:if>
                    <small id="birthdateHelp" class="form-text text-muted">YYYY-MM-DD format</small>
                 </div>
            </div>

            <fieldset class="form-group">
                <div class="row">
                  <legend class="col-form-label col-sm-2 pt-0">Sex</legend>
                  <div class="col-sm-10">
                    <div class="form-check">

                      <input class="form-check-input" type="radio" name="male" id="male" value="true"
                            <%
                               if (request.getAttribute("user")!=null)
                               {
                                 boolean b2= ((User) request.getAttribute("user")).isMale();
                                 if (b2){out.println("checked");} else {out.println("");}
                               }
                            %>
                       >

                      <label class="form-check-label" for="male">
                        Male
                      </label>
                    </div>
                    <div class="form-check">
                      <input class="form-check-input" type="radio" name="female" id="female" value="false"
                             <% if (request.getAttribute("user")!=null){
                                 boolean b1 = ((User) request.getAttribute("user")).isMale();
                                 if (!b1){
                                    out.println("checked");} else {out.println("");}
                             }
                             %>
                       >
                      <label class="form-check-label" for="female">
                        Female
                      </label>
                    </div>
                  </div>
                </div>
            </fieldset>


            <div class="form-group row">
                           <label for="dept" class="col-sm-2 col-form-label" >Department:</label>
                             <div class="col-sm-10">
                                <input type="text" class="form-control" id="dept" name = "dept" aria-describedby="deptHelp"
                                    placeholder="Enter department"  value=<% if (request.getAttribute("user")!=null){
                                                                            Dept dept = ((User) request.getAttribute("user")).getDept();
                                                                             if (dept!=null){
                                                                                out.println(dept.getName());
                                                                               } else { out.println("");}
                                                                             }
                                                                             			 %> >
                                <small id="deptHelp" class="form-text text-muted">Department name</small>
                             </div>
                        </div>

    		<div class="form-group row">
    		  <label for="salary" class="col-sm-2 col-form-label" >Salary:</label>
                <div class="col-sm-10">
                   <input type="text" class="form-control" id="salary" name="salary" aria-describedby="salaryHelp" placeholder="Enter salary"
                   <c:if test="${requestScope.user != null}">
                       value=${requestScope.user.getSalary()}>
                   </c:if>
                   <small id="salaryHelp" class="form-text text-muted">Salary of a worker</small>
                </div>
            </div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
    <br/>
</body>
</html>
