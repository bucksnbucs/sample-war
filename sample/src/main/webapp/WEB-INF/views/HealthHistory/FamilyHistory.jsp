<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=us-ascii">
	<title>Family History</title>
	<jsp:include page="/WEB-INF/views/head.jsp"/>
	<style>
		/*Now the CSS*/
		* {margin: 0; padding: 0;}

		.tree ul {
			padding-top: 20px; position: relative;
			
			transition: all 0.5s;
			-webkit-transition: all 0.5s;
			-moz-transition: all 0.5s;
		}

		.tree li {
			float: left; text-align: center;
			list-style-type: none;
			position: relative;
			padding: 20px 5px 0 5px;
			
			transition: all 0.5s;
			-webkit-transition: all 0.5s;
			-moz-transition: all 0.5s;
		}

		/*We will use ::before and ::after to draw the connectors*/

		.tree li::before, .tree li::after{
			content: '';
			position: absolute; top: 0; right: 50%;
			border-top: 1px solid #ccc;
			width: 50%; height: 20px;
		}
		.tree li::after{
			right: auto; left: 50%;
			border-left: 1px solid #ccc;
		}

		/*We need to remove left-right connectors from elements without 
		any siblings*/
		.tree li:only-child::after, .tree li:only-child::before {
			display: none;
		}

		/*Remove space from the top of single children*/
		.tree li:only-child{ padding-top: 0;}

		/*Remove left connector from first child and 
		right connector from last child*/
		.tree li:first-child::before, .tree li:last-child::after{
			border: 0 none;
		}
		/*Adding back the vertical connector to the last nodes*/
		.tree li:last-child::before{
			border-right: 1px solid #ccc;
			border-radius: 0 5px 0 0;
			-webkit-border-radius: 0 5px 0 0;
			-moz-border-radius: 0 5px 0 0;
		}
		.tree li:first-child::after{
			border-radius: 5px 0 0 0;
			-webkit-border-radius: 5px 0 0 0;
			-moz-border-radius: 5px 0 0 0;
		}

		/*Time to add downward connectors from parents*/
		.tree ul ul::before{
			content: '';
			position: absolute; top: 0; left: 50%;
			border-left: 1px solid #ccc;
			width: 0; height: 20px;
		}

		.tree li a{
			border: 1px solid #ccc;
			padding: 5px 10px;
			text-decoration: none;
			color: #666;
			font-family: arial, verdana, tahoma;
			font-size: 14px;
			display: inline-block;
			
			border-radius: 5px;
			-webkit-border-radius: 5px;
			-moz-border-radius: 5px;
			
			transition: all 0.5s;
			-webkit-transition: all 0.5s;
			-moz-transition: all 0.5s;
		}

		/*Time for some hover effects*/
		/*We will apply the hover effect the the lineage of the element also*/
		.tree li a:hover{
			background: #c8e4f8; color: #000; border: 1px solid #94a0b4;
		}

	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/navigation.jsp"/>
    <div id="page-wrapper">
    	<div class="row">
    		<div class="col-lg-12">
				<!--
				We will create a family tree using just CSS(3)
				The markup will be simple nested lists
				-->

    		</div>
    	</div>
        <div class="row">
            <div class="col-lg-12">
	            <c:if test="${pageContext.request.userPrincipal.name == 'doctor'}"> 
                	<h1><i class="fa fa-group"></i> Family History</h1>
					<h3>Patient: ${patient_name}</h3>
					<hr />
				</c:if>
	            <c:if test="${pageContext.request.userPrincipal.name != 'doctor'}"> 
	            	<h1 class="page-header"><i class="fa fa-group"></i> Family History</h1>
	            </c:if>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <ul class="nav nav-tabs">
                            <!-- <li class="active"><a href="#analysis" data-toggle="tab">Analysis</a>
                            </li> -->
                            <li class="active"><a href="#data" data-toggle="tab">Data</a>
                            </li>
                            <li><a href="#graphs" data-toggle="tab">Family Tree (mock-up)</a>
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
                            <div class="tab-pane fade in active" id="data">
                            	<br />
		                        <div class="dataTable_wrapper">
		                            <table id="familyhistory_table" class="table table-striped table-bordered table-hover display">
		                                <thead>
		                                    <tr>	
												<th>Relation</th>
												<th>Condition</th>
												<th>Onset Date</th>
												<th>Onset Age</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
	                        <!-- Tab panes -->
    	                    <div class="tab-pane fade in" id="graphs">
    	                    	<br />
								<div class="tree" id="tree" >

								</div>
                	        </div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->
		 
		 <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" id="family_details">
		    <div class="modal-dialog modal-sm">
		      <div class="modal-content">

		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		          <h4 class="modal-title" id="mySmallModalLabel"><div id="relationship">Brother</div></h4>
		        </div>
		        <div class="modal-body">
		        	<p>
			          <strong>Condition:</strong>  Type 2 Diabetes<br />
			          <strong>Onset Date:</strong>  05-13-2001<br />		          
			          <strong>Onset Age:</strong>  35<br />	
		          	</p>
		        </div>
		      </div><!-- /.modal-content -->
		    </div><!-- /.modal-dialog -->
		 </div><!-- /.modal -->

	</div>

	<jsp:include page="/WEB-INF/views/footer.jsp"/>

	<script type="text/javascript">

	$(document).ready(function() {
		$.ajax({
			url : 'get/family_tree',
			success : function(data) {
				$('#tree').html(data);
			}
		});
		
		$('#familyhistory_table').DataTable( {
	        ajax: {
	            url: "get/history/familyhistory"
	        },
	        columns: [
				{ data: "relation" },
	            { data: "condition" },
	            { data: "onsetDate" },
	            { data: "onsetAge"}
	        ],
	        order: [ 0, 'desc' ],
	        pageLength: 10
	    });

	});
	
	</script>
</body>
</html>