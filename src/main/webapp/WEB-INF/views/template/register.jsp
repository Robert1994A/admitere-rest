<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<div class="panel panel-default border-grey" id="register-container">
	<div class="panel-heading">
		<h4 class="panel-title">Register</h4>
	</div>

	<div class="panel-body">
		<div class="row">
			<form id="register-form" name="registerForm">
				<div class="row">
					<div class="col-md-4 form-group"
						ng-class="{ 'has-error': registerForm.username.$touched && registerForm.username.$invalid }">
						<label class="upper">CNP</label> <input type="text"
							class="form-control" ng-model="user.username" name="username"
							required="required" ng-minlength="13" ng-maxlength="13"
							placeholder="Enter your CNP" />
						<div class="help-block" ng-messages="registerForm.username.$error"
							ng-if="registerForm.username.$touched">
							<div ng-messages-include="validation-messages.html"></div>
						</div>
					</div>
					<div class="col-md-4 form-group"
						ng-class="{ 'has-error': registerForm.email.$touched && registerForm.email.$invalid }">
						<label class="upper">Email</label> <input type="email"
							class="form-control" ng-model="user.email" name="email"
							required="required" ng-minlength="2" ng-maxlength="50"
							placeholder="Enter your email" />
						<div class="help-block" ng-messages="registerForm.email.$error"
							ng-if="registerForm.email.$touched">
							<div ng-messages-include="validation-messages.html"></div>
						</div>
					</div>
					<div class="col-md-4 form-group"
						ng-class="{ 'has-error': registerForm.phoneNumber.$touched && registerForm.phoneNumber.$invalid}">
						<label class="upper">Phone numbers</label> <input
							ng-disabled="profileCreated" type="text" class="form-control"
							ng-model="user.phoneNumber" name="phoneNumber"
							required="required" ng-minlength="10" ng-maxlength="100"
							placeholder="Enter your phone numbers" />
						<div class="help-block"
							ng-messages="registerForm.phoneNumber.$error"
							ng-if="registerForm.phoneNumber.$touched">
							<div ng-messages-include="validation-messages.html"></div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 form-group"
						ng-class="{ 'has-error': registerForm.password.$touched && registerForm.password.$invalid }">
						<label class="upper">Password</label> <input type="password"
							class="form-control" ng-model="user.password" name="password"
							required="required" ng-minlength="8" ng-maxlength="50"
							placeholder="Enter your password" />
						<div class="help-block" ng-messages="registerForm.password.$error"
							ng-if="registerForm.password.$touched">
							<div ng-messages-include="validation-messages.html"></div>
						</div>
					</div>
					<div class="col-md-6 form-group"
						ng-class="{ 'has-error': registerForm.retypePassword.$touched && registerForm.retypePassword.$invalid }">
						<label class="upper">Retype password</label> <input
							type="password" class="form-control"
							ng-model="user.retypePassword" name="retypePassword"
							required="required" ng-minlength="2" ng-maxlength="50"
							placeholder="Retype your password" />
						<div class="help-block"
							ng-messages="registerForm.retypePassword.$error"
							ng-if="registerForm.retypePassword.$touched">
							<div ng-messages-include="validation-messages.html"></div>
						</div>
					</div>
					<!-- 
					<div class="col-md-6 form-group">
						<div class="g-recaptcha" id="g-recaptcha"
							data-sitekey="{{captchaSiteKey}}"></div>
					</div> -->
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-default" ng-click="register()">Register
						new account</button>
				</div>
			</form>
		</div>
	</div>
</div>