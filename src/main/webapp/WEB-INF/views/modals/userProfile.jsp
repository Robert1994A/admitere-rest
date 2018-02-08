<div class="modal-header">
	<button type="button" class="close" ng-click="cancel()"
		aria-hidden="true">×</button>
	<h4 class="modal-title" id="modal-label">User profile</h4>
</div>
<div class="modal-body">
	<div class="row">
		<div class="col-md-12">
			<div ng-if="!profileFound">
				<span ng-include src="'no-data-found.html'"></span>
			</div>
			<div ng-if="profileFound">
				<p>{{user.id}}</p>
				<p>{{user.email}}</p>
				<div class="text-center">
					<button type="button" class="btn btn-warning">Disable</button>
					<button type="button" class="btn btn-success">Enable</button>
					<button type="button" class="btn btn-danger">Delete</button>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-b" ng-click="cancel()">Close</button>
</div>