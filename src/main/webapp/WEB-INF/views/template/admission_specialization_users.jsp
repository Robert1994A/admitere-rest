<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head></head>
<div class="panel panel-default border-grey"
	id="admission-specialization-users-container">
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
		</div>
	</div>
	<div class="panel-body">
		<div class="row" ng-if="usersFound">
			<table class="table table-hover table-responsive">
				<thead>
					<tr>
						<th>CNP</th>
						<th>Email</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="user in users | filter:searchUser">
						<td>{{user.username}}</td>
						<td>{{user.email}}</td>
						<td>
							<div class="btn-group">
								<button type="button" ng-click="userProfile(user.id)"
									class="btn btn-primary btn-icon">
									<i class="icon-menu62"></i>
								</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row" ng-if="!usersFound">
			<span ng-include src="'no-data-found.html'"></span>
		</div>
	</div>
</div>