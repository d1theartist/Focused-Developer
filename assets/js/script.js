$(function(){
	var pageName = window.location.href.substr(window.location.href.lastIndexOf("/")+1);
	console.log(pageName);
	$('#header ul li a[href^="' + pageName + '"]').addClass('selected');
})

function changeVisible(elementID){
	console.log("in changeVisible "+elementID);
	document.getElementById(elementID).classList.toggle("hidden")
	/*if(document.getElementsByClassName(elementID).classList.contains("hidden") ){
		document.getElementsByClassName(elementID).className = "show";
	}else{
		document.getElementsByClassName(elementID).className = "hidden";
	}*/
}

// $("nav li ol").hide();