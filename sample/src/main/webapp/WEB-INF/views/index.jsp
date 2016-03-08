<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=us-ascii">
	<title>Health Keeper</title>
	<jsp:include page="/WEB-INF/views/head.jsp"/>
</head>
<body>
	<jsp:include page="/WEB-INF/views/navigation.jsp"/>

	<!-- /#page-wrapper -->
	<div id="page-wrapper">
		<div class="row">
	        <div class="col-lg-12">
	            <c:if test="${pageContext.request.userPrincipal.name == 'doctor'}"> 
	            	<h1>Health Overview</h1>
					<h3>Patient: ${patient_name}</h3>
					<hr />
				</c:if>

	            <c:if test="${pageContext.request.userPrincipal.name != 'doctor'}"> 
	            	<h1 class="page-header">Health Overview</h1>
	            </c:if>
	        </div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-green">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="glyphicon glyphicon-scale fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div id="weight_value" class="huge"></div>
								<div>Weight</div>
							</div>
						</div>
					</div>
					<a href="#">
						<div class="panel-footer">
							<a href="weight?tokenID=demo&pid=${pid}"><span class="pull-left">View Details</span> <span
								class="pull-right"><i class="fa fa-arrow-circle-right"></i></span></a>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="glyphicon glyphicon-bed fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div id="sleep_value" class="huge"></div>
								<div>Sleep Efficiency</div>
							</div>
						</div>
					</div>
					<a href="#">
						<div class="panel-footer">
							<a href="sleep?tokenID=demo&pid=${pid}"><span class="pull-left">View Details</span> <span
								class="pull-right"><i class="fa fa-arrow-circle-right"></i></span></a>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-red">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-heart fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div id="heart_rate_value" class="huge"></div>
								<div>Heart Rate</div>
							</div>
						</div>
					</div>
					<a href="#">
						<div class="panel-footer">
							<a href="blood_pressure?tokenID=demo&pid=${pid}"><span class="pull-left">View Details</span> <span
								class="pull-right"><i class="fa fa-arrow-circle-right"></i></span></a>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-yellow">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="glyphicon glyphicon-user fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div id="fat_value" class="huge"></div>
								<div>Body Fat</div>
							</div>
						</div>
					</div>
					<a href="#">
						<div class="panel-footer">
							<a href="body?tokenID=demo&pid=${pid}"><span class="pull-left">View Details</span> <span
								class="pull-right"><i class="fa fa-arrow-circle-right"></i></span></a>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
		</div>
		<hr />
		<!-- /.row -->
		<div class="row">
		    <!-- /.col-lg-4 -->
		    <div class="col-lg-4">
		        <div class="panel panel-default">
		            <div class="panel-heading">
		                Upcoming Appointments
		            </div>
		            <div class="panel-body">
						<dl>
						  <dt>Today</dt>
						  <dd>${appointmentList[0].startTime} - ${appointmentList[0].location}</dd>

						</dl>
						<dl>
						  <dt>This Month</dt>
						  <dt>${appointmentList[1].startDate}</dt>
						  <dd>${appointmentList[1].startTime} - ${appointmentList[1].location}</dd>
						</dl>
						<dl>
						  <dt>${appointmentList[2].startDate}</dt>
						  <dd>${appointmentList[2].startTime} - ${appointmentList[2].location}</dd>
						</dl>
						<a href="appointment?tokenID=demo&pid=${pid}">View More</a>
		            </div>
		        </div>
		    </div>
		    <!-- /.col-lg-4 -->
		    <div class="col-lg-4">
		        <div class="panel panel-default">
		            <div class="panel-heading">
		                Medication
		            </div>
		            <div class="panel-body">
						<dl>
						  <dt>Prescribed by: ${medicationList[0].prescriber}</dt>
						  <dd>${medicationList[0].medication}&nbsp;${medicationList[0].dosage}</dd>
						</dl>
						<dl>
						  <dt>Prescribed by: ${medicationList[1].prescriber}</dt>
						  <dd>${medicationList[1].medication}&nbsp;${medicationList[1].dosage}</dd>
						</dl>
						<a href="medications?tokenID=demo&pid=${pid}">View More</a>
		            </div>
		        </div>
		    </div>
		    <!-- /.col-lg-4 -->
		    <div class="col-lg-4">
		        <div class="panel panel-default">
		            <div class="panel-heading">
		                Exercise - Recent Activities
		            </div>
					<div class="panel-body">
						<dl id="last_activity">
							<dt></dt>
							<dd></dd>
						</dl>
						<dl id="prev_activity">
							<dt></dt>
							<dd></dd>
						</dl>
						<a href="activity?tokenID=demo&pid=${pid}">View More</a>
					</div>
				</div>
		    </div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/footer.jsp"/>

	<script type="text/javascript">

		$(document).ready(function() {
			$.ajax({
				url : 'get/last_weight',
				success : function(data) {
					$('#weight_value').html(data + 'Kg');
				}
			});
			$.ajax({
				url : 'get/last_sleep',
				success : function(data) {
					$('#sleep_value').html(data + '%');
				}
			});
			$.ajax({
				url : 'get/last_heart_rate',
				success : function(data) {
					$('#heart_rate_value').html(data);
				}
			});
			$.ajax({
				url : 'get/last_fat',
				success : function(data) {
					$('#fat_value').html(data + '%');
				}
			});
			$.ajax({
				url : 'get/last_activity',
				success : function(data) {
					var modal = $('#last_activity');
					modal.find('dt').html(data[0]);
					modal.find('dd').html(data[1] + ' ' + data[2] + ' m');
					modal = $('#prev_activity');
					modal.find('dt').html('2015-03-30');
					modal.find('dd').html(data[1] + ' 2332 m');
				}
			});
		});

	</script>
</body>
</html>