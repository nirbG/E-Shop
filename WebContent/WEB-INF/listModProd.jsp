<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Shop</title>
</head>
<body>
</head>
<body>

<ul>
  <li><a class="active" href="/E-Shop/Log/Admin">Retour</a></li>
  <li style="float:right;display:inline-block" >
    <%
  	out.println((String) request.getAttribute("button"));
  %>
  </li>
</ul>

<div style="margin:2% 5%">
	<h1>Modifier ou supprimer les produits:</h1>
	<div class="wrapper">
  		<% 
  		 out.println((String) request.getAttribute("prods"));
	  %>
	</div>
</div>

</body>

<script>

function supp(prod){
	
	var url = "../../Log/Admin/ProdControler";
	if (window.XMLHttpRequest) {
		requete = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		requete = new ActiveXObject("Microsoft.XMLHTTP");
	}
	requete.open("POST", url, true);
	requete.setRequestHeader("Content-type", "application/x-www-form-urlencoded; ");
	requete.send('action=2&id='+prod.getAttribute("id"));
	requete.onreadystatechange = function(event) {
		var message = ""; 
		if(requete.readyState==4){
			if (requete.status == 200) {
				console.log("supp");
				prod.remove();
			}else{
				alert('Une erreur est survenue lors de la mise à jour de la page');
			}
		}
	};
	
}
	window.addEventListener('load', function(e) {
	
		var classname=document.getElementsByClassName("suppProd");
		for (var i = 0; i < classname.length; i++) {
			classname[i].addEventListener('click', function(e) {
				var prod=(((e.target.parentNode).parentNode).parentNode).parentNode;
				supp(prod)
			});
		}
	});
</script>
<style>
.prod {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
	height:110px;
}
.prod div:nth-child(1) {	
	grid-column: 1;
	grid-row: 1;
}
.prod div:nth-child(2) {
	grid-column: 2;
	grid-row: 1;
	grid-column-end: 5;
}
.wrapper {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  column-gap: 10px;
  row-gap: 1em;

}
* {box-sizing: border-box;}

.wrapper {
  grid-column-gap: 10px;
  grid-row-gap: 1em;

  /*border: 2px solid #f76707;*/
  border-radius: 5px;
  background-color: #ffff;
}

.wrapper > div {
  border: 2px solid black;
  border-radius: 5px;
  background-color: #c0c0c0;
  padding: 0.5em;
  color: black;
}
.prix {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-auto-rows: 20px;
}
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: #111;
}

.active {
  background-color: #c0c0c0 ;
}
.btn{
  background-color: #555555;
  border: none;
  color: white;
  padding: 7px 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
   border: 2px solid #555555; 
  }
    a{
  	text-decoration: none !important;
	color: white;
}
</style>
</html>