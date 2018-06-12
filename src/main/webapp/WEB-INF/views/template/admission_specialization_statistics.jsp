<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head></head>
<div class="panel panel-default border-grey"
	id="admission-specialization-statistics-container">
	<div class="panel-heading">
		<h4 class="panel-title">Statistics</h4>
	</div>
	<div class="panel-body">
		<div class="row" ng-if="statisticsFound">
			<span>
				<div class="col-md-4" ng-repeat="stats in statistics">
					<div class="panel panel-white">
						<div class="panel-heading">
							<h6 class="panel-title">{{stats.name}}</h6>
						</div>
						<div class="panel-body">
							<canvas class="chart chart-bar" chart-data="stats.values"
								chart-labels="stats.labels" chart-options="chartOptions"></canvas>
						</div>
					</div>

				</div>
			</span>
		</div>
		<div class="row" ng-if="!statisticsFound">
			<span ng-include src="'no-data-found.html'"></span>
		</div>
	</div>
</div>