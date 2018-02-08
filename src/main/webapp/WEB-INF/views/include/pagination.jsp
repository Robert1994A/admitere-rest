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