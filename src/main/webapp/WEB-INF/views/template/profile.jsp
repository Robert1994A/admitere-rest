<div class="panel panel-default" id="profile-container">
	<div class="panel-heading">
		<h4 class="panel-title">Profile</h4>
	</div>
	<div class="panel-body">
		<div class="row">
			<form id="profile-form" name="profileForm">
				<div class="col-md-10 col-md-offset-1">
					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Details</h3>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group"
												ng-class="{ 'has-error': profileForm.firstName.$touched && profileForm.firstName.$invalid }">
												<label class="upper">First name</label> <input type="text"
													ng-disabled="profileCreated" class="form-control"
													ng-model="profile.firstName" name="firstName"
													required="required" ng-minlength="2" ng-maxlength="25"
													placeholder="Enter your first name" />
												<div class="help-block"
													ng-messages="profileForm.firstName.$error"
													ng-if="profileForm.firstName.$touched">
													<div ng-messages-include="validation-messages.html"></div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group"
												ng-class="{ 'has-error': profileForm.lastName.$touched && profileForm.lastName.$invalid}">
												<label class="upper">Last name</label> <input type="text"
													ng-disabled="profileCreated" class="form-control"
													ng-model="profile.lastName" name="lastName"
													required="required" ng-minlength="2" ng-maxlength="25"
													placeholder="Enter your last name" />
												<div class="help-block"
													ng-messages="profileForm.lastName.$error"
													ng-if="profileForm.lastName.$touched">
													<div ng-messages-include="validation-messages.html"></div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<label class="upper">Birth date</label>
											<p class="input-group">
												<input type="text" class="form-control" disabled="disabled"
													uib-datepicker-popup="{{dateFormat}}"
													ng-model="profile.birthDate"
													datepicker-options="dateOptions" is-open="datePopupOpened"
													ng-required="true" close-text="Close" /> <span
													class="input-group-btn">
													<button type="button" class="btn btn-default"
														ng-click="openDatePopup()">
														<i class="glyphicon glyphicon-calendar"></i>
													</button>
												</span>
											</p>
										</div>
										<div class="col-md-6">
											<div class="form-group"
												ng-class="{ 'has-error': profileForm.phoneNumber.$touched && profileForm.phoneNumber.$invalid}">
												<label class="upper">Phone numbers</label> <input
													ng-disabled="profileCreated" type="text"
													class="form-control" ng-model="profile.phoneNumber"
													name="phoneNumber" required="required" ng-minlength="10"
													ng-maxlength="100" placeholder="Enter your phone numbers" />
												<div class="help-block"
													ng-messages="profileForm.phoneNumber.$error"
													ng-if="profileForm.phoneNumber.$touched">
													<div ng-messages-include="validation-messages.html"></div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group"
												ng-class="{ 'has-error': profileForm.motherFirstName.$touched && profileForm.motherFirstName.$invalid}">
												<label class="upper">Mother first name</label> <input
													type="text" class="form-control"
													ng-disabled="profileCreated"
													ng-model="profile.motherFirstName" name="motherFirstName"
													required="required" ng-minlength="2" ng-maxlength="25"
													placeholder="Enter your mother first name" />
												<div class="help-block"
													ng-messages="profileForm.motherFirstName.$error"
													ng-if="profileForm.motherFirstName.$touched">
													<div ng-messages-include="validation-messages.html"></div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group"
												ng-class="{ 'has-error': profileForm.motherLastName.$touched && profileForm.motherLastName.$invalid}">
												<label class="upper">Mother last name</label> <input
													type="text" class="form-control"
													ng-disabled="profileCreated"
													ng-model="profile.motherLastName" name="motherLastName"
													required="required" ng-minlength="2" ng-maxlength="25"
													placeholder="Enter your mother last name" />
												<div class="help-block"
													ng-messages="profileForm.motherLastName.$error"
													ng-if="profileForm.motherLastName.$touched">
													<div ng-messages-include="validation-messages.html"></div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group"
												ng-class="{ 'has-error': profileForm.fatherFirstName.$touched && profileForm.fatherFirstName.$invalid}">
												<label class="upper">Father first name</label> <input
													type="text" class="form-control"
													ng-disabled="profileCreated"
													ng-model="profile.fatherFirstName" name="fatherFirstName"
													required="required" ng-minlength="2" ng-maxlength="25"
													placeholder="Enter your father first name" />
												<div class="help-block"
													ng-messages="profileForm.fatherFirstName.$error"
													ng-if="profileForm.fatherFirstName.$touched">
													<div ng-messages-include="validation-messages.html"></div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group"
												ng-class="{ 'has-error': profileForm.fatherLastName.$touched && profileForm.fatherLastName.$invalid}">
												<label class="upper">Father last name</label> <input
													type="text" class="form-control"
													ng-disabled="profileCreated"
													ng-model="profile.fatherLastName" name="fatherLastName"
													required="required" ng-minlength="2" ng-maxlength="25"
													placeholder="Enter your father last name" />
												<div class="help-block"
													ng-messages="profileForm.fatherLastName.$error"
													ng-if="profileForm.fatherLastName.$touched">
													<div ng-messages-include="validation-messages.html"></div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group"
												ng-class="{ 'has-error': profileForm.initialsName.$touched && profileForm.initialsName.$invalid}">
												<label class="upper">The initial letter of father
													name</label> <input type="text" class="form-control"
													ng-disabled="profileCreated"
													ng-model="profile.initialsName" name="initialsName"
													required="required" ng-minlength="1" ng-maxlength="1"
													placeholder="Enter the initial letter to the father's name" />
												<div class="help-block"
													ng-messages="profileForm.initialsName.$error"
													ng-if="profileForm.initialsName.$touched">
													<div ng-messages-include="validation-messages.html"></div>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="upper"> Sexuality</label> <select
													ng-disabled="profileCreated" required="required"
													class="form-control" name="genderId"
													ng-model="profile.gender.id">
													<option ng-repeat="gender in profileData.genders"
														value="{{gender.id}}">{{gender.name}}</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="upper"> Religion</label> <select
													required="required" class="form-control" name="religionId"
													ng-disabled="profileCreated" ng-model="profile.religion.id">
													<option ng-repeat="religion in profileData.religions"
														value="{{religion.id}}">{{religion.name}}</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="upper"> Citizenship</label> <select
													required="required" class="form-control"
													ng-disabled="profileCreated" name="citizenshipId"
													ng-model="profile.citizenship.id">
													<option ng-repeat="citizenship in profileData.citizenships"
														value="{{citizenship.id}}">{{citizenship.name}}</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="upper"> Ethnicity</label> <select
													required="required" class="form-control" name="ethnicityId"
													ng-disabled="profileCreated"
													ng-model="profile.ethnicity.id">
													<option ng-repeat="ethnicity in profileData.ethnicities"
														value="{{ethnicity.id}}">{{ethnicity.name}}</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="upper"> Social situation</label> <select
													required="required" class="form-control"
													name="socialSituationId" ng-disabled="profileCreated"
													ng-model="profile.socialSituation.id">
													<option
														ng-repeat="socialSituation in profileData.socialSituations"
														value="{{socialSituation.id}}">{{socialSituation.name}}</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="upper"> Family situation</label> <select
													required="required" class="form-control"
													ng-disabled="profileCreated" name="familySituationId"
													ng-model="profile.familySituation.id">
													<option
														ng-repeat="familySituation in profileData.familySituations"
														value="{{familySituation.id}}">{{familySituation.name}}</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="upper"> Marital status</label> <select
													required="required" class="form-control"
													ng-disabled="profileCreated" name="maritalStatusId"
													ng-model="profile.maritalStatus.id">
													<option
														ng-repeat="maritalStatus in profileData.maritalStatus"
														value="{{maritalStatus.id}}">{{maritalStatus.name}}</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="upper"> Special medical condition</label> <select
													required="required" class="form-control"
													name="medicalConditionId" ng-disabled="profileCreated"
													ng-model="profile.medicalCondition.id">
													<option
														ng-repeat="medicalCondition in profileData.medicalConditions"
														value="{{medicalCondition.id}}">{{medicalCondition.name}}</option>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">Residence</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-md-4">
													<div class="form-group">
														<label class="upper">Country</label> <select
															ng-disabled="profileCreated" required="required"
															class="form-control" name="countryId"
															ng-init="profile.country.id = countries[0].id"
															ng-model="profile.country.id">
															<option ng-repeat="country in countries"
																value="{{country.id}}">{{country.name}}</option>
														</select>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="upper">State</label> <span
															ng-show="states.length > 0"> <select
															ng-disabled="profileCreated" name="stateId"
															required="required" class="form-control"
															ng-init="profile.state.id = states[0].id"
															ng-model="profile.state.id">
																<option ng-repeat="state in states" value="{{state.id}}">{{state.name}}</option>
														</select>
														</span> <span ng-if="states.length == 0"
															class="form-control text-danger"> * Select country
															first </span>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="upper">City</label> <span
															ng-show="cities.length > 0"> <select name="cityId"
															ng-disabled="profileCreated" required="required"
															class="form-control" ng-model="profile.city.id">
																<option ng-repeat="city in cities" value="{{city.id}}">{{city.name}}</option>
														</select>
														</span> <span ng-if="cities.length == 0"
															class="form-control text-danger"> * Select state
															first </span>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<div class="form-group"
														ng-class="{ 'has-error': profileForm.street.$touched && profileForm.street.$invalid}">
														<label class="upper">Street</label> <input type="text"
															class="form-control" ng-model="profile.street"
															ng-disabled="profileCreated" name="street"
															required="required" ng-minlength="2" ng-maxlength="100"
															placeholder="Enter your street" />
														<div class="help-block"
															ng-messages="profileForm.street.$error"
															ng-if="profileForm.street.$touched">
															<div ng-messages-include="validation-messages.html"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group text-center">
									<button class="btn btn-default" ng-click="saveProfile()">
										<i class="fa fa-paper-plane"></i>Save changes
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>