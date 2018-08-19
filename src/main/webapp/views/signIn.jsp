<%--
  Created by IntelliJ IDEA.
  User: Barni
  Date: 14.08.2018
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/layout" %>

<t:genericpage subcategoriesMap="${subcategoriesMap}">
	<div class="container-fluid mt-3">
	<div class="container ">
		<div class="row justify-content-center border p-5 userLoginPanel">
			<div class="col-6 w-100">
				<form class="" method="post">
					<fieldset class="form-group row py-4">
						<legend class="col-form-legend col-sm-12"><b>Zaloguj się, aby odkryć oferty</b></legend>
						<div class="row">
							<div class="col-12">
								<div class="form-group">
									<label for="userPasswordInput">Login/Email</label>
									<input type="text"
										   name="userLoginInput" id="userLoginInput" class="form-control rounded-0"
										   style="width: 100%"
										   placeholder="exemple@exemple.pl"
										   aria-describedby="userLoginInputHelp">
									<small id="userLoginInputHelp" class="text-muted">Wprowadź login lub e-mail</small>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<div class="form-group">
									<label for="userPasswordInput">Hasło</label>
									<input type="password"
										   name="userPasswordInput" id="userPasswordInput"
										   class="form-control rounded-0"
										   placeholder="Hasło"
										   aria-describedby="userPassowordInputHelp">
									<small id="userPassowordInputHelp" class="text-muted">Wprowadź hasło</small>
								</div>
							</div>
						</div>
						<a href="/resetPassword" class="d-flex justify-content-end">Nie pamiętasz hasła?</a>
					</fieldset>
					<div class="form-group row">
						<div class="col-sm-12 d-flex justify-content-end p-0">
							<button type="submit" class="btn btn-warning rounded-0 font-weight-bold">Zaloguj się
							</button>
						</div>
					</div>
				</form>
				<div class="row mt-2">
					<div class="d-flex justify-content-start">
						<span class="font-weight-bold d-inline-block">Jesteś pierwszy raz?</span>
						<a class="pl-3" href="signUp">ZAREJESTRUJ SIĘ!</a>
					</div>
				</div>
			</div>
			<div class="col-6">
				<div class="d-flex">
					<p>Tutaj można jakąś reklamę wcisnąć</p>
				</div>
			</div>
		
		</div>
		
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
	</div>

</t:genericpage>
