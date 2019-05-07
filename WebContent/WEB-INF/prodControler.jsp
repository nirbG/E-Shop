<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>E-SHop</title>
</head>
<body>

<div class="topnav">
  <a class="active" href="/E-Shop/Log/Admin/ListModProd">Retour</a>
  <%
  	out.println((String) request.getAttribute("button"));
  %>
</div>
	<div style="margin:2% 5%">
		<h1> Modifier produit :</h1>
		<div class="wrapper">
			<div></div>
			<div>
				<div id="error"></div>
				<div id="mess" style="color : green"></div>
				<input name="action" type="hidden" value="1">
				<%out.println(request.getAttribute("id"));%>
				<p>Nom* :</p>
				<input id="nom" type="text" name="nom"  value="<% out.println( (String) request.getAttribute("nom")); %>">
				<p>Decription* :</p>
				<textarea id="description" name="description" rows="5" cols="33">
					<% out.println( (String) request.getAttribute("description")); %>
				</textarea>
				<p>prix * :</p>
				<% out.println(request.getAttribute("prix")); %>
				<p></p>
				<div id="modifier" class="btn" style="width:100%" >modifier</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function valider(){
		console.log("a");

		var id = document.getElementById("id");
		var nom = document.getElementById("nom");
		var description = document.getElementById("description");
		var prix = document.getElementById("prix");
		var iderror=0;
		var bool=true;

		if(nom.value==""){
			bool=false;
			iderror=5;
		}
		if(prix.value==""||prix.value<0){
			bool=false;
			iderror=8;
		}
		if(!bool){
			var url = "LogError";
			if (window.XMLHttpRequest) {
				requete = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				requete = new ActiveXObject("Microsoft.XMLHTTP");
			}
			requete.open("POST", url, true);
			requete.setRequestHeader("Content-type", "application/x-www-form-urlencoded;");
			requete.send('idError='+iderror);
			requete.onreadystatechange =majIHM;
			
		
		}else{
		var url = "../../Log/Admin/ProdControler";
		if (window.XMLHttpRequest) {
			requete = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requete = new ActiveXObject("Microsoft.XMLHTTP");
		}
		requete.open("POST", url, true);
		requete.setRequestHeader("Content-type", "application/x-www-form-urlencoded; ");
		requete.send("action=1&id="+id.value+"&nom="+nom.value+"&description="+description.value+"&prix="+prix.value+"");
		requete.onreadystatechange = function(event) {
			var message = ""; 
			if(requete.readyState==4){
				if (requete.status == 200) {
					document.getElementById("mess").innerHTML = "le produit a été modifier";

					setTimeout(function() {
					  document.getElementById("mess").innerHTML = "";
					},2000);
				}else{
					alert('Une erreur est survenue lors de la mise à jour de la page');
				}
			}
		};
		}
	}
	
	document.getElementById("modifier").addEventListener('click', function(e) {
		valider();
	});
</script>
<style>
form{
margin: 25% 2%;
}
input,button{
width: 100%;
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
/* Style the top navigation bar */
.topnav {
  overflow: hidden;
  background-color: #333;
}

/* Style the topnav links */
.topnav a {
  float: right;;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

.topnav .url {
  float: left;
}

/* Change color on hover */
.topnav a:hover {
  background-color: #ddd;
  color: black;
}
/* On screens that are 992px or less, set the background color to blue */
@media screen and (max-width: 992px) {
.prod {
	height: 110px;
	border: 2px solid black;
  	border-radius: 5px;
  	background-color: #c0c0c0;
	 color: black;
	width: 45%;
	display: inline-block;
	margin: 1%;
}

  form{
  text-align:center;
  }
    .wrapper {
  	display: grid;
  	grid-template-columns: repeat(1, 1fr);
  }
    .btn {
  	 font-size: 14px;
  	 padding-left: 0%;
  	 padding-right: 0%;
  }
  h3{
	font-size: 15px;
}

  
}

/* On screens that are 600px or less, set the background color to olive */
@media screen and (max-width: 600px) {
.prod {
	height: 110px;
	border: 2px solid black;
  	border-radius: 5px;
  	background-color: #c0c0c0;
	 color: black;
	width: 100%;
	display: inline-block;
}

    .topnav a {
    float: none;
    width: 100%;
  }
  .wrapper {
  	display: grid;
  	grid-template-columns: repeat(1, 1fr);
  }
    .btn {
  	 font-size: 10px;
  }
  h3{
	font-size: 16px;
}
</style>
</html>