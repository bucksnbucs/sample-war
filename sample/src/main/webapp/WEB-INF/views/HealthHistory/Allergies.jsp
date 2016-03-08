<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=us-ascii">
	<title>Allergies</title>
	<jsp:include page="/WEB-INF/views/head.jsp"/>
</head>
<body>
	<jsp:include page="/WEB-INF/views/navigation.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
	            <c:if test="${pageContext.request.userPrincipal.name == 'doctor'}"> 
                	<h1><i class="fa fa-medkit"></i> Allergies</h1>
					<h3>Patient: ${patient_name}</h3>
					<hr />
				</c:if>
	            <c:if test="${pageContext.request.userPrincipal.name != 'doctor'}"> 
	            	<h1 class="page-header"><i class="fa fa-medkit"></i> Allergies</h1>
	            </c:if>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table id="allergy_table" class="table table-striped table-bordered table-hover display">
                                <thead>
                                    <tr>	
										<th>Substance</th>
										<th>Reaction</th>
										<th>Severity</th>
										<th>Observed Date</th>
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

		$('#allergy_table').DataTable( {
	        ajax: {
	            url: "get/history/allergies?pid=${pid}"
	        },
	        columns: [
	            { data: "substance" },
	            { data: "reaction" },
	            { data: "severity" },
	            { data: "date" }
	        ],
	        order: [ 2, 'desc' ],
	        pageLength: 10,
	        fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
                if ( aData.severity == "Serious" ||  aData.severity== "Severe")
                {
                    $('td', nRow).css('background-color', '#f2dede');
                }
                else if ( aData.severity== "Moderate" )
                {
                    $('td', nRow).css('background-color', '#fcf8e3');
                }
            }
	    });

	});
	</script>
</body>
</html>