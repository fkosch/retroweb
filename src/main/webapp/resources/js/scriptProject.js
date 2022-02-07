	$(document).ready(function(){
	    $("#getProjects").click(function(){
	    	$.get("projects", function(projects){
	            createTable(projects);
	    	});
	    });
	});
	
	function createTable(projects) {
		var txt="<table border=\"1\"><tr><th>Projects</th><th>Is Active</th><th>created</th></tr>"
		var line="";
		$.each( projects, function( key, project ) {
		    line="<tr><td><a href=\"project?id="+project.id+"\">";
		    if( project.active === true ) {
		    	line+="<b>"+project.name+"</b>";
		    } else {
		    	line+="<em>"+project.name+"</em>";
		    }
		    line+="</a></td><td>"+project.active+"</td><td>"+project.created+"</td></tr>";
		    txt+=line;
		});
		txt+="</table>"
		$("#projects").html(txt);
	}
	
	function showProject(id) {
		$.get("projects/" + id, function(project){
			$("#project").html("<h2>Details of " + project.name + "</h2><p>" + project.active +"</p><p>" + project.created + "</p>");
    	});
	}
	
	function createProjectsMenu(projects) {
		var txt="<nav><ul class='nobullets'>"
		var item="";
		$.each( projects, function( key, project ) {
		    item="<li><a class='menu' href='#" + project.id + "' id='"+ project.id + "' onclick='showProject(" + project.id + ");setMenu(" + project.id + ")'>";
		    if( project.active === true ) {
		    	item+=project.name;
		    } else {
		    	item+=project.name;
		    }
		    item+="</a></li>";
		    txt+=item;
		});
		txt+="</nav>";
		$("#projects").html(txt);
		setMenu(0);
	}
	
	function setMenu(id) {
		$("ul").find("a.menu").css("color","black");
		$("ul").find("a.menu").css("font-weight","normal");
		$("ul").find("a.menu").css("text-decoration","none"); //this should be in css file
		$("ul.nobullets").css("list-style-type","none"); //this should be in css file
		$("#"+id).css("font-weight","bold");
	}