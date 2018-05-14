<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head></head>
<div class="panel panel-default border-grey"
	id="admission-specialization-statistics-container">
	<div class="panel-heading">
		<h4 class="panel-title">Admission specializations statistics</h4>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-4">
				<div class="panel panel-white">
					<div class="panel-heading">
						<h6 class="panel-title">Test</h6>
					</div>
					<div class="panel-body">
						<canvas id="bar" class="chart chart-bar" chart-data="data"
							chart-labels="labels" chart-series="series"></canvas>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="panel panel-white">
					<div class="panel-heading">
						<h6 class="panel-title">Test</h6>
					</div>
					<div class="panel-body">
						<canvas id="bar" class="chart chart-bar" chart-data="data"
							chart-labels="labels" chart-series="series"></canvas>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="panel panel-white">
					<div class="panel-heading">
						<h6 class="panel-title">Test</h6>
					</div>
					<div class="panel-body">
						<canvas id="bar" class="chart chart-bar" chart-data="data"
							chart-labels="labels" chart-series="series"></canvas>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>