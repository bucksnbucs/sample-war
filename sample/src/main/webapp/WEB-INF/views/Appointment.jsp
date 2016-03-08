<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
	<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	<title>Appointments</title>
	<jsp:include page="/WEB-INF/views/head.jsp"/>
	<link href="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.3.1/fullcalendar.min.css" rel="stylesheet" type="text/css">
	<style type='text/css'>
		#calendar {
			width: 900px;
			margin: 0 auto;
			text-align: center;
			font-size: 14px;
			font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/navigation.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
	            <c:if test="${pageContext.request.userPrincipal.name == 'doctor'}"> 
                	<h1><i class="fa fa-calendar"></i> Appointments</h1>
					<h3>Patient: ${patient_name}</h3>
					<hr />
				</c:if>
	            <c:if test="${pageContext.request.userPrincipal.name != 'doctor'}"> 
	            	<h1 class="page-header"><i class="fa fa-calendar"></i> Appointments</h1>
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
                        Weight
                    </div>
                    -->
                    <!-- /.panel-heading -->
                    <div class="panel-body">
						<!-- Nav tabs -->
                        <ul class="nav nav-tabs">
                            <!-- <li class="active"><a href="#analysis" data-toggle="tab">Analysis</a>
                            </li> -->
                            <li class="active"><a href="#graphs" data-toggle="tab">Calendar</a>
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
		                            <table id="appointment_table" class="table table-striped table-bordered table-hover display">
		                                <thead>
		                                    <tr>	
												<th>Status</th>
												<th>Date</th>
												<th>Time</th>
												<th>Location</th>
												<th>Description</th>
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
	                            <div id='calendar' style="width: 100%;"></div>
                	        </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/footer.jsp"/>
	<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.1/moment.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.3.1/fullcalendar.js"></script>

	<script type='text/javascript'>

		$(document).ready(function() {
			
			$('#calendar').fullCalendar({
				header: {
					left: 'prev,next today',
					center: 'title',
					right: 'month,agendaWeek,agendaDay'
				},
				defaultView: 'month',
				editable: false,
				events: function(start, end, timezone, callback) {
			        $.ajax({
			            url: 'get/history/appointments?pid=${pid}',
			            dataType: 'json',
						data: JSON.stringify({
				            start: $("#start").val(),
				            end: $("#end").val()
				        }),
				        success: function(response) {
				            //console.log(response['data']);
				            response['data'];
				            var events = [];
				            for (var i = 0; i < response['data'].length; i++) {
						    	//console.log(response['data'][i]);
								events.push({
			                        title: response['data'][i]['location'],
			                        start: response['data'][i]['start'], 
			                        end: response['data'][i]['end'] 	                        
			                    });
							}
			                callback(events);							
				        },
				        error: function(response) {
				            console.log(response);
				        }
			        });
			    }
			});
			
		});

	</script>

	<script type="text/javascript">

	$(document).ready(function() {

		$('#appointment_table').DataTable( {
	        ajax: {
	            url: "get/history/appointments?pid=${pid}"
	        },
	        columns: [
	            { data: "status" },
	            { data: "startDate" },
	            { data: "startTime" },
	            { data: "location" },
	            { data: "description" }
	        ],
	        order: [ 2, 'desc' ],
	        pageLength: 10
	    });

	});

	</script>
</body>
</html>