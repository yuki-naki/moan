function cleanFadeOut(){
	$("#black_background").attr('class', 'invisible');
	$("#registerDiv").attr('class', 'invisible');
	$("#loginDiv").attr('class', 'invisible');
	$(".loginFormInput").val("");
	$(".error").empty();
}
function formFadeOut(){
	$('#black_background').fadeOut();
	$('#registerDiv').fadeOut().promise().done(function() {cleanFadeOut(); });
	$('#loginDiv').fadeOut().promise().done(function() {cleanFadeOut(); });
}
$("#registerLink").click(function(){
	$('#black_background').fadeIn();
	$('#registerDiv').fadeIn().promise().done(function() {
		$("#black_background").attr('class', 'visible');
		$("#registerDiv").attr('class', 'visible');
	});
});
$("#loginLink").click(function(){
	$('#black_background').fadeIn();
	$('#loginDiv').fadeIn().promise().done(function() {
		$("#black_background").attr('class', 'visible');
		$("#loginDiv").attr('class', 'visible');
	});
});
$("#black_background").click(function(){
	formFadeOut();
});
$(".close").click(function(){
	formFadeOut();
});
$("#newThreadBtn").click(function(){
	if ($(this).hasClass('disabled')) {
        $("#newThreadBtnError").css("display","inline-block");
    }
	else {
    	$("#newThreadTable").css("display","block");
    	window.scrollTo(0,document.body.scrollHeight);
    }
});
$("#newCommentBtn").click(function(){
	if ($(this).hasClass('disabled')) {
        $("#newCommentBtnError").css("display","inline-block");
    }
});