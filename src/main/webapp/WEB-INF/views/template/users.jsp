<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default" id="users-container">
	<div class="panel-body">
		<div class="row" ng-if="usersFound">
			<!-- Include search form -->
			<span ng-include src="'include/search.html'"></span>

			<div class="table table-responsive table-hover">
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
									<button type="button" ng-click="userProfile(user.id)"
										class="btn btn-primary btn-icon">
										<i class="icon-info3"></i>
									</button>
									<button type="button" ng-click="userDetails(user.id)"
										class="btn btn-primary btn-icon">
										<i class="icon-menu62"></i>
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>

				<!-- Include pagination -->
				<span ng-include src="'include/pagination.html'"></span>
			</div>
		</div>

		<div class="row" ng-if="!usersFound">
			<span ng-include src="'no-data-found.html'"></span>
		</div>
	</div>
</div>



