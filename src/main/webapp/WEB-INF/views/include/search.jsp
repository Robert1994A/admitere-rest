<uib-accordion>
<div uib-accordion-group class="panel-default" is-open="status.open">
	<uib-accordion-heading> Advanced search <i
		class="pull-right glyphicon"
		ng-class="{'glyphicon-chevron-down': status.open, 'glyphicon-chevron-right': !status.open}"></i>
	</uib-accordion-heading>
	<div class="panel-body border-grey">
		<div class="row">
			<div class="col-md-6 form-group">
				<label class="upper">Search</label> <input type="text"
					ng-model="searchText" class="form-control">
			</div>
			<div class="col-md-2 form-group">
				<label class="upper">Sort by</label><select ng-model="sortBy"
					class="form-control">
					<option value="id" selected="selected">ID</option>
					<option value="name">Name</option>
					<option value="creationDate">Date</option>
				</select>
			</div>
			<div class="col-md-2 form-group">
				<label class="upper">Per page</label> <select ng-model="perPage"
					class="form-control">
					<option value="25" selected="selected">25</option>
					<option value="50">50</option>
				</select>
			</div>
			<div class="col-md-2 form-group">
				<label class="upper">Sort direction</label> <select
					ng-model="sortDirection" class="form-control">
					<option value="ASC" selected="selected">ASC</option>
					<option value="DESC">DESC</option>
				</select>
			</div>
		</div>
	</div>

	<ul class="list-inline text-center no-margin-bottom">
		<li><button ng-click="search()" class="btn btn-primary">
				<i class="icon-search4 position-left"></i> Search
			</button></li>
		<li><button ng-click="refresh()" class="btn btn-warning">
				<i class="icon-reload-alt position-left"></i> Refine
			</button></li>
	</ul>
</div>
</uib-accordion>
