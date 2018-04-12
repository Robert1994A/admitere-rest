<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default border-grey" id="faculty-container">
	<div class="panel-heading">
		<h4 class="panel-title">{{faculty.name}}</h4>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-12">
				<div class="content-group">
					<div class="panel-body bg-teal border-radius-top text-center">
						<div class="content-group-sm">
							<h5 class="text-semibold no-margin-bottom">{{faculty.name}}
							</h5>
							<span class="display-block">{{faculty.description}}</span>
						</div>

						<a class="display-inline-block content-group-sm"> <img
							src="<c:url value='/resources/images/{{university.logo}}'/>"
							class="img-circle img-responsive" alt=""
							style="width: 120px; height: 120px;">
						</a>

						<ul class="list-inline no-margin-bottom">
							<li><a ui-sref=".domains()"
								class="btn bg-teal-700 btn-rounded btn-icon"><i
									class="icon-list"></i> Domains</a></li>
							<li><a ui-sref=".sessions()"
								class="btn bg-teal-700 btn-rounded btn-icon"><i
									class="icon-list3"></i> Sessions</a></li>
							<li><a ui-sref=".specializations()"
								class="btn bg-teal-700 btn-rounded btn-icon"><i
									class="icon-magazine"> Specializations</i></a></li>
						</ul>
					</div>

					<div class="panel panel-body no-border-top no-border-radius-top">
						<div class="form-group">
							<label class="text-semibold">Phone number:</label> <span
								class="pull-right-sm">{{faculty.contactInformation.phoneNumber}}</span>
						</div>

						<div class="form-group">
							<label class="text-semibold">Email:</label> <span
								class="pull-right-sm"><a
								href="mailto:{{university.contactInformation.email}}">{{faculty.contactInformation.email}}</a></span>
						</div>

						<div class="form-group no-margin-bottom">
							<label class="text-semibold">URL:</label> <span
								class="pull-right-sm"><a target="_blank"
								href="{{university.url}}">{{faculty.url}}</a></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>