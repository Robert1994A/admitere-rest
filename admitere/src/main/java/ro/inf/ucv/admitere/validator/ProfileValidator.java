package ro.inf.ucv.admitere.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ro.inf.ucv.admitere.entity.Citizenship;
import ro.inf.ucv.admitere.entity.City;
import ro.inf.ucv.admitere.entity.Country;
import ro.inf.ucv.admitere.entity.Ethnicity;
import ro.inf.ucv.admitere.entity.FamilySituation;
import ro.inf.ucv.admitere.entity.Gender;
import ro.inf.ucv.admitere.entity.MaritalStatus;
import ro.inf.ucv.admitere.entity.MedicalCondition;
import ro.inf.ucv.admitere.entity.Profile;
import ro.inf.ucv.admitere.entity.Religion;
import ro.inf.ucv.admitere.entity.SocialSituation;
import ro.inf.ucv.admitere.entity.State;
import ro.inf.ucv.admitere.service.CitizenshipService;
import ro.inf.ucv.admitere.service.CityService;
import ro.inf.ucv.admitere.service.CountryService;
import ro.inf.ucv.admitere.service.EthnicityService;
import ro.inf.ucv.admitere.service.FamilySituationService;
import ro.inf.ucv.admitere.service.GenderService;
import ro.inf.ucv.admitere.service.MaritalStatusService;
import ro.inf.ucv.admitere.service.MedicalConditionService;
import ro.inf.ucv.admitere.service.ReligionService;
import ro.inf.ucv.admitere.service.SocialSituationService;
import ro.inf.ucv.admitere.service.StateService;

/**
 * Validates user answers to a form, check if the answers corresponds to the
 * respective question types
 */
@Component
public class ProfileValidator implements Validator {

	@Autowired
	private CountryService countryService;

	@Autowired
	private StateService stateService;

	@Autowired
	private CityService cityService;

	@Autowired
	private GenderService genderService;

	@Autowired
	private CitizenshipService citizenshipService;

	@Autowired
	private MaritalStatusService maritalStatusService;

	@Autowired
	private MedicalConditionService medicalConditionService;

	@Autowired
	private ReligionService religionService;

	@Autowired
	private EthnicityService ethnicityService;

	@Autowired
	private FamilySituationService familySituationService;

	@Autowired
	private SocialSituationService socialSituationService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Profile.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Profile profile = (Profile) target;

		Country country = countryService.findById(profile.getCountry().getId());
		if (country == null) {
			errors.reject("countryId", "profileForm.country.notFound");
		} else {
			profile.setCountry(country);
		}

		State state = stateService.findOne(profile.getState().getId());
		if (state == null) {
			errors.reject("stateId", "profileForm.state.notFounde");
		} else {
			profile.setState(state);
		}

		City city = cityService.findOne(profile.getCity().getId());
		if (city == null) {
			errors.reject("cityId", "profileForm.city.notFound");
		} else {
			profile.setCity(city);
		}

		Gender gender = genderService.findOne(profile.getGender().getId());
		if (gender == null) {
			errors.reject("genderId", "profileForm.gender.notFound");
		} else {
			profile.setGender(gender);
		}

		Citizenship citizenship = citizenshipService.findOne(profile.getCitizenship().getId());
		if (citizenship == null) {
			errors.reject("citizenshipId", "profileForm.citizenship.notFound");
		} else {
			profile.setCitizenship(citizenship);
		}

		MaritalStatus maritalStatus = maritalStatusService.findOne(profile.getMaritalStatus().getId());
		if (maritalStatus == null) {
			errors.reject("maritalStatusId", "profileForm.maritalStatus.notFound");
		} else {
			profile.setMaritalStatus(maritalStatus);
		}

		MedicalCondition medicalCondition = medicalConditionService.findOne(profile.getMedicalCondition().getId());
		if (medicalCondition == null) {
			errors.reject("medicalConditionId", "profileForm.medicalCondition.notFound");
		} else {
			profile.setMedicalCondition(medicalCondition);
		}

		Religion religion = religionService.findOne(profile.getReligion().getId());
		if (religion == null) {
			errors.reject("religionId", "profileForm.religion.notFound");
		} else {
			profile.setReligion(religion);
		}

		Ethnicity ethnicity = ethnicityService.findOne(profile.getEthnicity().getId());
		if (ethnicity == null) {
			errors.reject("ethnicityId", "profileForm.ethnicity.notFound");
		} else {
			profile.setEthnicity(ethnicity);
		}

		FamilySituation familySituation = familySituationService.findOne(profile.getFamilySituation().getId());
		if (familySituation == null) {
			errors.reject("familySituationId", "profileForm.familySituation.notFound");
		} else {
			profile.setFamilySituation(familySituation);
		}

		SocialSituation socialSituation = socialSituationService.findOne(profile.getSocialSituation().getId());
		if (socialSituation == null) {
			errors.reject("socialSituationId", "profileForm.socialSituation.notFound");
		} else {
			profile.setSocialSituation(socialSituation);
		}
	}
}
