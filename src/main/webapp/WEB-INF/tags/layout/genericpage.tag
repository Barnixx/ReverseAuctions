	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@tag description="Page Template" pageEncoding="utf-8" %>
		<%@attribute name="title" %>
		<%@attribute name="subcategoriesMap" required="true" type="java.util.Map" %>
		<!doctype html>
		<html lang="en">
		
		<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<!-- Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
		
		<!--Bootstrap Select JS-->
		<script src="../../../views/js/bootstrap-select/bootstrap-select.min.js"></script>
		
		<script src="../../../views/js/script.js"></script>
		
		<link rel="stylesheet" href="../../../views/css/reset.css" type="text/css">
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
		crossorigin="anonymous">
		<!-- Font Awesome -->
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
		integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
		crossorigin="anonymous">
		<!--Bootstrap Select CSS-->
		<link rel="stylesheet" href="../../../views/css/bootstrap-select/bootstrap-select.min.css">
		
		<link rel="stylesheet" href='../../../views/css/style.css' type="text/css">
		
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
		<a class="nav-link" style="cursor: pointer; white-space: nowrap" id="userBarDropdown" data-toggle="dropdown"
		aria-haspopup="true"
		aria-expanded="false">
		<i class="fa fa-user fa-lg" style="display: inline"></i>
		Moje Konto
		<i class="fas fa-angle-down pl-2"></i>
		</a>
		<div class="dropdown-menu dropdown-menu-right m-0 rounded-0" style="width: 200px;"
		aria-labelledby="userBarDropdown">
		<a class="dropdown-item text-center" href="#">Moje Aukcje</a>
		<a class="dropdown-item text-center" href="#">Moje Oferty</a>
		<div class="dropdown-divider"></div>
		<a class="dropdown-item text-center" href="">Ustawienia
		<i class="fa fa-cog fa-lg pl-3 ml-auto"></i>
		</a>
		<div class="dropdown-divider"></div>
		<a class="btn btn-warning rounded-0 text-secondary w-100" href="/signIn">Zaloguj</a>
		<a class="dropdown-item text-center pt-1" href="">Zarejstruj się</a>
		</div>
		</li>
		
		</ul>
		<form class="mx-2 my-auto d-inline w-100 order-0 p-0" method="GET" action="/searchAuction">
		<div class="input-group p-0 m-0">
		<input type="text" class="form-control border rounded-0 m-0 w-auto" placeholder="Co dziś chcesz zaoferować?">
		<span class="input-group-append p-0 m-0">
		<select class="selectpicker m-0 border p-0">
		<option value="0" selected>Wszędzie</option>
		<c:forEach items="${subcategoriesMap}" var="category">
			<option value="${category.key.id}">${category.key.categoryName}</option>
		</c:forEach>
		</select>
		
		</span>
		<span class="input-group-append p-0 m-0">
		<button class="btn btn-info rounded-0 m-0 p-0" type="submit">
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
		<li class="nav-item ml-3 p-0 border-right border-left dropdown" style="width: 200px">
		<a class="nav-link py-3 px-4 text-dark categoryButton" style="cursor: pointer;" id="categoryDropDown"
		aria-haspopup="true"
		aria-expanded="false" data-toggle="dropdown">
		Kategorie
		<i class="fas fa-angle-down pl-5 pr-0"></i>
		</a>
		<div class="dropdown-menu categoryNav border rounded-0 mt-0 p-0" aria-labelledby="categoryDropDown">
		<div class="categoryBox">
		<div class="border-right p-0 m-0" style="width: 198px">
		<c:forEach items="${subcategoriesMap}" var="category">
			<div id="category_${category.key.id}" class="pt-3 pl-3 categoryItem">
			<a class="nav-link font-weight-bold" href="/getCategory/${category.key.id}">${category.key.categoryName}</a>
			</div>
		</c:forEach>
		</div>
		<div class="subcategoryBox">
		<c:forEach items="${subcategoriesMap}" var="category">
			<div id="subcategory_${category.key.id}" class="subcategoryItem px-4 py-2">
			<ul>
			<c:forEach items="${category.value}" var="subcategory">
				<li class="pt-3 pl-2 pr-5">
				<a class="" href="/getCategory/${category.key.id}/${subcategory.id}">${subcategory.subcategoryName}</a>
				</li>
			</c:forEach>
			</ul>
			</div>
		</c:forEach>
		</div>
		</div>
		</div>
		</li>
		</ul>
		</div>
		</nav>
		</div>
		<jsp:doBody/>
		</div>
		<div id="pageFooter" class="footer">
		<div class="d-flex justify-content-center">
		
		<p id="copyright" class="text-white m-0">Copyright 2018, Reverse Auction Sp. z. o o.</p>
		</div>
		</div>
		
		</body>
		
		</html>