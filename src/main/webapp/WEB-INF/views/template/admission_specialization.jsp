<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<div class="panel panel-default border-grey"
	id="admission-specialization-container">
	<div class="panel-heading">
		<h4 class="panel-title">Admission specialization</h4>
	</div>

	<div class="panel-body">
		<div class="row">
			<div class="col-md-12" ng-if="admissionSpecializationFound">
				<div class="content-group">
					<div class="panel-body bg-teal border-radius-top text-center">
						<div class="content-group-sm">
							<h5 class="text-semibold no-margin-bottom">{{admissionSpecialization.name}}
							</h5>
						</div>

						<a class="display-inline-block content-group-sm"> <img
							src="<c:url value='/resources/images/my_logo.jpg'/>"
							class="img-circle img-responsive" alt=""
							style="width: 120px; height: 120px;">
						</a>


						<ul class="list-inline no-margin-bottom">
							<li><a ui-sref=".statistics()"
								class="btn bg-teal-700 btn-rounded btn-icon"><i
									class="icon-stats-bars2"></i> Statistics</a></li>
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li><a ui-sref=".users()"
									class="btn bg-teal-700 btn-rounded btn-icon"><i
										class="icon-users4"></i> Users</a></li>
							</security:authorize>
						</ul>
					</div>

					<div class="panel panel-body no-border-top no-border-radius-top">
						<div class="form-group">
							<label class="text-semibold">Type:</label> <span
								class="pull-right-sm">{{admissionSpecialization.specializationType}}</span>
						</div>

						<div class="form-group">
							<label class="text-semibold">Number of places:</label> <span
								class="pull-right-sm">{{admissionSpecialization.numberOfPlaces}}</span>
						</div>

						<div class="form-group">
							<label class="text-semibold">Creation date:</label> <span
								class="pull-right-sm">{{admissionSpecialization.creationDate
								| date: 'yyyy-MM-dd'}}</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12" ng-if="!admissionSpecializationFound">
				<span ng-include src="'no-data-found.html'"></span>
			</div>
		</div>
	</div>
</div>