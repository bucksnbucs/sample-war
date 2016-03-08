<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=us-ascii">
	<title>Documents</title>
	<jsp:include page="/WEB-INF/views/head.jsp"/>
	<!-- Jasny Bootstrap CSS -->
	<link href="resources/bower_components/jasny-bootstrap/css/jasny-bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/navigation.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
	            <c:if test="${pageContext.request.userPrincipal.name == 'doctor'}"> 
                	<h1><i class="fa fa-file-text"></i> Documents </h1>
					<h3>Patient: ${patient_name}</h3>
					<hr />
				</c:if>
	            <c:if test="${pageContext.request.userPrincipal.name != 'doctor'}"> 
	            	<h1 class="page-header"><i class="fa fa-file-text"></i> Documents </h1>
	            </c:if>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="modal fade bs-example-modal-lg" id="documentsModal" tabindex="-1" role="dialog" aria-hidden="true">
						  <div class="modal-dialog modal-lg">
						    <div class="modal-content">
						      <img src="" style="width: 100%;">
						    </div>
						  </div>
						</div>
						<div class="col-xs-6 col-md-3">
					      <a href="#" class="thumbnail" data-toggle="modal" data-target="#documentsModal" 
					      data-src="http://www.provendiagnostics.com/patients/sample_report.gif">
					        <img src="http://www.provendiagnostics.com/patients/sample_report.gif" style="height: 160px;">
					      </a>
					    </div>
						<div class="col-xs-6 col-md-3">
					      <a href="#" class="thumbnail" data-toggle="modal" data-target="#documentsModal" 
					      data-src="http://coalese.com/images/Raven_PDF_Summary.jpg">
							<img src="http://coalese.com/images/Raven_PDF_Summary.jpg" style="height: 160px;">
					      </a>
					    </div>
						<div class="col-xs-6 col-md-3">
					      <a href="#" class="thumbnail" data-toggle="modal" data-target="#documentsModal" 
					      data-src="https://emcow.files.wordpress.com/2013/07/cavitary-mass.jpg">
					        <img src="https://emcow.files.wordpress.com/2013/07/cavitary-mass.jpg" style="height: 160px;">
					      </a>
					    </div>
						<div class="col-xs-6 col-md-3">
					      <a href="#" class="thumbnail" data-toggle="modal" data-target="#documentsModal" 
					      data-src="https://emcow.files.wordpress.com/2012/10/dlfns1-1.png">
							<img src="https://emcow.files.wordpress.com/2012/10/dlfns1-1.png" style="height: 160px;">
					      </a>
					    </div>
					    <div class="col-xs-6 col-md-3">
							<div class="fileinput fileinput-new" data-provides="fileinput" style="width: 100%;">
								<div style="margin-bottom: 5px;">
									<span class="btn btn-primary btn-file">
										<span class="fileinput-new">Select Document</span>
										<span class="fileinput-exists">Upload</span>
										<input type="file" name="...">
									</span>
									<a href="#" class="btn btn-primary fileinput-exists" data-dismiss="fileinput">Delete</a>
								</div>
								<div class="fileinput-preview thumbnail"
									data-trigger="fileinput" style="width: 100%; height: 160px;"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->
	</div>

	<jsp:include page="/WEB-INF/views/footer.jsp"/>
    <!-- Jasny Bootstrap JavaScript -->
    <script src="resources/bower_components/jasny-bootstrap/js/jasny-bootstrap.min.js"></script>

	<script type="text/javascript">

	$('#documentsModal').on('show.bs.modal', function (event) {
		  var element = $(event.relatedTarget), // element that triggered the modal
		  src = element.data('src'), // Extract info from data-* attributes
		  modal = $(this);
		  modal.find('.modal-content img').attr("src", src);
		});

	</script>

</body>
</html>