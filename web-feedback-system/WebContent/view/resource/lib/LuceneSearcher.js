/**
 * this is lucene searcher tool make by anh tuyen pro
 */
var servlet = "LuceneServlet";
var input_name = "search_lucene"
var wrapper_class = ""
/**
 * function draw form search on page
 */
function init(){
	$("." + wrapper_class).html("<form action = '"+servlet+"' method = 'get' class = 'frm_search_lucene form-inline'>" +
			"<input style='width:90%' class = 'form-control in_search_lucene' name = '"+input_name+"' type='text' width='300px'>" +
					"<button type='submit' class = 'form-control btn btn-primary btn_search_lucene'>Search</button></form>");   
};


(function($) {
	
    $.fn.LuceneSearch = function( options ) {    	
        // Establish our default settings
        var settings = $.extend({
            _server_      : 'LuceneServlet',
            _input_name_	  :  'search_lucene'            
        }, options);        
        servlet = settings._server_;
        input_name = settings._input_name_;
        wrapper_class = this.attr("class");
        init();
    }
    

}(jQuery));