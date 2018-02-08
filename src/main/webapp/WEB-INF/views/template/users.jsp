<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default" id="users-container">
	<div class="panel-heading">
		<h4 class="panel-title">Users</h4>
	</div>
	<div class="panel-body">
		<div class="row" ng-if="usersFound">

			<span ng-include src="'include/search.html'"></span>

			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>CNP</th>
							<th>Email</th>
							<th>Enabled</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="user in users">
							<td>{{user.username}}</td>
							<td>{{user.email}}</td>
							<td><span
								ng-class="user.enabled ? 'label label-success' : 'label label-danger'">{{user.enabled}}</span></td>
							<td>
								<div class="btn-group">
									<button type="button" class="btn btn-primary dropdown-toggle"
										data-toggle="dropdown" aria-expanded="false">
										Action <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a ng-click="userDetails(user.id)">Details</a></li>
										<li><a ng-click="userProfile(user.id)">Profile</a></li>
									</ul>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<span ng-include src="'include/pagination.html'"></span>
		</div>
	</div>

	<div class="row" ng-if="!usersFound">
		<span ng-include src="'no-data-found.html'"></span>
	</div>
</div>



