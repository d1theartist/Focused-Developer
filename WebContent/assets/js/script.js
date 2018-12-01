$(function(){
	//href=${pageContext.request.contextPath}/home?page=home
	var pageName = window.location.href.substr(window.location.href.lastIndexOf("/")+1);
	var linkLength = pageName.length;

	//console.log("pageNAME :" +pageName+" length("+linkLength+")");
	
	var x = document.links.length;
	for(i=0; i<x; i++){
		var currentLink = document.links[i];
		var hrefLength = currentLink.href.length;
		var hrefSub = currentLink.href.substr(-linkLength);
		//console.log('current Link: '+currentLink.href+" length("+hrefLength+")");
		//console.log('current href: '+hrefSub);
		//console.log('current pageName slice: '+pageName.slice(0,8));
		
		if(hrefSub === pageName){	
			currentLink.className += " selected";
			
		}else{
			// split the string of class names into an array
			currentLink.className = currentLink.className.split(/\s+/).map(function(val){
				// if the string passed to the map function is equal to "selected"
						// make it equal to an empty string
						// else keep it the same
				return val === "active" ? "" : val;
			}).join(" ");
					
		}

	}
	
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