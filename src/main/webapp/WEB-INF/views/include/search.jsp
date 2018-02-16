<div class="panel-body">
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
	<li><button ng-click="search()" class="btn btn-link btn-rounded">
			<i class="icon-search4 position-left"></i> Search
		</button></li>
	<li><button ng-click="refresh()" class="btn btn-link btn-rounded">
			<i class="icon-reload-alt position-left"></i> Refine
		</button></li>
</ul>