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
<ul>
  <li><a class="active" href="/E-Shop/">E-Shop</a></li>
  <li><a href="/E-Shop/ListProducts">Nos Produits</a></li>
  <li><a href="/E-Shop/MyBasket">Votre panier</a></li>
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
				<h1> login</h1>
				<div id="error"> <% out.println( (String) request.getAttribute("erreur")); %></div>
				<form onsubmit="return valider()" action="/E-Shop/Log" method="post">
				<p>email :</p>
				<input id="email" type="text" name="id" value="<% out.println( (String) request.getAttribute("email")); %>" >
				<p>Password :</p>
				<input id="pass" type="text" name="pass">
				<a href="/E-Shop/Sinscire">s'inscrire</a>
				<input  type="submit" value="login">
			</form>
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

}
</style>
</html>