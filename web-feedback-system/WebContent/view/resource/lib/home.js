/**
 * set fixed notification bar
 */
$(document).ready(function() {
	$(document).scroll(function() {
		if ($(window).scrollTop() > 200)
			$("#tt").addClass("navbar-fixed-bottom");
		else
			$("#tt").removeClass("navbar-fixed-bottom");
	});
});

/**
 * show loading icon when loading data
 */
$(document).ajaxStart(function() {
	$("#loading").show();
});

/**
 * hide loading icon when data load completely
 */
$(document).ajaxStop(function() {
	$("#loading").hide();
});

/**
 * function go to top
 */
$(document).ready(function() {
	var offset = 250, // At what pixels show Back to Top Button
	scrollDuration = 300; // Duration of scrolling to top
	$(window).scroll(function() {
		if ($(this).scrollTop() > offset) {
			$('.go-to-top').fadeIn(500);
		} else {
			$('.go-to-top').fadeOut(500);
		}
	});

	// Smooth animation when scrolling
	$('.go-to-top').click(function(event) {
		event.preventDefault();
		$('html, body').animate({
			scrollTop : 0
		}, scrollDuration);
	})
});
