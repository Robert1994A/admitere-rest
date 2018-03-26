<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default border-grey" id="domains-container">
	<div class="panel-heading">
		<h4 class="panel-title">Domains</h4>
		<div class="heading-elements">
			<form class="heading-form" action="#">
				<div class="form-group has-feedback">
					<input type="search" class="form-control"
						ng-init="searchDomain.name=''" ng-model="searchDomain.name"
						placeholder="Search...">
					<div class="form-control-feedback">
						<i class="icon-search4 text-size-base text-muted"></i>
					</div>
				</div>
			</form>
			<button type="button" ui-sref=".add()"
				class="btn btn-primary heading-btn">
				<i class="icon-plus3 position-left"></i> Add new domain
			</button>
		</div>
	</div>
	<div class="panel-body">
		<div class="row" ng-if="domainsFound">
			<table class="table table-responsive table-hover">
				<thead>
					<tr>
						<th>Name</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="domain in domains | filter:searchDomain">
						<td>{{domain.name}}</td>
						<td>
							<div class="btn-group">
								<button type="button"
									ui-sref=".specializations({domainId: domain.id})"
									class="btn btn-primary btn-icon">
									<i class="icon-info3"></i>
								</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row" ng-if="!domainsFound">
			<span ng-include src="'no-data-found.html'"></span>
		</div>
	</div>
</div>