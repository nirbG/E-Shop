<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>E-Shop</title>
</head>
<body>

<div class="topnav">
  <%
  	out.println((String) request.getAttribute("button"));
  %>
</div>
	<div style="margin:2% 5%">
	<h1>Nouveau mot de passe :</h1>
	<div class="wrapper">
		<div></div>
			<div>
				<div id="error"> <% out.println( (String) request.getAttribute("erreur")); %></div>
				<form  action="/E-Shop/Log/NewPassword" method="post">
				<p> new Password :</p>
				<input id="pass" type="password" name="pass">
				<p>confirmer Password :</p>
				<input id="confpass" type="password" name="confpass">
				<input class="btn"  type="submit" value="Suivant">
			</form>
		</div>
	</div>
</body>
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