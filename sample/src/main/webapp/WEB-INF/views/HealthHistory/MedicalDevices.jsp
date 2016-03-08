<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=us-ascii">
	<title>Human API Activity</title>
	<jsp:include page="/WEB-INF/views/head.jsp"/>
</head>
<body>
	<jsp:include page="/WEB-INF/views/navigation.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Human API Data</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Medical Devices
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table id="medicaldevice_table" class="table table-striped table-bordered table-hover display">
                                <thead>
                                    <tr>	
										<th>Device Code</th>
										<th>Manufacturer</th>
										<th>Model</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->
	</div>

	<jsp:include page="/WEB-INF/views/footer.jsp"/>

	<script type="text/javascript">

	$(document).ready(function() {

		$('#medicaldevice_table').DataTable( {
	        ajax: {
	            url: "get/history/medicaldevices"
	        },
	        columns: [
	  	        { data: "device" },
	            { data: "manufacturer" },
	            { data: "model" }
	        ],
	        order: [ 0, 'desc' ],
	        pageLength: 10
	    });

	});
	
	</script>
</body>
</html>