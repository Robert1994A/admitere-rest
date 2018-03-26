<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Add new session</h4>
	</div>
	<div class="panel-body">
		<div class="row col-md-12">
			<form name="addSessionForm">
				<div class="form-group "
					ng-class="{ 'has-error': addSessionForm.name.$touched && addSessionForm.name.$invalid}">
					<label class="upper">Name</label> <input type="text"
						class="form-control" ng-model="name" name="name"
						required="required" ng-minlength="2" ng-maxlength="50"
						placeholder="Enter session name" />
					<div class="help-block" ng-messages="addSessionForm.name.$error"
						ng-if="addSessionForm.name.$touched">
						<div ng-messages-include="validation-messages.html"></div>
					</div>
				</div>
				<div class="form-group"
					ng-class="{ 'has-error': addSessionForm.expirationDate.$touched && addSessionForm.expirationDate.$invalid}">
					<label class="upper">Expiration date</label> <input type="text"
						class="form-control" ng-model="expirationDate"
						name="expirationDate" required="required" ng-minlength="2"
						ng-maxlength="50" placeholder="Enter expiration date name" />
					<div class="help-block"
						ng-messages="addSessionForm.expirationDate.$error"
						ng-if="addSessionForm.expirationDate.$touched">
						<div ng-messages-include="validation-messages.html"></div>
					</div>
				</div>
				
				<div class="form-group">
					<button type="button" class="btn btn-default">Add new
						session</button>
				</div>
			</form>
		</div>
	</div>
</div>