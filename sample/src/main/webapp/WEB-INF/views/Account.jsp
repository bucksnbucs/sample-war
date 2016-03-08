<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=us-ascii">
<title>My Account</title>
<jsp:include page="/WEB-INF/views/head.jsp" />

<script src='https://connect.humanapi.co/connect.js'></script>

</head>
<body>

	<jsp:include page="/WEB-INF/views/navigation.jsp" />
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header"><i class="fa fa-user"></i> My Account</h1>
				<c:if test="${success != null}">
			        <div class="alert alert-success alert-dismissable">
			            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			            <strong>Success </strong>Your account information has been updated successfully.
			        </div>
				</c:if>		
				<c:if test="${fail != null}">
			        <div class="alert alert-danger alert-dismissable">
			            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			            <strong>Error </strong>Please do not leave any field empty. 
			        </div>
				</c:if>						
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">		
                <form:form role="form" method="post" modelAttribute="patient">
                    <div class="form-group">
                        <label>Name</label>
                        <form:input path="firstName" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Last Name</label>
                        <form:input path="lastName" class="form-control" />
                    </div>
					<div class="form-group">
					    <label>Gender</label>
					    <div class="radio">
					        <label>
						        <form:radiobutton path="gender" value="Male" />Male
					        </label>
					    </div>
					    <div class="radio">
					        <label>
						        <form:radiobutton path="gender" value="Female" />Female
					        </label>
					    </div>
					</div>
                    <div class="form-group">
                        <label>Race</label>
						<form:select path="race" class="form-control">
			                <form:option value="Asian American">Asian American</form:option>
			                <form:option value="Black American or African American">Black American or African American</form:option>
			                <form:option value="Hispanic or Latino">Hispanic or Latino</form:option>
			                <form:option value="Native American or Alaska Native">Native American or Alaska Native</form:option>
			                <form:option value="Native Hawaiians or Other Pacific Islander">Native Hawaiians or Other Pacific Islander</form:option>
			                <form:option value="White/Non-Hispanic">White/Non-Hispanic</form:option>
			            </form:select>

                    </div>
                    <div class="form-group">
                        <label>Marital Status</label>
						<form:select path="maritalStatus" class="form-control">
			                <form:option value="Single">Single</form:option>
			                <form:option value="Married">Married</form:option>
			                <form:option value="Divorced">Divorced</form:option>
			                <form:option value="Widowed">Widowed</form:option>
			            </form:select>
                    </div>
                    <div class="form-group">
                        <label>Birthdate</label>
                        <form:input path="birthdate" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Height</label>
                        <form:input path="height" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Human API</label><br />
						<img id='connect-health-data-btn' width="200px" src='https://connect.humanapi.co/assets/button/blue.png'/>
					</div>
					<button type="submit" class="btn btn-primary btn-block">Update My Account</button>
            	</form:form>
			</div>
		</div>
 	</div>
	
	<jsp:include page="/WEB-INF/views/footer.jsp" />

	<script type="text/javascript">

	$(document).ready(function() {
		$("#connect-health-data-btn").click(function() {
			var options = {
					  clientId: 'd83487272a4b22e4c726812aeef23d26f7bcc3b5', // you should have this from the succesful authentication flow
					  clientUserId: encodeURIComponent('demo123@humanapi.co'), // can be email or any other unique identifier
					  close: function() {
					    // optional callback that will be called if the user closes the popup 
					    // without connecting any data sources.
					  },
					  error: function(err) {
					    // optional callback that will be called if an error occurs while 
					    // loading the popup.
					    
					    // `err` is an object with the fields: `code`, `message`, `detailedMessage`
					  } 
					}
					HumanConnect.open(options);
			});
	});

	</script>
</body>
</html>