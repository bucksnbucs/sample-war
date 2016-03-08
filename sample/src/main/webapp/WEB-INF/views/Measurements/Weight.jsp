<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=us-ascii">
	<title>Weight Measurements</title>
	<jsp:include page="/WEB-INF/views/head.jsp"/>
</head>
<body>
	<jsp:include page="/WEB-INF/views/navigation.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
	            <c:if test="${pageContext.request.userPrincipal.name == 'doctor'}"> 
                	<h1><i class="glyphicon glyphicon-scale"></i> Weight Measurements</h1>
					<h3>Patient: ${patient_name}</h3>
					<hr />
				</c:if>
	            <c:if test="${pageContext.request.userPrincipal.name != 'doctor'}"> 
	            	<h1 class="page-header"><i class="glyphicon glyphicon-scale"></i> Weight Measurements</h1>
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
												<th>Weight (kg)</th>
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
	                            <div id="myChart" width="400" height="400">
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
	            url: "get/weight?tokenID=" + location.search.split('tokenID=')[1]
	        },
	        columns: [
	            { data: "source" },
	            { data: "date" },
	            { data: "value" }
	        ],
	        order: [ 1, 'desc' ],
	        pageLength: 10
	    });
		
		renderLiveTempGraph();
		
	});
	
	function renderLiveTempGraph() {
		  // Get ready to store our graph instance in a variable
		  var mainGraph;
		 
		  // Call our API
		  $.getJSON('get/weight?tokenID=' + location.search.split('tokenID=')[1],
		    function(results) {
		 
		      // Initialise a Morris line graph and store it in mainGraph
		      mainGraph = Morris.Line({
		        element: 'myChart',
		        // Tell Morris where the data is
		        data: results.data,
		        // Tell Morris which property of the data is to be mapped to which axis
		        xkey: 'date',
		        ykeys: ['value'],
		        labels: ['Weight'],
		        postUnits: 'kg',
		        lineColors: ['blue'],
		        resize: true
		        });

		    });
		}
	
	</script>
</body>
</html>