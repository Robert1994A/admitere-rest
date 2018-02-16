<div class="datatable-footer">
	<div style="float: left" class="bg-primary text-highlight">Page
		{{pagination.number}} / {{pagination.totalPages}}</div>
	<div style="float: right">
		<ul uib-pagination
			ng-change="paginate()"
			total-items="pagination.totalElements" ng-model="pagination.number"
			max-size="pagination.totalPages" class="pagination-sm"
			boundary-links="true" num-pages="pagination.totalPages"></ul>
	</div>
</div>