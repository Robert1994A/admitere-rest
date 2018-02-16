<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Add new session</h4>
	</div>
	<div class="panel-body">
		<div class="row">
			<form name="addSessionForm">
				<div class="form-group col-md-4"
					ng-class="{ 'has-error': addSessionForm.name.$touched && addSessionForm.name.$invalid }">
					<label class="upper">Name</label> <input type="text"
						class="form-control" ng-model="name" name="name"
						required="required" ng-minlength="2" ng-maxlength="50"
						placeholder="Enter admission session name" />
					<div class="help-block" ng-messages="addSessionForm.name.$error"
						ng-if="addSessionForm.name.$touched">
						<div ng-messages-include="validation-messages.html"></div>
					</div>
				</div>
				<div class="form-group col-md-4"
					ng-class="{ 'has-error': addSessionForm.expiredDate.$touched && addSessionForm.expiredDate.$invalid }">
					<label class="upper">Expired date</label> <input type="text"
						class="form-control" ng-model="expiredDate" name="expiredDate"
						required="required" ng-minlength="2" ng-maxlength="50"
						placeholder="Select expired date" />
					<div class="help-block"
						ng-messages="addSessionForm.expiredDate.$error"
						ng-if="addSessionForm.expiredDate.$touched">
						<div ng-messages-include="validation-messages.html"></div>
					</div>
				</div>
				<div class="form-group col-md-4"
					ng-class="{ 'has-error': addSessionForm.enabled.$touched && addSessionForm.enabled.$invalid }">
					<label class="upper">Enabled</label> <input type="text"
						class="form-control" ng-model="enabled" name="enabled"
						required="required" ng-minlength="2" ng-maxlength="50"
						placeholder="Enter your email or username " />
					<div class="help-block" ng-messages="addSessionForm.enabled.$error"
						ng-if="addSessionForm.enabled.$touched">
						<div ng-messages-include="validation-messages.html"></div>
					</div>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-primary">
						<b><i class="glyphicon glyphicon-save position-left"></i></b> Add
					</button>
				</div>
			</form>
		</div>
	</div>
</div>