<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>E-Shop</title>
</head>
<body>

<div class="topnav">
  <a class="url active" href="/E-Shop/">E-Shop</a>
  <a class="url" href="/E-Shop/ListProducts">Nos produits</a>
  <a class="url" href="/E-Shop/MyBasket">Votre panier</a>
  <%
  	out.println((String) request.getAttribute("button"));
  %>
</div>
<div style="margin:2% 5%">
	<h1>Votre Compte</h1>
	<div class="wrapper">
  		<div id="forminfo" >
  			<div id="error"></div>
  			<div id="mess" style="color:green"></div>
  			<h1 style='margin-top:0%'>Modifier vos infos:</h1>
			<input type="hidden" value="1">
			<p>Nom* :</p>
			<input id="nom" type="text" name="nom" value="<% out.println( (String) request.getAttribute("nom")); %>" >
			<p>Prenom* :</p>
			<input id="prenom" type="text" name="prenom" value="<% out.println( (String) request.getAttribute("prenom")); %>" >
			<p>Email* :</p>
			<input id="email" type="email" name="email" value="<% out.println( (String) request.getAttribute("email")); %>" >
			<p></p>
			<div id="modinfo" class="btn" style="width:100%" >valider</div>
  		</div>
  		<div class='wrapperb'>
  			<div style="width: 100%; margin-top:2% " class=""><a class='btn' style='width: 100%;' href="/E-Shop/Deco">Deconexion</a></div>
  			<div style="width: 100%; margin-top:2% " class=""><a class='btn' style='width: 100%;' href="/E-Shop/Log/ConfirmerMdp">Modifier mot de passe</a></div>
			<div style="width: 100%; margin-top:2% " class=""><a class='btn' style='width: 100%;' href="/E-Shop/Log/VoirPanier">Vos paniers</a></div>
		</div>
  	</div>
</div>
</body>
<script type="text/javascript">

function valider(){
	var nom = document.getElementById("nom");
	var prenom = document.getElementById("prenom");
	var email = document.getElementById("email");
	var iderror=0;
	var bool=true;
	if(nom.value==""){
		bool=false;
		iderror=1;
	}
	if(prenom.value==""){
		bool=false;
		iderror=2;
	}
	if(email.value==""){
		bool=false;
		iderror=5;
	}
	if(nom.value=="" && prenom.value=="" && email.value==""){
		bool=false;
		iderror=6;
	}
	if(!bool){
		var url = "../LogError";
		if (window.XMLHttpRequest) {
			requete = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requete = new ActiveXObject("Microsoft.XMLHTTP");
		}
		requete.open("POST", url, true);
		requete.setRequestHeader("Content-type", "application/x-www-form-urlencoded;");
		requete.send('idError='+iderror);
		requete.onreadystatechange =function(event) {
			console.log(requete.readyState +" :readyState");
			console.log(requete.status +" :status");
			var message = ""; 
			if(requete.readyState==4){
			if (requete.status == 200) {
				document.getElementById("error").innerHTML = requete.responseText;
			}else{
				alert('Une erreur est survenue lors de la mise à jour de la page');
			}
			}
		};
		return false;
	}else{
		var url = "ModInfo";
		if (window.XMLHttpRequest) {
			requete = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requete = new ActiveXObject("Microsoft.XMLHTTP");
		}
		requete.open("POST", url, true);
		requete.setRequestHeader("Content-type", "application/x-www-form-urlencoded; ");
		requete.send('action=1&nom='+nom.value+"&prenom="+prenom.value+"&email="+email.value);
		requete.onreadystatechange =majIHMafterADD;
	}
}

function majIHMafterADD() {
	console.log(requete.readyState +" :readyState");
	console.log(requete.status +" :status");
	var message = ""; 

	if(requete.readyState==4){
		if (requete.status == 200) {
			document.getElementById("mess").innerHTML = "Vos info on été modifié";

			setTimeout(function() {
			  document.getElementById("mess").innerHTML = "";
			},2000);
		}else{
			alert('Une erreur est survenue lors de la mise à jour de la page');
		}
	}
	
}

window.addEventListener('load', function(e) {

	document.getElementById("modinfo").addEventListener('click', function(e) {
		valider();
	});

});

</script>
<style>
.wrapper {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 10px;
}
.wrapperb {
  display: grid;
  grid-template-columns: repeat(1, 1fr);
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
input{width:100%}]

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
   
  	 padding-left: 0%;
  	 padding-right: 0%;
  
  }
    a{
  	text-decoration: none !important;
	color: white;
}
.wrapperP {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  column-gap: 10px;
  row-gap: 1em;

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