<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default" id="universities-container">
	<div class="panel-heading">
		<h4 class="panel-title">Universities</h4>
		<div class="heading-elements">
			<form class="heading-form" action="#">
				<div class="form-group has-feedback">
					<input type="search" class="form-control"
						ng-init="searchUniversity.name=''"
						ng-model="searchUniversity.name" placeholder="Search...">
					<div class="form-control-feedback">
						<i class="icon-search4 text-size-base text-muted"></i>
					</div>
				</div>
			</form>
			<button type="button" ui-sref=".add()"
				class="btn btn-primary heading-btn">
				<i class="icon-plus3 position-left"></i> Add new university
			</button>
		</div>
	</div>
	<div class="panel-body">
		<!-- Include search form -->
		<span ng-include src="'include/search.html'"></span>
		
		<div class="row" ng-if="universitiesFound">
			<table class="table table-responsive table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Contact</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<tr
						ng-repeat="university in universities | filter:searchUniversity">
						<td>{{university.id}}</td>
						<td>{{university.name}}</td>
						<td>
							<p>
								<i class="icon-envelope"></i>
								{{university.contactInformation.email}}
							</p>
							<p>
								<i class="icon-phone2"></i>
								{{university.contactInformation.phoneNumber}}
							</p>
							<p>
								<i class="icon-link2"></i> <a href="{{university.url}}">
									{{university.url}}</a>
							</p>
						</td>
						<td>
							<div class="btn-group">
								<button type="button"
									ui-sref=".detail({universityId: university.id})"
									class="btn btn-primary btn-icon">
									<i class="icon-info3"></i>
								</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>

			<span ng-include src="'include/pagination.html'"></span>
		</div>

		<div class="row" ng-if="!universitiesFound">
			<span ng-include src="'no-data-found.html'"></span>
		</div>
	</div>
</div>