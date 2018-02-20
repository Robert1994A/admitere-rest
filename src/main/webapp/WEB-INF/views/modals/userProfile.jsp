<div class="modal-header">
	<button type="button" class="close" ng-click="cancel()"
		aria-hidden="true">×</button>
	<h4 class="modal-title" id="modal-label">User profile</h4>
</div>
<div class="modal-body">
	<div class="row">
		<div class="col-md-12">
			<div ng-if="!profileFound">
				<span ng-include src="'no-data-found.html'"></span>
			</div>
			<div class="row" ng-if="profileFound">
				<uib-accordion>
				<div uib-accordion-group class="panel-default" is-open="status.open">
					<uib-accordion-heading> Details <i
						class="pull-right glyphicon"
						ng-class="{'glyphicon-chevron-down': status.open, 'glyphicon-chevron-right': !status.open}"></i>
					</uib-accordion-heading>
					<div class="panel-body border-grey">
						<div class="well">
							<dl class="dl-horizontal">
								<dt>First name</dt>
								<dd>{{profile.firstName}}</dd>
								<dt>Last name</dt>
								<dd>{{profile.lastName}}</dd>
								<dt>Initials name</dt>
								<dd>{{profile.initialsName}}</dd>
								<dt>Father first name</dt>
								<dd>{{profile.fatherFirstName}}</dd>
								<dt>Father last name</dt>
								<dd>{{profile.fatherLastName}}</dd>
								<dt>Mother first name</dt>
								<dd>{{profile.motherFirstName}}</dd>
								<dt>Mother last name</dt>
								<dd>{{profile.motherLastName}}</dd>
							</dl>
						</div>
					</div>
				</div>
				</uib-accordion>

				<uib-accordion>
				<div uib-accordion-group class="panel-default" is-open="status.open">
					<uib-accordion-heading> Residence <i
						class="pull-right glyphicon"
						ng-class="{'glyphicon-chevron-down': status.open, 'glyphicon-chevron-right': !status.open}"></i>
					</uib-accordion-heading>
					<div class="panel-body border-grey">
						<div class="well">
							<dl class="dl-horizontal">
								<dt>Country</dt>
								<dd>{{profile.country.name}}</dd>
								<dt>State</dt>
								<dd>{{profile.state.name}}</dd>
								<dt>City</dt>
								<dd>{{profile.city.name}}</dd>
								<dt>Street</dt>
								<dd>{{profile.street}}</dd>
								<dt>Birth date</dt>
								<dd>{{profile.birthDate}}</dd>
							</dl>
						</div>
					</div>
				</div>
				</uib-accordion>

				<uib-accordion>
				<div uib-accordion-group class="panel-default" is-open="status.open">
					<uib-accordion-heading> Others <i
						class="pull-right glyphicon"
						ng-class="{'glyphicon-chevron-down': status.open, 'glyphicon-chevron-right': !status.open}"></i>
					</uib-accordion-heading>
					<div class="panel-body border-grey">
						<div class="well">
							<dl class="dl-horizontal">
								<dt>Citizenship</dt>
								<dd>{{profile.citizenship.name}}</dd>
								<dt>Gender</dt>
								<dd>{{profile.gender.name}}</dd>
								<dt>Family situation</dt>
								<dd>{{profile.familySituation.name}}</dd>
								<dt>Marital status</dt>
								<dd>{{profile.maritalStatus.name}}</dd>
								<dt>Medical condition</dt>
								<dd>{{profile.medicalCondition.name}}</dd>
								<dt>Religion</dt>
								<dd>{{profile.religion.name}}</dd>
								<dt>Social situation</dt>
								<dd>{{profile.socialSituation.name}}</dd>
							</dl>
						</div>
					</div>
				</div>
				</uib-accordion>

			</div>
		</div>
	</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-b" ng-click="cancel()">Close</button>
</div>