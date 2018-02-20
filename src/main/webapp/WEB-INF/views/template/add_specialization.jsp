<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Add new specialization</h4>
	</div>
	<div class="panel-body">
		<div class="row col-md-12">
			<form name="addSpecializationForm">
				<div class="form-group"
					ng-class="{ 'has-error': addSpecializationForm.name.$touched && addSpecializationForm.name.$invalid }">
					<label class="upper">Name</label> <input type="text"
						class="form-control" ng-model="name" name="name"
						required="required" ng-minlength="2" ng-maxlength="50"
						placeholder="Enter specialization name" />
					<div class="help-block"
						ng-messages="addSpecializationForm.name.$error"
						ng-if="addSpecializationForm.name.$touched">
						<div ng-messages-include="validation-messages.html"></div>
					</div>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-default">Add new
						specialization</button>
				</div>
			</form>
		</div>
	</div>
</div>