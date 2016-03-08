<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="pull-left" href="<%=request.getContextPath()%>"><img style="max-width:300px; margin-top:5px; margin-bottom:5px; margin-left:15px;" src="resources/images/healthkeeper.png" /></a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">
		<c:if test="${pageContext.request.userPrincipal.name == 'doctor'}"> 
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"><b>Select Patient</b><span class="caret"></span>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="?tokenID=demo&pid=3.955750000-01">Beard, Justin C.</a></li>
					<li class="divider"></li>
					<li><a href="?tokenID=demo&pid=3.000001751-01">Bolton, Dillon S.</a></li>
					<li class="divider"></li>
					<li><a href="?tokenID=demo&pid=3.568001602-01">Flores, Isabel S.</a></li>	
					<li class="divider"></li>									
					<li><a href="?tokenID=demo&pid=3.802001361-01">Justice, Alyssa D.</a></li>
				</ul></li>
		</c:if>
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-envelope fa-2x"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-messages">
				<li><a href="#">
						<div>
							<strong>Dr. Robert Jones</strong> <span class="pull-right text-muted">
								<em>Yesterday</em>
							</span>
						</div>
						<div>You have new test results available.</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<strong>Dr. Garciaparra</strong> <span class="pull-right text-muted">
								<em>Last week</em>
							</span>
						</div>
						<div>Re: Prescription refill request</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<strong>Dr. Marbury</strong> <span class="pull-right text-muted">
								<em>2 months ago</em>
							</span>
						</div>
						<div>Re: Your hemoglobin h1ac monitoring</div>
				</a></li>
				<li class="divider"></li>
				<li><a class="text-center" href="#"> <strong>Read
							All Messages</strong> <i class="fa fa-angle-right"></i>
				</a></li>
			</ul> <!-- /.dropdown-messages --></li>

		<!-- /.dropdown -->
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-user fa-2x"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<c:if test="${pageContext.request.userPrincipal.name != 'doctor'}">
					<li><a href="account?pid=${pid}">
						<i class="fa fa-user fa-fw"></i> My Account</a>
					</li>
					<li class="divider"></li>
				</c:if>
				<li><a href="j_spring_security_logout">
					<i class="fa fa-sign-out fa-fw"></i> Logout</a>
				</li>
			</ul>
		</li>
		<!-- /.dropdown-user -->

		<!-- /.dropdown-help -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i
				class="fa fa-question-circle fa-2x"></i> <i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#" id="help"><i class="fa fa-info fa-fw"></i><span
						id="enable-tooltip">Enable Tooltips</span></a></li>
				<li class="divider"></li>
				<li>

					<a href="#" data-toggle="modal" data-target="#popupVideo" 
					    data-src="http://www.youtube.com/embed/X2HWo0YKjO4?autoplay=1">
					    <i class="fa fa-play-circle-o"></i> Human API
					</a>
				</li>
				<li class="divider"></li>
				<li>
					<a href="#" data-toggle="modal" data-target="#popupVideo" 
					    data-src="http://www.youtube.com/embed/fOen5_dBcuQ?autoplay=1">
					    <i class="fa fa-play-circle-o"></i> Doctor's View
					</a>
				</li>
				<li class="divider"></li>
				<li>
					<a href="#" data-toggle="modal" data-target="#popupVideo" 
					    data-src="http://www.youtube.com/embed/umFkqXPghKY?autoplay=1">
					    <i class="fa fa-play-circle-o"></i> My Account
					</a>
				</li>
				<li class="divider"></li>
				<li>
					<a href="#" data-toggle="modal" data-target="#popupVideo" 
					    data-src="http://www.youtube.com/embed/dEDnAVAvGK0?autoplay=1">
					    <i class="fa fa-play-circle-o"></i> Measurements
					</a>
				</li>

			</ul></li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->

	<!-- Dialog for enabling tooltip, can be placed anywhere -->
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Help - FAQ</h4>
				</div>
				<div class="modal-body">
					<p>
						<strong>How do I reset my password?</strong><br />To reset your
						password go to My Account on the top right corner.
					</p>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade bs-example-modal-lg" id="popupVideo" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content" id="videoPlayer">
				<iframe width="100%" height="500px" src=""> </iframe>
			</div>
		</div>
	</div>
	<!-- End Modal -->

	<br />
	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				
				<li><a href="ataglance?pid=${pid}" data-toggle="tooltip" data-placement="bottom"
					title="View a concise overview of patient health">
					<i class="glyphicon glyphicon-zoom-in fa-fw"></i> Patient At-A-Glance</a>
				</li>
				<li><a href="index.html" data-toggle="tooltip"
					data-placement="bottom"
					title="Click here to see the options for Health History."><i
						class="fa fa-heart fa-fw"></i> Health History<span	
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="allergies?pid=${pid}" data-toggle="tooltip" data-placement="bottom"
							title="This will list all your registered allergies.">Allergies</a>
						</li>
						<li><a href="conditions?pid=${pid}" data-toggle="tooltip" data-placement="bottom"
							title="View known conditions for which you have been treated.">Conditions</a>
						</li>
						<li><a href="familyhistory?pid=${pid}" data-toggle="tooltip" data-placement="bottom"
							title="This will list all known conditions for close relatives.">Family History</a>
						</li>
						<li><a href="medications?pid=${pid}" data-toggle="tooltip" data-placement="bottom"
							title="Name, dosage and frequency of prescribed drugs, non-prescribed (over-the-counter) drugs and suplements.">Medications</a></li>
