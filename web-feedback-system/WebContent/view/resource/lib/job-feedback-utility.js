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

function myCollapse(xxx) {
	$("#full-info" + xxx).slideToggle("slow");
	if ($("#short-description" + xxx).is(":visible"))
		$("#short-description" + xxx).hide();
	else
		$("#short-description" + xxx).show();
}

/**
 * show tips when hover to any star
 * 
 * @param id
 * @param star
 */
function mouseOverRating(id, star) {
	for (var i = 1; i <= star; i++) {
		$("#" + id + "_" + i).css("color", "#F9D400");
	}
	for (var i = star + 1; i < 6; i++) {
		$("#" + id + "_" + i).css("color", "#D9EDF7");
	}
	switch (star) {
	case 1:
		$("#tip_" + id).text("Rất không phù hợp");
		break;
	case 2:
		$("#tip_" + id).text("Không phù hợp");
		break;
	case 3:
		$("#tip_" + id).text("Có thể chấp nhận");
		break;
	case 4:
		$("#tip_" + id).text("Khá phù hợp");
		break;
	case 5:
		$("#tip_" + id).text("Rất phù hợp");
		break;
	default:
		$("#tip_" + id).text("");
		break;
	}
}

function mouseOutRating(id) {	
	for (var i = 1; i <= 5; i++) {
		if ($("#" + id + "_" + i).attr("value") === "1")
			$("#" + id + "_" + i).css("color", "#F9D400");
		else
			$("#" + id + "_" + i).css("color", "#D9EDF7");
	}
	$("#tip_" + id).text("");
}

/**
 * process when user click like button
 * 
 * @param obj
 * @param xxx
 */
function likeClick(obj, jobId) {
	var _rating = 0;
	for (var i = 1; i < 6; i++) {
		if ($("#" + jobId + "_" + i).attr("value") === "1")
			_rating = i;
		else
			break;
	}
	if (($(obj).attr("value")) === "0") {
		$.ajax({
			type : "POST",
			url : "ControllerJobRecommended",
			data : {
				saveClick : "save",
				saved : "1",
				jobId : jobId,
				rating : _rating
			}
		});
		$(obj).css("color", "#5890ff");
		$(obj).attr("value", "1");
	} else {
		$.ajax({
			type : "POST",
			url : "ControllerJobRecommended",
			data : {
				saveClick : "save",
				saved : "0",
				jobId : jobId,
				rating : _rating
			}
		});
		$(obj).css("color", "#9197a3");
		$(obj).attr("value", "0");
	}
}

//function dislikeClick(xxx) {
//
//	$("#panel" + xxx)
//			.find('.panel-footer')
//			.html(
//					"<p>Cảm ơn bạn đã phản hồi.</p>"
//							+ " <p>Những phản hồi của bạn sẽ giúp chúng tôi đưa ra những gợi ý việc làm chính xác hơn cho bạn!</p>");
//
//	$("#panel" + xxx).delay(3000).slideUp(3000);
//	$.ajax({
//		type : "POST",
//		url : "ControllerJobRecommended",
//		data : {
//			saved : "-1",
//			index : xxx
//		}
//	});
//}

/**
 * rating
 * 
 * @param obj
 * @param rating_star
 * @param jobId
 */
function rating(obj, rating_star, jobId) {
	for (var i = 1; i <= rating_star; i++) {
		$("#" + jobId + "_" + i).css("color", "#F9D400");
		$("#" + jobId + "_" + i).attr("value", "1");
	}
	for (var i = rating_star + 1; i < 6; i++) {
		$("#" + jobId + "_" + i).css("color", "#D9EDF7");
		$("#" + jobId + "_" + i).attr("value", "0");
	}
	$.ajax({
		type : "POST",
		url : "ControllerJobRecommended",
		data : {
			rateClick : "rate",
			rating : rating_star,
			jobId : jobId,
			saved : $("#" + jobId).attr("value")
		},
		success : function(data) {
			$.notify("Bạn đã đánh giá " + data + " công việc!", "success", {position:"right middle"});
		}
	});
}

// load job when ready
$(document).ready(function() {
	{
		$.ajax({
			type : "POST",
			url : "ControllerJobRecommended",
			data : {
				ready : "ready"
			},
			success : function(data) {
				$("#content-wrapper").append(data);
			}
		});
	}
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