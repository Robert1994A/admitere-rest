<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default border-grey" id="sessions-container">
	<div class="panel-heading">
		<h4 class="panel-title">Sessions</h4>
		<div class="heading-elements">
			<button type="button" ui-sref=".add()"
				class="btn btn-primary heading-btn">
				<i class="icon-plus3 position-left"></i> Add new session
			</button>
		</div>
	</div>
	<div class="panel-body">
		<div class="row" ng-if="sessionsFound">
			<ol class="list no-margin-bottom">
				<li ng-repeat="session in sessions">{{session.name}}</li>
			</ol>
		</div>
		<div class="row" ng-if="!sessionsFound">
			<span ng-include src="'no-data-found.html'"></span>
		</div>
	</div>
</div>