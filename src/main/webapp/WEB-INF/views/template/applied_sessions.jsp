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
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="appliedSession in appliedSessions">
						<td>{{appliedSession.admissionSpecialization.facultySpecializationNomenclature.facultyDomainNomenclature.faculty.university.name}}</td>
						<td>{{appliedSession.admissionSpecialization.facultySpecializationNomenclature.facultyDomainNomenclature.faculty.name}}</td>
						<td>{{appliedSession.creationDate | date:'dd/MM/yyyy'}}</td>
						<td>
							<div class="btn-group">
								<button type="button" ng-click="moreDetails(appliedSession.id)"
									class="btn btn-primary btn-icon">
									<i class="icon-info3"></i>
								</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row" ng-if="!appliedSessionsFound">
			<span ng-include src="'no-data-found.html'"></span>
		</div>
	</div>
</div>

<script type="text/ng-template" id="appliedSessionDetail.html">
<div class="modal-header">
	<button type="button" class="close" ng-click="cancel()"
		aria-hidden="true">×</button>
	<h4 class="modal-title" id="modal-label">
		Applied session details.
	</h4>
</div>
<div class="modal-body">
	<div class="row">
		<div class="col-md-12">
			<div class="content-group">
					<div class="panel-body bg-teal border-radius-top text-center">
						<a class="display-inline-block content-group-sm"> <img
							src="<c:url value="/resources/images/{{university.logo}}"/>"
							class="img-circle img-responsive" alt=""
							style="width: 120px; height: 120px;">
						</a>
					</div>

					<div class="panel panel-body no-border-top no-border-radius-top">

						<div class="panel panel-white">
							<div class="panel-heading">
								<h6 class="panel-title">University</h6>
							</div>
							
							<div class="panel-body">
								<div class="form-group">
									<label class="text-semibold">Name:</label> <span
										class="pull-right-sm">{{university.name}}</span>
								</div>

								<div class="form-group">
									<label class="text-semibold">Description:</label> <span
										class="pull-right-sm">{{university.description}}</span>
								</div>

								<div class="form-group">
									<label class="text-semibold">Phone number:</label> <span
										class="pull-right-sm">{{university.contactInformation.phoneNumber}}</span>
								</div>

								<div class="form-group">
									<label class="text-semibold">Email:</label> <span
										class="pull-right-sm"><a
											href="mailto:{{university.contactInformation.email}}">{{university.contactInformation.email}}</a></span>
								</div>

								<div class="form-group">
									<label class="text-semibold">URL:</label> <span
										class="pull-right-sm"><a target="_blank"
										href="{{university.url}}">{{university.url}}</a></span>
								</div>
							</div>
						</div>

						<div class="panel panel-white">
							<div class="panel-heading">
								<h6 class="panel-title">Faculty</h6>
							</div>
							
							<div class="panel-body">
								<div class="form-group">
									<label class="text-semibold">Name:</label> <span
										class="pull-right-sm">{{faculty.name}}</span>
								</div>

								<div class="form-group">
									<label class="text-semibold">Description:</label> <span
										class="pull-right-sm">{{faculty.description}}</span>
								</div>

								<div class="form-group">
									<label class="text-semibold">Phone number:</label> <span
										class="pull-right-sm">{{faculty.contactInformation.phoneNumber}}</span>
								</div>

								<div class="form-group">
									<label class="text-semibold">Email:</label> <span
										class="pull-right-sm"><a
										href="mailto:{{faculty.contactInformation.email}}">{{faculty.contactInformation.email}}</a></span>
								</div>

								<div class="form-group">
									<label class="text-semibold">URL:</label> <span
										class="pull-right-sm"><a target="_blank"
										href="{{faculty.url}}">{{faculty.url}}</a></span>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-b" ng-click="cancel()">Close</button>
</div>       
</script>