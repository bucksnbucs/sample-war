<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=us-ascii">
<title>Health At-A-Glance</title>
<jsp:include page="/WEB-INF/views/head.jsp" />

<script src='https://connect.humanapi.co/connect.js'></script>

</head>
<body>

	<jsp:include page="/WEB-INF/views/navigation.jsp" />
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header"><i class="glyphicon glyphicon-zoom-in"></i> Patient Health At-A-Glance</h1>
			</div>
		</div>

        <!-- /.row -->
        <div class="row">
            <div class="col-lg-5">
                <div class="panel panel-default">
		            <div class="panel-heading">
		                Patient Basic Info
		            </div>	
		            <div class="panel-body">
		                <table class="table table-condensed">
		                	<tr>
		                		<th>First Name</th>
		                		<td>${patient.firstName}</td>
		                	</tr>
		                	<tr>
		                		<th>Last Name</th>
		                		<td>${patient.lastName}</td>
		                	</tr>
		                	<tr>
		                		<th>Sex</th>
		                		<td>${patient.gender}</td>
		                	</tr>
		                	<tr>
		                		<th>Race</th>
		                		<td>${patient.race}</td>
		                	</tr>
		                	<tr>
		                		<th>Marital Status</th>
		                		<td>${patient.maritalStatus}</td>
		                	</tr>
		                	<tr>
		                		<th>Birthdate</th>
		                		<td>${patient.birthdate}</td>
		                	</tr>
		                	<tr>
		                		<th>Height</th>
		                		<td>${patient.height}</td>
		                	</tr>
						</table>
		            </div>
                </div>
            </div>
            <div class="col-lg-7">
                <div class="panel panel-default">
		            <div class="panel-heading">
		                Patient Health-at-a-Glance
		            </div>	
		            <div class="panel-body">
		                <table class="table table-condensed">
		                	<tr>
		                		<th>Smoking Status</th>
		                		<td>${patient.smoker}</td>
		                	</tr>
		                	<tr>
		                		<th>Drinking Status</th>
		                		<td>${patient.drinker}</td>
		                	</tr>
		                	<tr>
		                		<th>Known Medical Conditions</th>
		                		<td>${patient.conditions}</td>
		                	</tr>
		                	<tr>
		                		<th>Past surgeries</th>
		                		<td>${patient.surgeries}</td>
		                	</tr>
		                	<tr>
		                		<th>Medications</th>
		                		<td>${patient.medicationList}</td>
		                	</tr>
		                	<tr>
		                		<th>Allergies</th>
		                		<td>${patient.allergies}</td>
		                	</tr>
		                </table>
					</div>
				</div>
            </div>

        </div>
                <!-- /.row -->
        <div class="row">
            <div class="col-lg-5">
                <div class="panel panel-default">
		            <div class="panel-heading">
		                Providers (Click for Appointment History)
		            </div>	
		            <div class="panel-body" >
		                		<c:forEach items="${patient.providerList}" var="provider" varStatus="myCount">
		                		<td><a id="provider_${myCount.count}" href="">${provider}</a></td>
		                		<c:if test="${myCount.count < fn:length(patient.providerList)}">
		                		&nbsp;&nbsp;|&nbsp;&nbsp;
		                		</c:if>
		                		</c:forEach>
		            </div>
                </div>
            </div>
        </div>	
        
        <div id="dialog1" style="display:none;" width="100%" title="Appointment Details">
			<div class="panel panel-default" style="width:20%; float:left;" align="center">
		            <div class="panel-heading">
		                Appointment Date
		            </div>	
		            <div class="panel-body">
		            <table id="dates_table" class="table table-condensed">
		            
		            </table>
					</div>
				</div>
			<div class="panel panel-default" style="width:78%; float:right">
			<div class="panel-heading">
		                Patient Detail
		            </div>	
		            <div class="panel-body">
		                <table id="appt_table" class="table table-condensed">
		                	
		                </table>
					</div>
		</div>
 	</div>

	<jsp:include page="/WEB-INF/views/footer.jsp" />
	
		<script type="text/javascript">
		
	$(document).ready(function() {
		
		var obsDates = ${obsDates};
		var providerList = ${providerList};
		var obsMap = ${obsMap};
		var ids = new Array();;
		
		for (var i = 0; i < providerList.length; i++)
			{
				var id = "#provider_" + (i+1);
				$(id).click(function(event) {
					event.preventDefault();
					$("dates_table").html('');
					$("#appt_table").html('<tr><th><b>Select a date for appointment details.</b></th></td>');
					
					var splits = this.id.split('_');
					var index = splits[1];					
					
					var html = '';
					for (var j = index - 1; j < obsDates.length; j+=providerList.length)
						{
							var pindex = index-1;
						    var rowid = "appt_" + pindex + "_" + j;
							html = html + '<tr><td><a id="'+ rowid + '" href="">'+obsDates[j]+'</a></td></tr>';
							ids.push(rowid);
						}
					$("#dates_table").html(html);
					
					$("#dialog1").dialog({
			            maxWidth:900,
			            maxHeight:500,
			            width: 900,
			            height: 500
			    	});
					
					for (var j = 0; j < ids.length; j++)
					{
						var rowid = "#" + ids[j];
						
						$(rowid).click(function(event) {
							event.preventDefault();
							var html = '';
							var splits = this.id.split('_');
							var pindex = splits[1];
							var date = $(this).html();
							$("#appt_table").html('');
							var appts = obsMap[date];
							html = html + '<tr><th>Provider</th><td>'+providerList[pindex]+'</td></tr>';
							html = html + '<tr><th>Date</th><td>'+date+'</td></tr>';
							for(var k = 0; k < appts.length; k++)
								{
									var appt = appts[k];
									if(appt["units"] != null) 
										html = html + '<tr><th>'+appt["type"]+'</th><td>' + appt["value"] + ' ' + appt["units"]+'</td></tr>';
									else
										html = html + '<tr><th>'+appt["type"]+'</th><td>' + appt["value"] + '</td></tr>';	
								}
							$("#appt_table").html(html);
						});
					}
				});
			}

	} );
	
	</script>
	
</body>
</html>