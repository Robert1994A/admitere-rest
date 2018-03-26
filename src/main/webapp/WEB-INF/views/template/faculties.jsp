<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default border-grey" id="faculties-container">
	<div class="panel-heading">
		<h4 class="panel-title">Faculties</h4>
		<div class="heading-elements">
			<form class="heading-form" action="#">
				<div class="form-group has-feedback">
					<input type="search" class="form-control"
						ng-init="searchFaculty.name=''" ng-model="searchFaculty.name"
						placeholder="Search...">
					<div class="form-control-feedback">
						<i class="icon-search4 text-size-base text-muted"></i>
					</div>
				</div>
			</form>
			<button type="button" ui-sref=".add()"
				class="btn btn-primary heading-btn">
				<i class="icon-plus3 position-left"></i> Add new faculty
			</button>
		</div>
	</div>
	<div class="panel-body">
		<div class="row" ng-if="facultiesFound">
			<table class="table table-responsive table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<td>Contact</td>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="faculty in faculties | filter:searchFaculty">
						<td>{{faculty.id}}</td>
						<td>{{faculty.name}}</td>
						<td>
							<p>
								<i class="icon-envelope"></i>
								{{faculty.contactInformation.email}}
							</p>
							<p>
								<i class="icon-phone2"></i>
								{{faculty.contactInformation.phoneNumber}}
							</p>
							<p>
								<i class="icon-link2"></i> <a href="{{faculty.url}}">
									{{faculty.url}}</a>
							</p>
						</td>
						<td>
							<div class="btn-group">
								<button type="button" ui-sref=".detail({facultyId: faculty.id})"
									class="btn btn-primary btn-icon">
									<i class="icon-info3"></i>
								</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row" ng-if="!facultiesFound">
			<span ng-include src="'no-data-found.html'"></span>
		</div>
	</div>
</div>