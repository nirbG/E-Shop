<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>E-Shop</title>
</head>
<body>
<script type="text/javascript">
	function valider(){
		console.log("a");
		var id = document.getElementById("id");
		var pass = document.getElementById("pass");
		var iderror=0;
		var bool=true;
		console.log(id.value);
		console.log(pass.value);
		if(id.value==""){
			bool=false;
			iderror=1;
		}
		if(pass.value==""){
			bool=false;
			iderror=2
		}
		if(pass.value=="" && id.value==""){
			bool=false;
			iderror=3
		}
		if(!bool){
			var url = "LogError";
			if (window.XMLHttpRequest) {
				requete = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				requete = new ActiveXObject("Microsoft.XMLHTTP");
			}
			requete.open("POST", url, true);
			requete.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
			requete.send('idError='+iderror);
			requete.onreadystatechange =majIHM;
			return true;
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
</script>
<div class="topnav">
  <a class="url active" href="/E-Shop/">E-Shop</a>
  <a class="url" href="/E-Shop/ListProducts">Nos produits</a>
  <a class="url" href="/E-Shop/MyBasket">Votre panier</a>
  <%
  	out.println((String) request.getAttribute("button"));
  %>
</div>
	<div style="margin:2% 5%">
		<h1>Se connecter</h1>
		<div class="wrapper">
		<div></div>
			<div>
				<div id="error"> <% out.println( (String) request.getAttribute("erreur")); %></div>
				<form onsubmit="return valider()" action="/E-Shop/Log" method="post">
				<p>email :</p>
				<input id="email" type="text" name="id" value="<% out.println( (String) request.getAttribute("email")); %>" >
				<p>Password :</p>
				<input id="pass" type="password" name="pass">
				<a href="/E-Shop/Sinscire" style="color:blue">s'inscrire</a>
				<input class="btn"  type="submit" value="login">
				</form>
			</div>
		</div>
	</div>
</body>
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
  border-radius: 5px;
  background-color: #ffff;
}



.prod {
	height: 110px;
	border: 2px solid black;
  	border-radius: 5px;
  	background-color: #c0c0c0;
	color: black;
	width: 30%;
	display: inline-block;
	margin: 1%;
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
.h2-overflow {
    overflow: hidden;
    white-space: nowrap;
    width: 90% !important;
    text-overflow: ellipsis;
    font-size: 20px;
}

.photo{
border: 1px solid;

margin: 3%;

border-radius: 5px;

width: 10%;

display: inline-block;

height: 80px;

float: left;
}
.corp{
width: 80%;
display: inline-block;
padding-top: 5%;
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


    .btn {
  	 font-size: 14px;
  }
  h3{
	font-size: 15px;
}

  
}

/* On screens that are 600px or less, set the background color to olive */
@media screen and (max-width: 600px) {
.wrapper {
  display: grid;
  grid-template-columns: repeat(1, 1fr);
  }
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

    .btn {
  	 font-size: 10px;
  }
  h3{
	font-size: 16px;
}
  
}
</style>
</html>