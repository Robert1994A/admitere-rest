<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default border-grey"
	id="specializations-container">
	<div class="panel-heading">
		<h4 class="panel-title">Specializations</h4>
		<div class="heading-elements">
			<form class="heading-form" action="#">
				<div class="form-group has-feedback">
					<input type="search" class="form-control"
						ng-init="searchSpecialization.name=''" ng-model="searchSpecialization.name"
						placeholder="Search...">
					<div class="form-control-feedback">
						<i class="icon-search4 text-size-base text-muted"></i>
					</div>
				</div>
			</form>
			<button type="button" ui-sref=".add()"
				class="btn btn-primary heading-btn">
				<i class="icon-plus3 position-left"></i> Add new specialization
			</button>
		</div>
	</div>
	<div class="panel-body">
		<div class="row" ng-if="specializationsFound">
			<table class="table table-responsive table-hover">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Number of places</th>
						<th>Form of learning</th>
						<th>Accreditation</th>
						<th>Number of credits</th>
						<th>Contact</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="specialization in specializations | filter:searchSpecialization">
						<td>{{specialization.name}}</td>
						<td>{{specialization.description}}</td>
						<td>{{specialization.maxNumberOfPlaces}}</td>
						<td>{{specialization.formOfLearning}}</td>
						<td>{{specialization.accreditation}}</td>
						<td>{{specialization.numberOfCredits}}</td>
						<td>
							<p>
								<i class="icon-envelope"></i>
								{{specialization.contactInformation.email}}
							</p>
							<p>
								<i class="icon-phone2"></i>
								{{specialization.contactInformation.phoneNumber}}
							</p>
							<p>
								<i class="icon-link2"></i> <a href="{{specialization.url}}">
									{{specialization.url}}</a>
							</p>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row" ng-if="!specializationsFound">
			<span ng-include src="'no-data-found.html'"></span>
		</div>
	</div>
</div>