<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=us-ascii">
	<title>Lab Results</title>
	<jsp:include page="/WEB-INF/views/head.jsp"/>
</head>
<body>
	<jsp:include page="/WEB-INF/views/navigation.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
	            <c:if test="${pageContext.request.userPrincipal.name == 'doctor'}"> 
                	<h1><i class="fa fa-flask"></i> Lab Results</h1>
					<h3>Patient: ${patient_name}</h3>
					<hr />
				</c:if>
	            <c:if test="${pageContext.request.userPrincipal.name != 'doctor'}"> 
	            	<h1 class="page-header"><i class="fa fa-flask"></i> Lab Results</h1>
	            </c:if>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Lab Results
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table id="labresults_table" class="table table-striped table-bordered table-hover display">
                                <thead>
                                    <tr>	
										<th>Lab Name</th>
										<th>Lab Date</th>
										<th>Results</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div id="dialog1" style="display:none;" width="50%" title="Lab Results">
			<table id="results_detail_table" class="table table-striped table-bordered table-hover display">
			<thead>
			<tr>
				<th>Name</th>
				<th>Value</th>
				<th>Interpretation</th>
				<th>Reference Range</th>
				<th>Status</th>
			</tr>
			</thead>
			</table>
		</div>
		<!-- /.row -->
	</div>

	<jsp:include page="/WEB-INF/views/footer.jsp"/>

	<script type="text/javascript">

	$(document).ready(function() {
	    var table = $('#labresults_table').DataTable( {
	        "ajax": "get/history/labresults?pid=${pid}",
	        columns: [
	  				{ data: "name" },
	  	            { data: "date" },
	  	            { data: null}
	  	        ],
	  	      order: [ 1, 'desc' ],
	        "columnDefs": [ {
	            "targets": -1,
	            "data": null,
	            "defaultContent": "<a href=''>Click to see results</a>"
	        } ]
	    } );
	    
		var myTable = $("#results_detail_table").DataTable({
			paging: false,
			sorting: false,
			ordering: false,
			filter: false,
			order: [ 1, 'desc' ]
		});
	 
	    $('#labresults_table tbody').on( 'click', 'a', function (event) {
	    	event.preventDefault();
	    	myTable.clear();

	        var data = table.row( $(this).parents('tr') ).data();
	        var rows = data["tableData"];	        
	        for(var i = 0; i < rows.length; i++)
	        	{
	        		var row = rows[i];
	    	    	myTable.row.add(row);
	        	}
			
	        myTable.draw();
			//$("#dialog1").html(data["report"]);
	    	$("#dialog1").dialog({
	            maxWidth:900,
	            maxHeight:500,
	            width: 800,
	            height: 400
	    	});
	    } );
	} );
	
	</script>
</body>
</html>