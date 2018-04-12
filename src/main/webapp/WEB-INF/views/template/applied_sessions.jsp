<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default border-grey"
	id="applied-sessions-container">
	<div class="panel-heading">
		<h4 class="panel-title">Applied sessions</h4>
		<div class="heading-elements">
			<form class="heading-form" action="#">
				<div class="form-group has-feedback">
					<input type="search" class="form-control"
						ng-init="searchAppliedSession.name=''"
						ng-model="searchAppliedSession.name" placeholder="Search...">
					<div class="form-control-feedback">
						<i class="icon-search4 text-size-base text-muted"></i>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="panel-body">
		<div class="row" ng-if="appliedSessionsFound">
			<table class="table table-responsive table-hover">
				<thead>
					<tr>
						<th>University</th>
						<th>Faculty</th>
						<th>Applied date</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="appliedSession in appliedSessions">
						<td>{{appliedSession.admissionSpecialization.facultySpecializationNomenclature.facultyDomainNomenclature.faculty.university.name}}</td>
						<td>{{appliedSession.admissionSpecialization.facultySpecializationNomenclature.facultyDomainNomenclature.faculty.name}}</td>
						<td>{{appliedSession.creationDate | date:'dd/MM/yyyy'}}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row" ng-if="!appliedSessionsFound">
			<span ng-include src="'no-data-found.html'"></span>
		</div>
	</div>
</div>