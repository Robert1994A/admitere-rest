<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-default" id="universities-container">
	<div class="panel-heading">
		<h4 class="panel-title">Universities</h4>
	</div>

	<div class="panel-body">
		<div class="row" ng-if="universitiesFound">
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>URL</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="university in universities">
							<td>{{university.id}}</td>
							<td>{{university.name}}</td>
							<td>{{university.url}}</td>
							<td>
								<div class="btn-group">
									<button type="button" class="btn btn-primary dropdown-toggle"
										data-toggle="dropdown" aria-expanded="false">
										Action <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a ng-click="userDetails(user.id)">Details</a></li>
									</ul>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<span ng-include src="'include/pagination.html'"></span>
		</div>

		<div class="row" ng-if="!universitiesFound">
			<span ng-include src="'no-data-found.html'"></span>
		</div>
	</div>
</div>