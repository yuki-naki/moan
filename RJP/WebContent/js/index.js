function cleanFadeOut(){
	$("#black_background").attr('class', 'invisible');
	$("#registerDiv").attr('class', 'invisible centerDiv');
	$("#loginDiv").attr('class', 'invisible centerDiv');
	$(".loginFormInput").val("");
	$(".error").empty();
}
function formFadeOut(){
	$('#black_background').fadeOut();
	$('#registerDiv').fadeOut().promise().done(function() {cleanFadeOut(); });
	$('#loginDiv').fadeOut().promise().done(function() {cleanFadeOut(); });
}
function disableBackButton(){
	window.history.forward();
}
$("#registerLink").click(function(){
	$('#black_background').fadeIn();
	setTimeout(function(){ $("#registerDiv").attr('class', 'centerDiv'); }, 0.5);
	$('#registerDiv').fadeIn().promise().done(function() {
		$("#black_background").attr('class', 'visible');
		$("#registerDiv").attr('class', 'visible centerDiv');
	});
});
$("#loginLink").click(function(){
	$('#black_background').fadeIn();
	setTimeout(function(){ $("#loginDiv").attr('class', 'centerDiv'); }, 0.5);
	$('#loginDiv').fadeIn().promise().done(function() {
		$("#black_background").attr('class', 'visible');
		$("#loginDiv").attr('class', 'visible centerDiv');
	});
});
$("#logout").click(function(){
	$(".li").css("margin-left","0px");
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
    	$("#submitNewThreadDiv").css("display","block");
    	window.scrollTo(0,document.body.scrollHeight);
    }
});
$("#newCommentBtn").click(function(){
	if ($(this).hasClass('disabled')) {
        $("#newCommentBtnError").css("display","inline-block");
    }
});
$("#updateCommentBtn").click(function(){
    window.scrollTo(0,document.body.scrollHeight);
    $("#newCommentBtn").removeAttr("disabled");
    var str = $(this).data("content").replace(/<br>\n/g,"\n");
    str = str.replace(/<br>/g,"");
    $("#textareaComment").val(str);
    $("#newCommentBtn").attr("name","updateComment");
});
$("#go_top").click(function(){
	$('html').animate({scrollTop:0}, 600);
});
$(window).scroll(function(){
	scrtop = $(this).scrollTop();
	if(scrtop >= 150){
		$('#go_top').fadeIn();
	}
	else{ $('#go_top').fadeOut(); }
});