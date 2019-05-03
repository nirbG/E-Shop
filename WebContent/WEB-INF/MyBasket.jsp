<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<ul>
  <li><a href="/E-Shop/">E-Shop</a></li>
  <li><a href="/E-Shop/ListProducts">Nos produits</a></li>
  <li><a class="active" href="/E-Shop/MyBasket">Votre panier</a></li>
  <li style="float:right;display:inline-block" >
  <%
  	out.println((String) request.getAttribute("button"));
  %>
  </li>
</ul>

<div style="margin:2% 5%">
	<h1>Panier:</h1>
	<div class="wrapper">
  		<% 
  		 out.println((String) request.getAttribute("prods"));
	  %>
	</div>
	<div>
		<a href="/E-Shop/RemoveFromBasket?idprod=-1"> 
			<button class="btn">Valider panier</button>
		</a>
	</div>
</div>
<style>

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

</body>
</html>