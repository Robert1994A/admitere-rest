<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Register</h4>
	</div>

	<div class="panel-body">
		<div class="row">
			<div class="col-md-12">
				<form id="register-form" name="registerForm">
					<div class="row">
						<div class="col-md-6 form-group"
							ng-class="{ 'has-error': registerForm.username.$touched && registerForm.username.$invalid }">
							<label class="upper">CNP</label> <input type="text"
								class="form-control" ng-model="username" name="username"
								required="required" ng-minlength="13" ng-maxlength="13"
								placeholder="Enter your CNP" />
							<div class="help-block"
								ng-messages="registerForm.username.$error"
								ng-if="registerForm.username.$touched">
								<div ng-messages-include="validation-messages.html"></div>
							</div>
						</div>
						<div class="col-md-6 form-group"
							ng-class="{ 'has-error': registerForm.email.$touched && registerForm.email.$invalid }">
							<label class="upper">Email</label> <input type="email"
								class="form-control" ng-model="email" name="email"
								required="required" ng-minlength="2" ng-maxlength="50"
								placeholder="Enter your email" />
							<div class="help-block" ng-messages="registerForm.email.$error"
								ng-if="registerForm.email.$touched">
								<div ng-messages-include="validation-messages.html"></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 form-group"
							ng-class="{ 'has-error': registerForm.password.$touched && registerForm.password.$invalid }">
							<label class="upper">Password</label> <input type="text"
								class="form-control" ng-model="password" name="password"
								required="required" ng-minlength="8" ng-maxlength="50"
								placeholder="Enter your password" />
							<div class="help-block"
								ng-messages="registerForm.password.$error"
								ng-if="registerForm.password.$touched">
								<div ng-messages-include="validation-messages.html"></div>
							</div>
						</div>
						<div class="col-md-6 form-group"
							ng-class="{ 'has-error': registerForm.retypePassword.$touched && registerForm.retypePassword.$invalid }">
							<label class="upper">Retype password</label> <input type="text"
								class="form-control" ng-model="retypePassword"
								name="retypePassword" required="required" ng-minlength="2"
								ng-maxlength="50" placeholder="Retype your password" />
							<div class="help-block"
								ng-messages="registerForm.retypePassword.$error"
								ng-if="registerForm.retypePassword.$touched">
								<div ng-messages-include="validation-messages.html"></div>
							</div>
						</div>
					</div>
					<div class="col-md-12 form-group">
						<button type="submit" class="btn btn-default"
							ng-click="register()">Register New Account</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>