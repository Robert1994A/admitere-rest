<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Users</h4>
	</div>
	<div class="panel-body">
		<div class="row" ng-if="usersFound">
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
									</ul>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="datatable-footer">
				<div style="float: left" class="bg-primary text-highlight">Page
					{{pagination.number}} / {{pagination.totalPages}}</div>
				<div style="float: right">
					<paging class="pagination" page="pagination.number"
						page-size="pagination.size" total="pagination.totalElements"
						hide-if-empty="true" ul-class="pagination" active-class="active"
						disabled-class="disabled" show-prev-next="true"
						show-first-last="true" text-next="Next" text-prev="Previous"
						text-first="First" text-last="Last"
						paging-action="paginate(page, pageSize, total)"> </paging>
				</div>
			</div>
		</div>

		<div class="row" ng-if="!usersFound">
			<span ng-messages-include="no-data-found.html"></span>
		</div>
	</div>
</div>


