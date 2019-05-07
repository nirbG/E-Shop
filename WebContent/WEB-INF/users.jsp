<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	
  		<h1>Admin : </h1>
  		<div id="" class="wrapperP" >
  			<a class="btn" href="/E-Shop/Deco">Déconnexion</a>
  			<a class='btn' href="/E-Shop/Log/ConfirmerMdp">Modifier mot de passe</a>
  		</div>
		<div class="wrapper">
			<div id="forminfo" >
  				<div id="error"></div>
  				<div id="mess" style="color:green"></div>
  				<h1>Modifier vos infos:</h1>
					<input type="hidden" value="1">
					<p>Nom* :</p>
					<input id="nom" type="text" name="nom" value="<% out.println( (String) request.getAttribute("nom")); %>" >
					<p>Prénom* :</p>
					<input id="prenom" type="text" name="prenom" value="<% out.println( (String) request.getAttribute("prenom")); %>" >
					<p>Email* :</p>
					<input id="email" type="email" name="email" value="<% out.println( (String) request.getAttribute("email")); %>" >
					<p></p>
					<div id="modinfo" class="btn" >Valider</div>
  			</div>
			<div id="createUser" >
  				<h1>create user:</h1>
  				<div id="CreateError"></div>
  				<div id="createMess" style="color:green"></div>
				<input type="hidden" value="1">
				<p>Nom* :</p>
				<input id="Cnom" type="text" name="nom" >
				<p>Prenom* :</p>
				<input id="Cprenom" type="text" name="prenom" >
				<p>Password* :</p>
				<input id="Cpassword" type="password" name="password" >
				<p>conf Password* :</p>
				<input id="Cconf" type="password" name="conf" >
				<p>Email* :</p>
				<input id="Cemail" type="email" name="email" ><p></p>
				<div id="create" class="btn" >create</div>
  			</div>
  			
		</div>
		<div class="table">
			<h1>Users:</h1>
			<div style="max-height: 300px;width: 100%;overflow: auto">
	  			<% 
	  			String tableU = (String) request.getAttribute("users");;
	 	 		out.println( tableU );
	  			%>
			</div>
		</div>
		<h1>Gestion des produits : </h1>
		<div class="wrapperP">
			<div class="btn"><a href="/E-Shop/Log/Admin/CreateProd">Créer un produit</a></div>
			<div class="btn"><a href="/E-Shop/Log/Admin/ListModProd">Modifier ou supprimer un produit</a></div>
		</div>
		<h1>Gestion des paniers : </h1>
		<div class="wrapperP">
			<div class="btn"><a href="/E-Shop/Log/Admin/listeBasket">Voir les paniers</a></div>
			<div class="btn"><a href="/E-Shop/Log/VoirPanier">Vos paniers</a></div>
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
			var url = "../Log/ModInfo";
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
	
	function create(){
		console.log("a");
		var nom = document.getElementById("Cnom");
		var prenom = document.getElementById("Cprenom");
		var conf = document.getElementById("Cconf");
		var password = document.getElementById("Cpassword");
		var email = document.getElementById("Cemail");
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
					document.getElementById("CreateError").innerHTML = requete.responseText;
				}else{
					alert('Une erreur est survenue lors de la mise à jour de la page');
				}
				}
			}
			return false;
		}
		var url = "../Log/ModInfo";
		if (window.XMLHttpRequest) {
			requete2 = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requete2 = new ActiveXObject("Microsoft.XMLHTTP");
		}
		requete2.open("POST", url, true);
		requete2.setRequestHeader("Content-type", "application/x-www-form-urlencoded; ");
		requete2.send('action=4&nom='+nom.value+"&prenom="+prenom.value+"&password="+password.value+"&email="+email.value);
		requete2.onreadystatechange =function(event) {
			var message = ""; 
			if(requete2.readyState==4){
				if (requete2.status == 200) {
					console.log("add");
					var tab=document.getElementById("tab");
					var tr=document.createElement("tr");
					//<tr class='user' id='"+u.getId()+"'>"
					var id=requete2.responseText;
					tr.innerHTML = 
					"<td>"+id+"</td>"+
					"<td>"+nom.value+"</td>"+
					"<td>"+prenom.value+"</td>"+
					"<td>"+email.value+"</td>"+
					"<td>1</td>"+
					"<td class='wrapperbtn'><div class='btn '><a href='/E-Shop/Log/ModInfo?email="+email.value+"'>mod</a></div> <div class='btn suppUser'>supp</div></td>";
					tr.className += "user";
					tr.setAttribute("id",id);
					tab.append(tr);
					var classname=document.getElementsByClassName("suppUser");
					classname[classname.length-1].addEventListener('click', function(e) {
						var user=(e.target.parentNode).parentNode;
						supp(user)
					});
					document.getElementById("createMess").innerHTML = "le users a été créé";

					setTimeout(function() {
					  document.getElementById("createMess").innerHTML = "";
					},2000);
				}else{
					alert('Une erreur est survenue lors de la mise à jour de la page');
				}
			}
		};
	}
	
	function supp(user){
		
		var url = "ModInfo";
		if (window.XMLHttpRequest) {
			requete = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requete = new ActiveXObject("Microsoft.XMLHTTP");
		}
		requete.open("POST", url, true);
		requete.setRequestHeader("Content-type", "application/x-www-form-urlencoded; ");
		requete.send('action=3&id='+user.getAttribute("id"));
		requete.onreadystatechange = function(event) {
			var message = ""; 
			if(requete.readyState==4){
				if (requete.status == 200) {
					console.log("supp");
					user.remove();
				}else{
					alert('Une erreur est survenue lors de la mise à jour de la page');
				}
			}
		};
		
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
		
		document.getElementById("create").addEventListener('click', function(e) {
			create();
		});
		document.getElementById("modinfo").addEventListener('click', function(e) {
			valider();
		});
		var classname=document.getElementsByClassName("suppUser");
		for (var i = 0; i < classname.length; i++) {
			classname[i].addEventListener('click', function(e) {
				var user=(e.target.parentNode).parentNode;
				supp(user)
			});
		}
	});
</script>
<style>
.wrapper {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  column-gap: 10px;
  row-gap: 1em;

}

.wrapperP {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  column-gap: 10px;
  row-gap: 1em;

}

.wrapperbtn {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  column-gap: 5px;
  row-gap: 1px;

}

.wrapperP div {
  width: 100%

}
* {box-sizing: border-box;}

.wrapper {
  grid-column-gap: 10px;
  grid-row-gap: 1em;

  /*border: 2px solid #f76707;*/
  border-radius: 5px;
  background-color: #ffff;
}
.wrapper input {
width:100%;
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
.topnav .url:hover {
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

    .wrapper {
  	display: grid;
  	grid-template-columns: repeat(1, 1fr);
  }
  .wrapperP {
  grid-template-columns: repeat(1, 1fr);

}

.wrapperbtn {
  grid-template-columns: repeat(1, 1fr);

}
    .btn {
  	 font-size: 14px;
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
  
}
</style>
</html>