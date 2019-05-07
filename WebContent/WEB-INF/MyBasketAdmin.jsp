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
  <a class="active" href="  <%
  	out.println((String) request.getAttribute("url"));
  %>">Retour</a>
  <%
  	out.println((String) request.getAttribute("button"));
  %>
</div>
<div style="margin:2% 5%">
	<h1>Panier : <% out.println((String) request.getAttribute("user")); %></h1>
	<div class="wrapper">
  		<% 
  		 out.println((String) request.getAttribute("prods"));
	  %>
	</div>
	<h2>prix : <% out.println((String) request.getAttribute("prix")); %></h2>
</div>
<style>
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
.wrapper {
  display: grid;
  grid-template-columns: repeat(1, 1fr);
  column-gap: 10px;
  row-gap: 1em;

}
* {box-sizing: border-box;}

.wrapper {
  grid-column-gap: 10px;
  grid-row-gap: 1em;

  border: 2px solid #000000;
  border-radius: 5px;
  background-color: #c0c0c0;
}


.prod {
  display: grid;
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
  	grid-template-columns: repeat(2, 1fr);
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