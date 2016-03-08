<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=us-ascii">
	<title>Blood Glucose / Oxygen Measurements</title>
	<jsp:include page="/WEB-INF/views/head.jsp"/>
</head>
<body>
	<jsp:include page="/WEB-INF/views/navigation.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
	            <c:if test="${pageContext.request.userPrincipal.name == 'doctor'}"> 
                	<h1><i class="fa fa-heart"></i> Blood Glucose / Oxygen Measurements</h1>
					<h3>Patient: ${patient_name}</h3>
					<hr />
				</c:if>
	            <c:if test="${pageContext.request.userPrincipal.name != 'doctor'}"> 
	            	<h1 class="page-header"><i class="fa fa-heart"></i> Blood Glucose / Oxygen Measurements</h1>
	            </c:if>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                	<!-- 
                    <div class="panel-heading">
                        BMI / Body Fat
                    </div>
                    -->
                    <!-- /.panel-heading -->
                    <div class="panel-body">
						<!-- Nav tabs -->
                        <ul class="nav nav-tabs">
                            <!-- <li class="active"><a href="#analysis" data-toggle="tab">Analysis</a>
                            </li> -->
                            <li class="active"><a href="#graphs" data-toggle="tab">Graphs</a>
                            </li>
                            <li><a href="#data" data-toggle="tab">Data</a>
                            </li>
                            <!--
                            <li><a href="#resources" data-toggle="tab">Resources</a>
                            </li>
                            -->
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">
	                        <!-- Tab panes -->
                            <!-- <div class="tab-pane fade in active" id="analysis">
                            	<h4>Last 30 Days</h4>
                            	<p>...</p>
                            </div> -->
                            <div class="tab-pane fade in" id="data">
                            	<br />
		                        <div class="dataTable_wrapper" style="width:98%;">
		                            <table id="activity_table" class="table table-striped table-bordered table-hover display">
		                                <thead>
		                                    <tr>	
												<th>Source</th>
												<th>Date</th>
												<th>Glucose (mg/dL)</th>
												<th>Oxygen (%SpO2)</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
	                        <!-- Tab panes -->
	                        <!--
                            <div class="tab-pane fade in" id="resources">
                            	<h5><a href="http://www.webmd.com/sleep-disorders/">WebMD - Sleep Disorders</a>  <span class="label label-success">Doctor Certified</span></h5>
                            	<h5><a href="http://blog.fitbit.com/a-good-nights-sleep/#i.160efzpoyey7x6">Fitbit - A Good Nights Sleep</a>  <span class="label label-info">HealthKeeper Favorite</span></h5>
                            	<h5><a href="http://sleepfoundation.org/sleep-health">Sleep Foundation</a></h5>

                            </div>
                            -->
	                        <!-- Tab panes -->
    	                    <div class="tab-pane fade in active" id="graphs">
    	                    	<br />
								<div class="dataTable_wrapper">
									<div class="btn-group" data-toggle="buttons">
										<label id="button1" class="btn btn-primary active">
											<input type="radio" name="options" id="option1" autocomplete="off" checked>Glucose
										</label>
										<label id="button2" class="btn btn-primary">
											<input type="radio" name="options" id="option2" autocomplete="off">Oxygen
										</label>
									</div>
									<div id="myChart" style="min-height:400;height:400;width=100%">
									</div>
								</div>
                	        </div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<jsp:include page="/WEB-INF/views/footer.jsp"/>

	<script type="text/javascript">

	$(document).ready(function() {

		$('#activity_table').DataTable( {
	        ajax: {
	            url: "get/blood?tokenID=" + location.search.split('tokenID=')[1]
	        },
	        columns: [
	            { data: "source" },
	            { data: "date" },
	            { data: "glucose" },
	            { data: "oxygen" }
	        ],
	        order: [ 1, 'desc' ],
	        pageLength: 10
	    });

		function drawGraph(results, label, color, units) {
			// Get ready to store our graph instance in a variable
			$('#myChart').html('');
			Morris.Line({
		        element: 'myChart',
		        // Tell Morris where the data is
		        data: results.data,
		        // Tell Morris which property of the data is to be mapped to which axis
		        xkey: 'date',
		        ykeys: ['value'],
		        labels: [label],
		        lineColors: [color],
		        postUnits: units,
		        resize: true
		        });
		}

		var token = location.search.split('tokenID=')[1],
		glucoseData = $.getJSON('get/blood_glucose?tokenID=' + token, function(result){
			drawGraph(result, 'Glucose', 'green', ' mg/dL');}),
		oxygenData = $.getJSON('get/blood_oxygen?tokenID=' + token);

		$("#button1").click(function() {drawGraph(glucoseData.responseJSON, 'Glucose', 'green', ' mg/dL');});
		$("#button2").click(function() {drawGraph(oxygenData.responseJSON, 'Oxygen', 'blue', '% SpO2');});
		$("#button1").trigger('click'); 

	});

	</script>
</body>
</html>