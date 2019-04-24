<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>E-Shop</title>
</head>
<body>
<ul>
  <li><a class="active" href="/E-Shop/">E-Shop</a></li>
  <li><a href="/E-Shop/ListProducts">Nos produits</a></li>
  <li style="float:right;display:inline-block" >
  <%
  	out.println((String) request.getAttribute("button"));
  %>
  </li>
</ul>
	<div style="margin:2% 5%">
		<div class="wrapper">
		<div></div>
			<div>
				<h1> mod users</h1>
				<div id="error"></div>
				<form action="/E-Shop/ModInfo" method="POST" onsubmit="return valider()">
					<input name="action" type="hidden" value="2">
					<%out.println(request.getAttribute("id"));%>
					<p>Nom* :</p>
					<input id="nom" type="text" name="nom"  value="<% out.println( (String) request.getAttribute("nom")); %>">
					<p>Prenom* :</p>
					<input id="prenom" type="text" name="prenom" value="<% out.println( (String) request.getAttribute("prenom")); %>" >
					<p>Email* :</p>
					<input id="email" type="email" name="email" value="<% out.println( (String) request.getAttribute("email")); %>">
					<p></p>
					<input type="submit" value="modifier">
				</form>
			</div>
	</div>
</body>
<script type="text/javascript">
	function valider(){
		console.log("a");
		var nom = document.getElementById("nom");
		var prenom = document.getElementById("prenom");
		var conf = document.getElementById("conf");
		var password = document.getElementById("password");
		var email = document.getElementById("email");
		var iderror=0;
		var bool=true;

		if(email.value==""){
			bool=false;
			iderror=5;
		}
		if(conf.value!=password.value){
			bool=false;
			iderror=7;
		}
		if(password.value==""){
			bool=false;
			iderror=4;
		}
		if(prenom.value==""){
			bool=false;
			iderror=2;
		}
		if(nom.value==""){
			bool=false;
			iderror=1;
		}
		if(nom.value=="" && prenom.value==""&& conf.value==""&& password.value==""&& email.value==""){
			bool=false;
			iderror=6;
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
			return false;
		}
		return true;
	}
	function majIHM() {
		var message = ""; 
		if (requete.readyState == 4) {
			if (requete.status == 200) {
				document.getElementById("error").innerHTML = requete.responseText;
			}else{
				alert('Une erreur est survenue lors de la mise à jour de la page');
			}

		}
	}
	function maj() {
		
	}
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
</style>
</html>