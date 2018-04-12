<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-default border-grey" id="university-container">
	<div class="panel-heading">
		<h4 class="panel-title">{{university.name}}</h4>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-12">
				<div class="content-group">
					<div class="panel-body bg-teal border-radius-top text-center">
						<div class="content-group-sm">
							<h5 class="text-semibold no-margin-bottom">{{university.name}}
							</h5>
							<span class="display-block">{{university.description}}</span>
						</div>

						<a class="display-inline-block content-group-sm"> <img
							src="<c:url value="/resources/images/{{university.logo}}"/>"
							class="img-circle img-responsive" alt=""
							style="width: 120px; height: 120px;">
						</a>

						<ul class="list-inline no-margin-bottom">
							<li><a ui-sref=".faculties()"
								class="btn bg-teal-700 btn-rounded btn-icon"><i
									class="icon-graduation"></i> Faculties</a></li>
						</ul>
					</div>

					<div class="panel panel-body no-border-top no-border-radius-top">
						<div class="form-group">
							<label class="text-semibold">Phone number:</label> <span
								class="pull-right-sm">{{university.contactInformation.phoneNumber}}</span>
						</div>

						<div class="form-group">
							<label class="text-semibold">Email:</label> <span
								class="pull-right-sm"><a
								href="mailto:{{university.contactInformation.email}}">{{university.contactInformation.email}}</a></span>
						</div>

						<div class="form-group no-margin-bottom">
							<label class="text-semibold">URL:</label> <span
								class="pull-right-sm"><a target="_blank"
								href="{{university.url}}">{{university.url}}</a></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>