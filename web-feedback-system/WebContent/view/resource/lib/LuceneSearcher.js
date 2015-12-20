/**
 * this is lucene searcher tool make by anh tuyen pro
 */
var servlet = "LuceneServlet";

/**
 * function draw form search on page
 */
$(document).ready(function(){
	$(".form_search_lucene").html("<form action = '"+servlet+"' method = 'get' class = 'frm_search_lucene form-inline'>" +
			"<input style='width:90%' class = 'form-control in_search_lucene' name = 'search_lucene' type='text' width='300px'>" +
					"<button type='submit' class = 'form-control btn btn-primary btn_search_lucene'>Search</button></form>");   
});



/**
 * function send user input to server searching
 */

//$(document).ready(function(){
//	$(".frm_search_lucene").submit(function(e){
//		e.preventDefault();
//		$.ajax({
//			type : "POST",
//			url : servlet,
//			data : {
//				search_lucene : $(".in_search_lucene").val()
//			},
//			success : function(data) {
//				$("#results_search_lucene").html(data);
//			}
//		});
//	})
//});


(function($) {
    $.fn.LuceneSearch = function( options ) {

        // Establish our default settings
        var settings = $.extend({
            _server_      : 'LuceneServlet',
            _submit_	  :  ''	
        }, options);        
        servlet = settings._server_;
        
    }

}(jQuery));