<!-- 						<li><a href="#" data-toggle="tooltip" data-placement="bottom" -->
<!-- 							title="This will list all past surguries.">Surgical History</a></li> -->
						<li><a href="labresults?pid=${pid}" data-toggle="tooltip" data-placement="bottom"
							title="Lab Results.">Lab Results</a></li>
					</ul> <!-- /.nav-second-level --></li>
				<li><a href="#" data-toggle="tooltip" data-placement="bottom"
					title="Keep track of all your measurements."><i
						class="fa fa-dashboard fa-fw"></i> Measurements<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li>
							<a href="activity?tokenID=demo&pid=${pid}" data-toggle="tooltip"
							data-placement="bottom"
							title="Exercise information from external sources.">Exercise</a>
						</li>
						<li>
							<a href="blood?tokenID=demo&pid=${pid}" data-toggle="tooltip"
							data-placement="bottom"
							title="Blood Glucose/Oxygen information from external sources.">Blood Glucose / Oxygen</a>
						</li>
						<li>
							<a href="blood_pressure?tokenID=demo&pid=${pid}" data-toggle="tooltip"
							data-placement="bottom"
							title="Blood Pressure information from external sources.">Blood Pressure</a>
						</li>
						<li>
							<a href="body?tokenID=demo&pid=${pid}" data-toggle="tooltip"
							data-placement="bottom"
							title="BMI/Body Fat information from external sources.">BMI / Body Fat</a>
						</li>
						<li>
							<a href="sleep?tokenID=demo&pid=${pid}" data-toggle="tooltip"
							data-placement="bottom"
							title="Sleep information from external sources.">Sleep</a>
						</li>
						<li>
							<a href="weight?tokenID=demo&pid=${pid}" data-toggle="tooltip"
							data-placement="bottom"
							title="Weight information from external sources.">Weight</a>
						</li>
					</ul> <!-- /.nav-second-level --></li>
				<li><a href="appointment?pid=${pid}" data-toggle="tooltip" data-placement="bottom"
					title="View your upcoming and past appointments.">
					<i class="fa fa-calendar"></i> Appointments</a>
				</li>
				<li><a href="document?pid=${pid}" data-toggle="tooltip" data-placement="bottom"
					title="Keep all your health related documents together with all your health data.">
					<i class="fa fa-file-text"></i> Documents</a>
				</li>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>
