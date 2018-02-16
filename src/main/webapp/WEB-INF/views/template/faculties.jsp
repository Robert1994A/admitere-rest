<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-default border-grey" id="faculties-container">
	<div class="panel-heading">
		<h4 class="panel-title">Faculties</h4>
		<div class="heading-elements">
			<button type="button" ui-sref=".add()"
				class="btn btn-primary heading-btn">
				<i class="icon-plus3 position-left"></i> Add new faculty
			</button>
		</div>
	</div>
	<div class="panel-body">
		<div class="row" ng-if="facultiesFound">
			<table class="table table-responsive">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="faculty in faculties">
						<td>{{faculty.id}}</td>
						<td>{{faculty.name}}</td>
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