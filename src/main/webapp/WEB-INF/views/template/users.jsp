<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default" id="users-container">
	<div class="panel-heading">
		<h4 class="panel-title">Users</h4>
		<div class="heading-elements">
			<form class="heading-form" action="#">
				<div class="form-group has-feedback">
					<input type="search" class="form-control"
						ng-init="searchUser.username=''" ng-model="searchUser.username"
						placeholder="Search...">
					<div class="form-control-feedback">
						<i class="icon-search4 text-size-base text-muted"></i>
					</div>
				</div>
			</form>
			<button type="button" ui-sref=".add()"
				class="btn btn-primary heading-btn">
				<i class="icon-plus3 position-left"></i> Add new user
			</button>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="row" ng-if="usersFound">
				<!-- Include search form -->
				<span ng-include src="'include/search.html'"></span>

				<table class="table table-hover table-responsive">
					<thead>
						<tr>
							<th><div class="checker">
									<span><input type="checkbox" class="styled"></span>
								</div></th>
							<th>CNP</th>
							<th>Email</th>
							<th>Enabled</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="user in users | filter:searchUser">
							<td class="col-md-1"><div class="checker">
									<span><input type="checkbox" class="styled"></span>
								</div></td>
							<td>{{user.username}}</td>
							<td>{{user.email}}</td>
							<td><span
								ng-class="user.enabled ? 'label label-success' : 'label label-danger'">{{user.enabled}}</span></td>
							<td>
								<div class="btn-group">
									<button type="button" ng-click="userDetails(user.id)"
										class="btn btn-primary btn-icon">
										<i class="icon-info3"></i>
									</button>
									<button type="button" ng-click="userProfile(user.id)"
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



