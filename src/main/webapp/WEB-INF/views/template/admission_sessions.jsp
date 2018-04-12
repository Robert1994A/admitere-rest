<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default border-grey" id="sessions-container">
	<div class="panel-heading">
		<h4 class="panel-title">
			Sessions<a class="heading-elements-toggle"><i class="icon-more"></i></a>
		</h4>
		<div class="heading-elements">
			<form class="heading-form" action="#">
				<div class="form-group has-feedback">
					<input type="search" class="form-control"
						ng-init="searchSession.name=''" ng-model="searchSession.name"
						placeholder="Search...">
					<div class="form-control-feedback">
						<i class="icon-search4 text-size-base text-muted"></i>
					</div>
				</div>
			</form>
			<button type="button" ui-sref=".add()"
				class="btn btn-primary heading-btn">
				<i class="icon-plus3 position-left"></i> Add new session
			</button>
		</div>
	</div>
	<div class="panel-body">
		<div class="row" ng-if="sessionsFound">
			<div ng-repeat="session in sessions">

				<uib-accordion>
				<div uib-accordion-group class="panel-default" is-open="status.open">
					<uib-accordion-heading> {{session.name}} <i
						class="pull-right glyphicon"
						ng-class="{'glyphicon-chevron-down': status.open, 'glyphicon-chevron-right': !status.open}"></i>
					</uib-accordion-heading>
					<div class="panel-body border-blue">
						<div class="row">
							<div
								ng-repeat="admissionSpecialization in session.admissionSpecializations">
								<div class="col-md-4 col-sm-3" style="margin-top: 10px"
									ng-model="facultySpecializationNomenclature"
									ng-init="facultySpecializationNomenclature = admissionSpecialization.facultySpecializationNomenclature">
									<div class="panel panel-primary">
										<div class="panel-heading">
											<h6 class="panel-title">{{facultySpecializationNomenclature.name}}</h6>
										</div>
										<div class="panel-body">
											<div class="panel-body">
												<b>Description:</b>
												{{facultySpecializationNomenclature.description}}<br /> <b>Email:
												</b>
												{{facultySpecializationNomenclature.contactInformation.email}}
												<br /> <b>Phone number:</b>{{facultySpecializationNomenclature.contactInformation.phoneNumber}}<br />
												<b>URL:</b><a
													href="{{facultySpecializationNomenclature.url}}">
													{{facultySpecializationNomenclature.url}}</a><br /> <b>Number
													of places:</b>
												{{facultySpecializationNomenclature.maxNumberOfPlaces}}<br />

												<b>Form of learning: </b>{{facultySpecializationNomenclature.formOfLearning}}<br />

												<b>Accreditation: </b>{{facultySpecializationNomenclature.accreditation}}<br />

												<b>Number of credits: </b>{{facultySpecializationNomenclature.numberOfCredits}}<br />

												<b>Nomenclature name: </b>
												{{admissionSpecialization.specializationSample.sampleNomenclature.name}}<br />

												<b>Nomenclature type: </b>{{admissionSpecialization.specializationSample.sampleNomenclature.nomenclatureType}}<br />

												<b>Enabled: </b>{{session.enabled}}<br /> <b>Creation
													date: </b>{{session.creationDate | date: 'yyyy-MM-dd'}}<br />

												<b>Expiration date: </b>{{session.expirationDate | date:
												'yyyy-MM-dd'}}<br />

												<div class="text-center">
													<div class="btn-group">
														<button type="button" class="btn btn-primary"
															ng-click="apply(admissionSpecialization.id)">Apply</button>
														<button type="button" class="btn btn-primary"
															ui-sref=".detail({admissionSessionId: session.id})">Go
															to session page</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</uib-accordion>
			</div>
		</div>
		<div class="row" ng-if="!sessionsFound">
			<span ng-include src="'no-data-found.html'"></span>
		</div>
	</div>
</div>