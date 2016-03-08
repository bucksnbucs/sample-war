    <!-- jQuery -->
    <script src="resources/bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <!-- DataTables JavaScript -->

    <script src="resources/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="resources/dist/js/sb-admin-2.js"></script>
    
    <!-- Morris.js JavaScript dependencies-->
    <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
    
    <!-- Jquery UI .js dependencies -->
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

    <!-- Bootstrap Popover and Tooltip.js -->
    <script src="resources/bower_components/bootstrap/js/tooltip.js"></script>
    <script src="resources/bower_components/bootstrap/js/popover.js"></script>


    <script>
        var tooltip_enabled = false;
        $( document ).ready(function() {
            $('[data-toggle="tooltip"]').tooltip('enable');
            tooltip_enabled=true;
            $("#enable-tooltip").text('Disable Tooltips');
        });
        
        $('#help').click(function(){
            if (tooltip_enabled){
                $('[data-toggle="tooltip"]').tooltip('disable');
                tooltip_enabled=false;
                $("#enable-tooltip").text('Enable Tooltips');
            }
            else{
                $('[data-toggle="tooltip"]').tooltip('enable');
                tooltip_enabled=true;
                $("#enable-tooltip").text('Disable Tooltips');
            }
        });

    	$('#popupVideo').on('show.bs.modal', function (event) {
    		  var element = $(event.relatedTarget), // element that triggered the modal
    		  src = element.data('src'), // Extract info from data-* attributes
    		  modal = $(this);
    		  modal.find('.modal-content iframe').attr("src", src);
    		});

        $('#popupVideo').on('hidden.bs.modal', function () {
  		  var modal = $(this);
		  modal.find('.modal-content iframe').attr("src", '');
        });

    </script>