<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default border-grey" id="domains-container">
	<div class="panel-heading">
		<h4 class="panel-title">Domains</h4>
	</div>
	<div class="panel-body">
		<div class="row" ng-if="domainsFound">
			<ol class="list no-margin-bottom">
				<li ng-repeat="domain in domains">{{domain.name}}
					<ol class="list">
						<li
							ng-repeat="specialization in domain.facultySpecializationNomenclatures">
							{{specialization.name}}</li>
					</ol>
				</li>
			</ol>
		</div>
		<div class="row" ng-if="!domainsFound">
			<span ng-include src="'no-data-found.html'"></span>
		</div>
	</div>
</div>