$(function(){
	var pageName = window.location.href.substr(window.location.href.lastIndexOf("/")+1);
	console.log(pageName);
	$('#header ul li a[href^="' + pageName + '"]').addClass('selected');
})