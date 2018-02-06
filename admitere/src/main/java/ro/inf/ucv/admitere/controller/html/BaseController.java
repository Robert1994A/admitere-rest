package ro.inf.ucv.admitere.controller.html;

import java.security.SecureRandom;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import ro.inf.ucv.admitere.captcha.AttemptsService;
import ro.inf.ucv.admitere.captcha.CaptchaService;
import ro.inf.ucv.admitere.service.CitizenshipService;
import ro.inf.ucv.admitere.service.CityService;
import ro.inf.ucv.admitere.service.CountryService;
import ro.inf.ucv.admitere.service.EthnicityService;
import ro.inf.ucv.admitere.service.FamilySituationService;
import ro.inf.ucv.admitere.service.GenderService;
import ro.inf.ucv.admitere.service.MaritalStatusService;
import ro.inf.ucv.admitere.service.MedicalConditionService;
import ro.inf.ucv.admitere.service.StateService;
import ro.inf.ucv.admitere.service.UniversityService;
import ro.inf.ucv.admitere.service.ProfileService;
import ro.inf.ucv.admitere.service.ReligionService;
import ro.inf.ucv.admitere.service.RoleService;
import ro.inf.ucv.admitere.service.SampleNomenclatureService;
import ro.inf.ucv.admitere.service.SocialSituationService;
import ro.inf.ucv.admitere.service.UserService;
import ro.inf.ucv.admitere.service.utils.ConfigurationUtils;
import ro.inf.ucv.admitere.service.utils.Mailer;
import ro.inf.ucv.admitere.service.utils.StringGenerator;
import ro.inf.ucv.admitere.validator.ProfileValidator;

@Controller
public class BaseController {

	@Autowired
	protected RoleService roleService;

	@Autowired
	protected UserService userService;

	@Autowired
	protected ProfileService profileService;

	@Autowired
	protected CityService cityService;

	@Autowired
	protected CountryService countryService;

	@Autowired
	protected StateService stateService;

	@Autowired
	protected GenderService genderService;

	@Autowired
	protected CitizenshipService citizenshipService;

	@Autowired
	protected EthnicityService ethnicityService;

	@Autowired
	protected MaritalStatusService maritalStatusService;

	@Autowired
	protected ReligionService religionService;

	@Autowired
	protected MedicalConditionService medicalConditionService;

	@Autowired
	protected FamilySituationService familySituationService;

	@Autowired
	protected SocialSituationService socialSituationService;

	@Autowired
	protected UniversityService universityService;

	@Autowired
	protected SampleNomenclatureService sampleNomenclatureService;

	@Autowired
	protected Mailer mailer;

	@Autowired
	protected ProfileValidator profileValidator;

	@Autowired
	protected ConfigurationUtils configurationUtils;

	@Autowired
	protected AttemptsService attemptsService;

	@Autowired
	protected CaptchaService captchaService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	protected StringGenerator stringGenerator;

	protected BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, new SecureRandom());

	private Locale locale = LocaleContextHolder.getLocale();

	public String getMessage(String key, Object[] args, Locale locale) {
		return messageSource.getMessage(key, args, locale);
	}

	public String getMessage(String key, Object args, Locale locale) {
		Object[] objects = { args };
		return messageSource.getMessage(key, objects, locale);
	}

	public Locale getLocale() {
		return locale;
	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(profileValidator);
	}
}
