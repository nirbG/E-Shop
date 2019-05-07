<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	<h1>Panier :</h1>
	<div class="wrapper">
  		<% 
  		 out.println((String) request.getAttribute("prods"));
	  %>
	</div>
	<div>
		<a href="<% 
  		 out.println((String) request.getAttribute("url"));
	  %>"> 
			<button class="btn">Supprimer panier</button>
		</a>
		<a href="/E-Shop/ValiderPanier"> 
			<button class="btn">Valider panier</button>
		</a>
	</div>
</div>
<style>
form{
margin: 25% 2%;
}

.wrapper {
  display: grid;
  column-gap: 10px;
  row-gap: 1em;

}
* {box-sizing: border-box;}

.wrapper {
  grid-column-gap: 10px;
  grid-row-gap: 1em;
  border-radius: 5px;
  border: 2px solid #000000;
  background-color: #c0c0c0;
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
  width: 50%;
display: inline-block;
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

</body>
</html>