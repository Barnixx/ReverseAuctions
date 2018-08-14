	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@tag description="Page Template" pageEncoding="utf-8" %>
		<%@attribute name="title" %>
		<!doctype html>
		<html lang="en">
		<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
		integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
		crossorigin="anonymous">
		<link rel="stylesheet" href='<c:url value="/views/css/style.css"/>' type="text/css">
		
		<title>${title}</title>
		</head>
		<body>
		<div id="body">
		<div class="container-fluid border shadow-lg p-0">
		<nav class="navbar navbar-expand-md navbar-light bg-white border-bottom py-4 px-5 fixed-top">
		<a href="/" class="navbar-brand ml-5 pl-4 font-weight-bold">Reverse Auction</a>
		<div class="navbar-collapse mr-auto" id="navbarForm">
		<ul class="navbar-nav ml-auto order-1">
		<li class="nav-item dropdown mr-5">
		<a class="nav-link" style="cursor: pointer; white-space: nowrap" id="userBarDropdown"
		data-toggle="dropdown" aria-haspopup="true"
		aria-expanded="false">
		<i class="fa fa-user fa-lg" style="display: inline"></i>
		Moje Konto
		</a>
		<div class="dropdown-menu" aria-labelledby="userBarDropdown">
		<a class="dropdown-item" href="#">Moje Aukcje</a>
		<a class="dropdown-item" href="#">Moje Oferty</a>
		<div class="dropdown-divider"></div>
		<a class="dropdown-item" href="">Ustawienia
		<i class="fa fa-cog fa-lg ml-auto"></i>
		</a>
		<div class="dropdown-divider"></div>
		<a class="btn btn-warning rounded-0 text-secondary w-100" href="/signIn">Zaloguj</a>
		<a class="dropdown-item text-center pt-1" href="">Zarejstruj się</a>
		</div>
		</li>
		
		</ul>
		<form class="mx-2 my-auto d-inline w-100 order-0" method="GET" action="/searchAuction">
		<div class="input-group">
		<input type="text" class="form-control border rounded-0 w-auto" placeholder="Co dziś chcesz zaoferować?">
		<span class="input-group-append">
		<select class="form-control border rounded-0">
		<option>Elektronika
		</option>
		<option>Nieruchomości</option>
		</select>
		</span>
		<span class="input-group-append">
		<button class="btn btn-info border rounded-0 mh-100 " style="height: 100%;"
		type="submit">
		<b>Szukaj</b>
		</button>
		</span>
		</div>
		</form>
		</div>
		</nav>
		<nav class="navbar navbar-expand navbar-light bg-white p-0">
		<div class="navbar m-0 p-0">
		<ul class="navbar-nav m-0">
		<li class="nav-item ml-3 p-0 border-right border-left">
		<a class="nav-link py-3 px-4 text-dark" style="cursor: pointer;">
		Kategorie <i class="fas fa-angle-down pl-4 pr-2"></i>
		</a>
		</li>
		</ul>
		</div>
		
		</nav>
		</div>
		<jsp:doBody/>
		</div>
		<div id="pageFooter">
		<p id="copyright">Copyright 2018, Reverse Auction Sp. z. o o.</p>
		</div>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
		</body>
		</html>