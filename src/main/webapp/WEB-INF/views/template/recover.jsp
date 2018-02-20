<div class="panel panel-default border-grey">
	<div class="panel-heading">
		<h4 class="panel-title">Recover</h4>
	</div>

	<div class="panel-body">
		<div class="row">
			<p class="center">To receive a new password, enter your email
				address or CNP below.</p>
			<form name="recoverForm">
				<div class="form-group"
					ng-class="{ 'has-error': recoverForm.email.$touched && recoverForm.email.$invalid }">
					<input type="text" class="form-control" ng-model="email"
						name="email" required="required" ng-minlength="2"
						ng-maxlength="50" placeholder="Enter your email or username " />
					<div class="help-block" ng-messages="recoverForm.email.$error"
						ng-if="recoverForm.email.$touched">
						<div ng-messages-include="validation-messages.html"></div>
					</div>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-default">Recover your
						Password</button>
				</div>
			</form>
		</div>
	</div>
</div>