<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Add new faculty</h4>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="col-md-12">
					<form name="addFacultyForm">
						<div class="form-group"
							ng-class="{ 'has-error': addFacultyForm.name.$touched && addFacultyForm.name.$invalid }">
							<label class="upper">Name</label> <input type="text"
								class="form-control" ng-model="name" name="name"
								required="required" ng-minlength="2" ng-maxlength="50"
								placeholder="Enter your email or username " />
							<div class="help-block" ng-messages="addFacultyForm.name.$error"
								ng-if="addFacultyForm.name.$touched">
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
	</div>
</div>