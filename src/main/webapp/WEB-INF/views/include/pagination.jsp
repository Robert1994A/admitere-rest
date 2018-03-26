<div class="datatable-footer center">
	<span class="pagination-sm" uib-pagination previous-text="&laquo;"
		next-text="&raquo;" total-items="pagination.totalElements"
		items-per-page="pagination.numberOfElements"
		ng-model="pagination.number" max-size="5" boundary-link-numbers="true"
		force-ellipses="true" num-pages="pagination.totalPages"
		ng-change="paginate()"></span>
</div>