<div class="modal-header">
	<button type="button" class="close" ng-click="cancel()"
		aria-hidden="true">×</button>
	<h4 class="modal-title" id="modal-label">Error:</h4>
</div>
<div class="modal-body">
	<div class="row">
		<div class="col-md-12">{{message}}</div>
	</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-primary" ng-click="cancel()">Close</button>
</div>