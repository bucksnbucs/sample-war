<!-- /#page-wrapper -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Health Overview</h1>
		</div>
            <div class="col-lg-12">
	            <c:if test="${pageContext.request.userPrincipal.name == 'doctor'}"> 
                	<h1 class="page-header">Health Overview</h1>
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
						<a href="weight?tokenID=demo"><span class="pull-left">View Details</span> <span
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
						<a href="sleep?tokenID=demo"><span class="pull-left">View Details</span> <span
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
						<a href="blood_pressure?tokenID=demo"><span class="pull-left">View Details</span> <span
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
						<a href="body?tokenID=demo"><span class="pull-left">View Details</span> <span
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
					  <dd>8:35 AM - Dr. Rogers</dd>
					  <dd>5:00 PM - Dr. Rogers</dd>
					</dl>
					<dl>
					  <dt>Tomorrow</dt>
					  <dd>8:35 AM - Dr. Rogers</dd>
					</dl>
					<dl>
					  <dt>In 2 days</dt>
					  <dd>5:00 PM - Dr. Rogers</dd>
					</dl>
					<a href="appointment">View More</a>
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
					  <dt>Prescribed by: Dr. Kaden</dt>
					  <dd>OMS 50 every 6 hours</dd>
					</dl>
					<dl>
					  <dt>Prescribed by: Dr. Stuart</dt>
					  <dd>glyburide 5.0mg a day</dd>
					</dl>
					<a href="medications">View More</a>
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
					<dl id="#last_activity">
					  <dt></dt>
					  <dd></dd>
					</dl>
					<dl>
					  <dt>Yesterday</dt>
					  <dd>Walk 11 miles</dd>
					</dl>
					<a href="activity?tokenID=demo">View More</a>
	            </div>
	        </div>
	    </div>
	</div>
</div>
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
				modal.find('dt').html(data + '%');
				modal.find('dd').html(' LECO ');
			}
		});
	});

</script>