<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Login</h4>
	</div>

	<div class="panel-body">
		<div class="row">
			<div class="col-md-offset-1 col-md-10">
				<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
					<div class="alert bg-warning alert-styled-right">
						<button type="button" class="close" data-dismiss="alert">
							<span>×</span><span class="sr-only">Close</span>
						</button>
						<span class="text-semibold">Warning!</span>
						<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
						.
					</div>
				</c:if>
				<form name="loginForm" action="<c:url value="/login_security"/>"
					method="post">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="form-group"
						ng-class="{ 'has-error': loginForm.username.$touched && loginForm.username.$invalid }">
						<label class="upper">Username</label> <input type="text"
							class="form-control" ng-model="username" name="username"
							required="required" ng-minlength="13" ng-maxlength="13"
							placeholder="Enter your username" />
						<div class="help-block" ng-messages="loginForm.username.$error"
							ng-if="loginForm.username.$touched">
							<div ng-messages-include="validation-messages.html"></div>
						</div>
					</div>
					<div class="form-group"
						ng-class="{ 'has-error': loginForm.password.$touched && loginForm.password.$invalid }">
						<label class="upper">Password</label> <input type="password"
							class="form-control" ng-model="password" name="password"
							required="required" ng-minlength="2" ng-maxlength="50"
							placeholder="Enter your password" />
						<div class="help-block" ng-messages="loginForm.password.$error"
							ng-if="loginForm.password.$touched">
							<div ng-messages-include="validation-messages.html"></div>
						</div>
					</div>
					<div class="form-group form-inline text-left ">
						<div class="checkbox">
							<label><input type="checkbox" name="remember_me"><small>Remember
									me</small> </label>
						</div>
						<a href="#!recover" class="right"><small>Lost your
								Password?</small></a>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">Login</button>
					</div>

					<p class="small">
						Don't have an account yet? <a href="#!register">Register New
							Account</a>
					</p>
				</form>
			</div>
		</div>
	</div>
</div>


