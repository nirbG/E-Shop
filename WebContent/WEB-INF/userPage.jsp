<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>E-Shop</title>
</head>
<body>
<ul>
  <li><a  href="/E-Shop/">E-Shop</a></li>
  <li><a  class="active" href="/E-Shop/ListProducts">Nos produits</a></li>
  <li style="float:right;display:inline-block" >
    <%
  	out.println((String) request.getAttribute("button"));
  %>
  </li>
</ul>

<div style="margin:2% 5%">
	<h1>Votre Compte</h1>
	<div class="wrapper">
  		<div>
  			<div id="error"></div>
  			<h1>Modifier vos infos:</h1>
			<form onsubmit="return valider()">
			<input type="hidden" value="1">
			<p>Nom* :</p>
			<input id="nom" type="text" name="nom" value="<% out.println( (String) request.getAttribute("nom")); %>" >
			<p>Prenom* :</p>
			<input id="prenom" type="text" name="prenom" value="<% out.println( (String) request.getAttribute("prenom")); %>" >
			<p>Email* :</p>
			<input id="email" type="email" name="email" value="<% out.println( (String) request.getAttribute("email")); %>" >
			<p></p>
			<input id="modinfo" type="submit" value="valider">
			</form>
  		</div>
  		<div>
			<a href="/E-Shop/Deco">Deconexion</a>
			<button>Modifier mot de passe</button>
  		</div>
  	</div>
</div>
</body>
<style>
.wrapper {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 10px;
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