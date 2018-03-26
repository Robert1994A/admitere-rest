package ro.inf.ucv.admitere.controller.html;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import ro.inf.ucv.admitere.entity.AdmissionSpecialization;
import ro.inf.ucv.admitere.entity.AdmissionSession;
import ro.inf.ucv.admitere.entity.Citizenship;
import ro.inf.ucv.admitere.entity.City;
import ro.inf.ucv.admitere.entity.ContactInformation;
import ro.inf.ucv.admitere.entity.Country;
import ro.inf.ucv.admitere.entity.Ethnicity;
import ro.inf.ucv.admitere.entity.Faculty;
import ro.inf.ucv.admitere.entity.FacultyDomainNomenclature;
import ro.inf.ucv.admitere.entity.FacultySpecializationNomenclature;
import ro.inf.ucv.admitere.entity.FamilySituation;
import ro.inf.ucv.admitere.entity.Gender;
import ro.inf.ucv.admitere.entity.MaritalStatus;
import ro.inf.ucv.admitere.entity.MedicalCondition;
import ro.inf.ucv.admitere.entity.Profile;
import ro.inf.ucv.admitere.entity.Religion;
import ro.inf.ucv.admitere.entity.Role;
import ro.inf.ucv.admitere.entity.SampleNomenclature;
import ro.inf.ucv.admitere.entity.SocialSituation;
import ro.inf.ucv.admitere.entity.SpecializationSample;
import ro.inf.ucv.admitere.entity.State;
import ro.inf.ucv.admitere.entity.University;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.enumerations.NomenclatureType;
import ro.inf.ucv.admitere.enumerations.Roles;
import ro.inf.ucv.admitere.enumerations.SpecializationType;
import ro.inf.ucv.admitere.service.utils.StringUtils;
import ro.inf.ucv.admitere.wrapper.ProfileWrapper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class InitController extends BaseController {

	private static final String DESCRIPTION = "Description";

	private static final String EMAIL = "test@gmail.com";

	private static final String NUMBER = "0123456789";

	private static final String URL = "http://google.ro";

	@GetMapping("/init")
	public void home(Model model) throws Exception {
		if (userService.count() == 0) {
			boolean flush = false;
			initNomenclator(flush);
			initProfileData(flush);
			initSampleNomenclature(flush);
			initDatabaseUsers(20, flush);
		}
	}

	public void initDatabaseUsers(int numberOfUsers, boolean flush) throws Exception {
		Role roleAdmin = new Role();
		roleAdmin.setName(Roles.ADMIN.getName());
		roleAdmin.setCreationDate(new Date());
		roleService.save(roleAdmin, flush);

		Role roleFaculty = new Role();
		roleFaculty.setName(Roles.FACULTY.getName());
		roleFaculty.setCreationDate(new Date());
		roleService.save(roleFaculty, flush);

		Role roleUniversity = new Role();
		roleUniversity.setName(Roles.UNIVERSITY.getName());
		roleUniversity.setCreationDate(new Date());
		roleService.save(roleUniversity, flush);

		Role roleUser = new Role();
		roleUser.setName(Roles.USER.getName());
		roleUser.setCreationDate(new Date());
		roleService.save(roleUser, flush);

		List<Role> rolesAdmin = new ArrayList<Role>();
		rolesAdmin.add(roleUniversity);
		rolesAdmin.add(roleFaculty);
		rolesAdmin.add(roleAdmin);
		rolesAdmin.add(roleUser);

		List<Role> rolesFaculty = new ArrayList<Role>();
		rolesFaculty.add(roleFaculty);
		rolesFaculty.add(roleUser);

		List<Role> rolesUniversity = new ArrayList<Role>();
		rolesUniversity.add(roleUniversity);
		rolesUniversity.add(roleFaculty);
		rolesUniversity.add(roleUser);

		List<Role> rolesUser = new ArrayList<Role>();
		rolesUser.add(roleUser);

		ProfileWrapper profileWrapper = createProfileWrapper();
		List<City> cities = cityService.findAll();
		List<Country> countries = countryService.findAll();
		List<State> states = stateService.findAll();
		List<University> universities = universityService.findAll();
		List<Faculty> faculties = facultyService.findAll();

		long beginTime = new GregorianCalendar(1971, 01, 01).getTime().getTime();
		long endTime = new GregorianCalendar(2000, 01, 01).getTime().getTime();
		long diff = endTime - beginTime + 1;

		for (int i = 0; i < numberOfUsers; i++) {
			String username = securityUtils.getUsernameCNPString();
			User userAdmin = new User();
			userAdmin.setEnabled(true);
			userAdmin.setEmail("admin" + i + "@gmail.com");
			userAdmin.setRegisterToken(securityUtils.getEncodedRandomString());
			userAdmin.setUsername(username);
			userAdmin.setPassword(securityUtils.encode(username));
			userAdmin.setRoles(rolesAdmin);
			userAdmin.setPhoneNumber(securityUtils.getPhoneNumberString());
			userAdmin.setRecoverPaswordToken(securityUtils.getEncodedRandomString());
			userAdmin = userService.save(userAdmin, flush);
			initProfile(userAdmin, profileWrapper, countries, cities, states, beginTime, diff);
		}

		for (int i = 0; i < numberOfUsers; i++) {
			Faculty faculty = null;
			if (faculties != null && !faculties.isEmpty()) {
				faculty = faculties.get(getRandom(faculties.size()));
			}

			String username = securityUtils.getUsernameCNPString();
			User userFaculty = new User();
			userFaculty.setEnabled(true);
			userFaculty.setPhoneNumber(securityUtils.getPhoneNumberString());
			userFaculty.setEmail("faculty" + i + "@gmail.com");
			userFaculty.setRegisterToken(securityUtils.getEncodedRandomString());
			userFaculty.setUsername(username);
			userFaculty.setPassword(securityUtils.encode(username));
			userFaculty.setRoles(rolesFaculty);
			userFaculty.setRecoverPaswordToken(securityUtils.getEncodedRandomString());
			userFaculty.setFaculty(faculty);
			userFaculty = userService.save(userFaculty, flush);
			initProfile(userFaculty, profileWrapper, countries, cities, states, beginTime, diff);
		}

		for (int i = 0; i < numberOfUsers; i++) {
			University university = null;
			if (universities != null && !universities.isEmpty()) {
				university = universities.get(getRandom(universities.size()));
			}

			String username = securityUtils.getUsernameCNPString();
			User userUniversity = new User();
			userUniversity.setEnabled(true);
			userUniversity.setEmail("university" + i + "@gmail.com");
			userUniversity.setRegisterToken(securityUtils.getEncodedRandomString());
			userUniversity.setPhoneNumber(securityUtils.getPhoneNumberString());
			userUniversity.setUsername(username);
			userUniversity.setPassword(securityUtils.encode(username));
			userUniversity.setRoles(rolesUniversity);
			userUniversity.setRecoverPaswordToken(securityUtils.getEncodedRandomString());
			userUniversity.setUniversity(university);
			userUniversity = userService.save(userUniversity, flush);
			initProfile(userUniversity, profileWrapper, countries, cities, states, beginTime, diff);
		}

		for (int i = 0; i < numberOfUsers; i++) {
			String username = securityUtils.getUsernameCNPString();
			User user = new User();
			user.setEnabled(new Random().nextBoolean());
			user.setEmail("user" + i + "@gmail.com");
			user.setPhoneNumber(securityUtils.getPhoneNumberString());
			user.setRegisterToken(securityUtils.getEncodedRandomString());
			user.setUsername(username);
			user.setPassword(securityUtils.encode(username));
			user.setRoles(rolesUser);
			user.setRecoverPaswordToken(securityUtils.getEncodedRandomString());
			user = userService.save(user, flush);
			initProfile(user, profileWrapper, countries, cities, states, beginTime, diff);
		}

		List<SampleNomenclature> sampleNomenclatures = sampleNomenclatureService.findAll();
		List<SpecializationSample> specializationSamples = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			SpecializationSample specializationSample = new SpecializationSample();
			specializationSample.setPercent(new Random().nextInt((100 - 10) + 1) + 10);
			specializationSample.setSampleNomenclature(sampleNomenclatures.get(getRandom(sampleNomenclatures.size())));
			specializationSamples.add(specializationSampleService.save(specializationSample, flush));
		}

		Set<Faculty> tmp = new HashSet<>();
		for (int a = 0; a < 50; a++) {
			Faculty faculty = faculties.get(getRandom(faculties.size()));
			if (!tmp.add(faculty)) {
				continue;
			}
			List<FacultyDomainNomenclature> facultyDomainNomenclatures = faculty.getFacultyDomainNomenclatures();
			List<FacultySpecializationNomenclature> facultySpecializationNomenclatures = new ArrayList<FacultySpecializationNomenclature>();
			for (FacultyDomainNomenclature facultyDomainNomenclature : facultyDomainNomenclatures) {
				facultySpecializationNomenclatures
						.addAll(facultyDomainNomenclature.getFacultySpecializationNomenclatures());
			}

			List<AdmissionSession> admissionSessions = new ArrayList<AdmissionSession>();
			for (int i = 0; i < 15; i++) {
				AdmissionSession admissionSession = new AdmissionSession();
				admissionSession.setEnabled(ThreadLocalRandom.current().nextBoolean());
				admissionSession.setName("Admission session for " + faculty.getName() + " " + i);
				admissionSession.setExpirationDate(getDate());

				List<AdmissionSpecialization> admissionSpecializations = new ArrayList<AdmissionSpecialization>();
				for (int j = 0; j < 15; j++) {
					AdmissionSpecialization admissionSpecialization = new AdmissionSpecialization();
					FacultySpecializationNomenclature facultySpecializationNomenclature = facultySpecializationNomenclatures
							.get(getRandom(facultySpecializationNomenclatures.size()));
					SpecializationSample specializationSample = specializationSamples
							.get(getRandom(specializationSamples.size()));
					admissionSpecialization.setNumberOfPlaces(new Random().nextInt(100 - 30) + 30);
					admissionSpecialization.setFacultySpecializationNomenclature(facultySpecializationNomenclature);
					admissionSpecialization.setSpecializationType(SpecializationType.getRandomSpecializationType());
					admissionSpecialization.setSpecializationSample(specializationSample);
					admissionSpecializations.add(admissionSpecializationService.save(admissionSpecialization, flush));
				}
				admissionSession.setAdmissionSpecializations(admissionSpecializations);
				admissionSessions.add(admisionSessionService.save(admissionSession, flush));
			}
			faculty.setAdmissionSessions(admissionSessions);
			facultyService.save(faculty, flush);
		}

	}

	private void initProfile(User user, ProfileWrapper profileWrapper, List<Country> countries, List<City> cities,
			List<State> states, long beginTime, long diff) {
		Profile profile = new Profile();
		profile.setCreationDate(getDate());
		profile.setBirthDate(getBirthDate(beginTime, diff));
		profile.setStreet(securityUtils.getSmallRandomString());
		profile.setFirstName(securityUtils.getSmallRandomString());
		profile.setLastName(securityUtils.getSmallRandomString());
		profile.setFatherFirstName(securityUtils.getSmallRandomString());
		profile.setFatherLastName(securityUtils.getSmallRandomString());
		profile.setMotherFirstName(securityUtils.getSmallRandomString());
		profile.setMotherLastName(securityUtils.getSmallRandomString());
		profile.setInitialsName(RandomStringUtils.randomAlphabetic(1).toUpperCase());
		profile.setCity(cities.get(getRandom(cities.size())));
		profile.setCountry(countries.get(getRandom(countries.size())));
		profile.setState(states.get(getRandom(states.size())));

		List<Citizenship> citizenships = profileWrapper.getCitizenships();
		profile.setCitizenship(citizenships.get(getRandom(citizenships.size())));

		List<Religion> religions = profileWrapper.getReligions();
		profile.setReligion(religions.get(getRandom(religions.size())));

		List<SocialSituation> socialSituations = profileWrapper.getSocialSituations();
		profile.setSocialSituation(socialSituations.get(getRandom(socialSituations.size())));

		List<FamilySituation> familySituations = profileWrapper.getFamilySituations();
		profile.setFamilySituation(familySituations.get(getRandom(familySituations.size())));

		List<MedicalCondition> medicalConditions = profileWrapper.getMedicalConditions();
		profile.setMedicalCondition(medicalConditions.get(getRandom(medicalConditions.size())));

		List<Gender> genders = profileWrapper.getGenders();
		profile.setGender(genders.get(getRandom(genders.size())));

		List<Ethnicity> ethnicities = profileWrapper.getEthnicities();
		profile.setEthnicity(ethnicities.get(getRandom(ethnicities.size())));

		List<MaritalStatus> maritalStatus = profileWrapper.getMaritalStatus();
		profile.setMaritalStatus(maritalStatus.get(getRandom(maritalStatus.size())));

		profile.setUser(user);
		user.setProfile(profile);
		userService.save(user, true);
	}

	int getRandom(int max) {
		if (max == 0) {
			return 0;
		}

		return new Random().nextInt(max);
	}

	Date getBirthDate(long beginTime, long diff) {
		return new Date(beginTime + (long) (Math.random() * diff));
	}

	Date getDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, getRandom(28));
		return cal.getTime();
	}

	private void initSampleNomenclature(boolean flush) {
		SampleNomenclature sampleNomenclature = new SampleNomenclature("Nota BAC", NomenclatureType.BACCALUAREATE);
		SampleNomenclature sampleNomenclature1 = new SampleNomenclature("Interview", NomenclatureType.INTERVIEW);
		SampleNomenclature sampleNomenclature2 = new SampleNomenclature("Other", NomenclatureType.OTHER);
		SampleNomenclature sampleNomenclature3 = new SampleNomenclature("Transcript", NomenclatureType.TRANSCRIPT);
		SampleNomenclature sampleNomenclature4 = new SampleNomenclature("Written test", NomenclatureType.WRITTEN_TEST);
		sampleNomenclatureService.save(sampleNomenclature, flush);
		sampleNomenclatureService.save(sampleNomenclature1, flush);
		sampleNomenclatureService.save(sampleNomenclature2, flush);
		sampleNomenclatureService.save(sampleNomenclature3, flush);
		sampleNomenclatureService.save(sampleNomenclature4, flush);
	}

	private void initProfileData(boolean flush) {
		// Initialize gender
		genderService.save(new Gender("Female"), flush);
		genderService.save(new Gender("Male"), flush);
		genderService.save(new Gender("Other"), flush);

		// Initialize citizenship
		citizenshipService.save(new Citizenship("Romanian"), flush);
		citizenshipService.save(new Citizenship("English"), flush);
		citizenshipService.save(new Citizenship("American"), flush);
		citizenshipService.save(new Citizenship("Other"), flush);

		// Initialize ethnicity
		ethnicityService.save(new Ethnicity("Romanian"), flush);
		ethnicityService.save(new Ethnicity("English"), flush);
		ethnicityService.save(new Ethnicity("Other"), flush);

		// Initialize family situation
		familySituationService.save(new FamilySituation("Orphan by a parent"), flush);
		familySituationService.save(new FamilySituation("Orphan by both parents"), flush);
		familySituationService.save(new FamilySituation("Other"), flush);

		// Initialize marital status
		maritalStatusService.save(new MaritalStatus("Married"), flush);
		maritalStatusService.save(new MaritalStatus("Unmarried"), flush);
		maritalStatusService.save(new MaritalStatus("Divorced"), flush);
		maritalStatusService.save(new MaritalStatus("Widow(er)"), flush);

		// Initialize religion
		religionService.save(new Religion("Orthodox"), flush);
		religionService.save(new Religion("Catholic"), flush);
		religionService.save(new Religion("Other"), flush);

		// Initialize medical condition
		medicalConditionService.save(new MedicalCondition("Grade 1 handicap"), flush);
		medicalConditionService.save(new MedicalCondition("Grade 2 handicap"), flush);
		medicalConditionService.save(new MedicalCondition("Serious and incurable diseases"), flush);

		// Initialize social situation
		socialSituationService.save(new SocialSituation("Institutionalized"), flush);
		socialSituationService.save(new SocialSituation("In placement"), flush);
		socialSituationService.save(new SocialSituation("Derived from the single parent family"), flush);
		socialSituationService.save(new SocialSituation("Other"), flush);
	}

	/*
	 * private void initUniversities(boolean flush) { ClassLoader classLoader =
	 * getClass().getClassLoader(); File universitiesFile = new
	 * File(classLoader.getResource("init/universities.csv").getFile()); try {
	 * List<String> allLines =
	 * Files.readAllLines(Paths.get(universitiesFile.getAbsolutePath())); int
	 * counter = 0; for (String line : allLines) { String[] tmp = line.split(",");
	 * ContactInformation contactInformation = new ContactInformation("university" +
	 * counter + "@gmail.com", "0720123456"); University university = new
	 * University(tmp[0], "Description text", tmp[1], contactInformation);
	 * 
	 * Set<Faculty> faculties = new HashSet<>(); for (int i = 0; i < 10; i++) {
	 * Set<SpecializationNomenclature> specializations = new HashSet<>(); for (int j
	 * = 0; j < 10; j++) { SpecializationNomenclature specializationNomenclature =
	 * new SpecializationNomenclature( "Specialization name" + j,
	 * "Specialization description", "Specialization domain");
	 * specializations.add(specializationNomenclature); } Faculty faculty = new
	 * Faculty("Faculty " + counter + i, "Description", "http://google.ro", new
	 * ContactInformation("faculty" + counter + i + "@gmail.com", "0250123412"),
	 * specializations); faculties.add(faculty); }
	 * university.setFaculties(faculties); universityService.save(university,
	 * flush); counter += 1; } } catch (Exception e) { e.printStackTrace(); } }
	 */

	private void initNomenclator(boolean flush) throws Exception {
		int tdLengthFaculty = 7;
		int tdLengthDomain = 6;
		int tdLengthSpecialization = 5;
		int tdLengthUniversity = 1;

		ClassLoader classLoader = getClass().getClassLoader();
		File nomenclatorFile = new File(classLoader.getResource("init/nomenclator.xml").getFile());
		InputStream inputStream = new FileInputStream(nomenclatorFile);
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		InputSource is = new InputSource(reader);
		is.setEncoding("UTF-8");

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(is);
			document.getDocumentElement().normalize();
			Element documentElement = document.getDocumentElement();
			NodeList tbodyElements = documentElement.getElementsByTagName("tbody");
			if (tbodyElements != null && tbodyElements.getLength() == 1) {
				Element tbodyElement = (Element) tbodyElements.item(0);
				if (tbodyElement != null) {
					NodeList trElements = tbodyElement.getElementsByTagName("tr");
					if (trElements != null && trElements.getLength() > 0) {
						int trElementslength = trElements.getLength();
						University university = null;
						List<University> universitiesList = new ArrayList<>();
						for (int i = 0; i < trElementslength; i++) {
							Node currentNode = trElements.item(i);
							if (currentNode != null && currentNode.getNodeType() == Node.ELEMENT_NODE) {
								NodeList tdElements = ((Element) currentNode).getElementsByTagName("td");
								if (tdElements != null) {
									int tdElementsLength = tdElements.getLength();
									// That means we have a university name
									if (tdElementsLength == tdLengthUniversity) {
										NodeList universityNode = ((Element) tdElements.item(0))
												.getElementsByTagName("strong");
										if (universityNode != null) {
											String universityName = universityNode.item(0).getTextContent();
											university = createUniversity(universityName);
											universitiesList.add(university);
										}
									}

									if (tdElementsLength == tdLengthFaculty) {
										String facultyName = tdElements.item(0).getTextContent();
										String domainName = tdElements.item(1).getTextContent();
										String specializationName = tdElements.item(2).getTextContent();
										String accreditation = tdElements.item(3).getTextContent();
										String formOfLearning = tdElements.item(4).getTextContent();
										String numberOfCredits = tdElements.item(5).getTextContent();
										String numberOfPlaces = tdElements.item(6).getTextContent();
										FacultyDomainNomenclature facultyDomainNomenclature = createFacultyDomainNomenclature(
												domainName);
										FacultySpecializationNomenclature facultySpecializationNomenclature = createFacultySpecialization(
												specializationName, accreditation, formOfLearning, numberOfCredits,
												numberOfPlaces);
										addFaculty(university, facultyName, facultyDomainNomenclature,
												facultySpecializationNomenclature);
									}

									if (tdElementsLength == tdLengthDomain) {
										String domainName = tdElements.item(0).getTextContent();
										String specializationName = tdElements.item(1).getTextContent();
										String accreditation = tdElements.item(2).getTextContent();
										String formOfLearning = tdElements.item(3).getTextContent();
										String numberOfCredits = tdElements.item(4).getTextContent();
										String numberOfPlaces = tdElements.item(5).getTextContent();
										FacultyDomainNomenclature facultyDomainNomenclature = createFacultyDomainNomenclature(
												domainName);
										FacultySpecializationNomenclature facultySpecializationNomenclature = createFacultySpecialization(
												specializationName, accreditation, formOfLearning, numberOfCredits,
												numberOfPlaces);
										addDomainAndSpecialization(university, facultyDomainNomenclature,
												facultySpecializationNomenclature);
									}

									if (tdElementsLength == tdLengthSpecialization) {
										String specializationName = tdElements.item(0).getTextContent();
										String accreditation = tdElements.item(1).getTextContent();
										String formOfLearning = tdElements.item(2).getTextContent();
										String numberOfCredits = tdElements.item(3).getTextContent();
										String numberOfPlaces = tdElements.item(4).getTextContent();
										FacultySpecializationNomenclature facultySpecializationNomenclature = createFacultySpecialization(
												specializationName, accreditation, formOfLearning, numberOfCredits,
												numberOfPlaces);
										addSpecialization(university, facultySpecializationNomenclature);
									}
								}
							}
						}

						universityService.saveUniversities(universitiesList, flush);
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void addSpecialization(University university,
			FacultySpecializationNomenclature facultySpecializationNomenclature) {
		List<Faculty> faculties = university.getFaculties();
		Faculty faculty = faculties.get(faculties.size() - 1);
		List<FacultyDomainNomenclature> facultyDomainNomenclatures = faculty.getFacultyDomainNomenclatures();
		FacultyDomainNomenclature facultyDomainNomenclature = facultyDomainNomenclatures
				.get(facultyDomainNomenclatures.size() - 1);
		facultyDomainNomenclature.getFacultySpecializationNomenclatures().add(facultySpecializationNomenclature);
	}

	private void addDomainAndSpecialization(University university, FacultyDomainNomenclature facultyDomainNomenclature,
			FacultySpecializationNomenclature facultySpecializationNomenclature) {
		List<Faculty> faculties = university.getFaculties();
		Faculty faculty = faculties.get(faculties.size() - 1);
		List<FacultySpecializationNomenclature> facultySpecializationNomenclatures = new ArrayList<>();
		facultySpecializationNomenclatures.add(facultySpecializationNomenclature);
		facultyDomainNomenclature.setFacultySpecializationNomenclatures(facultySpecializationNomenclatures);
		faculty.getFacultyDomainNomenclatures().add(facultyDomainNomenclature);
	}

	private FacultyDomainNomenclature createFacultyDomainNomenclature(String domainName) {
		domainName = StringUtils.removeNewLineTabsAndEnter(domainName);
		return new FacultyDomainNomenclature(domainName, domainName + " " + DESCRIPTION, URL);
	}

	private void addFaculty(University university, String facultyName,
			FacultyDomainNomenclature facultyDomainNomenclature,
			FacultySpecializationNomenclature facultySpecializationNomenclature) {
		facultyName = StringUtils.removeNewLineTabsAndEnter(facultyName);
		Faculty faculty = new Faculty(facultyName, facultyName + " " + DESCRIPTION, URL,
				new ContactInformation(EMAIL, NUMBER));

		List<FacultySpecializationNomenclature> facultySpecializationNomenclatures = new ArrayList<>();
		facultySpecializationNomenclatures.add(facultySpecializationNomenclature);

		List<FacultyDomainNomenclature> facultyDomainNomenclatures = new ArrayList<>();
		facultyDomainNomenclatures.add(facultyDomainNomenclature);
		facultyDomainNomenclature.setFacultySpecializationNomenclatures(facultySpecializationNomenclatures);

		faculty.setFacultyDomainNomenclatures(facultyDomainNomenclatures);
		university.getFaculties().add(faculty);
	}

	private FacultySpecializationNomenclature createFacultySpecialization(String specializationName,
			String accreditation, String formOfLearning, String numberOfCredits, String numberOfPlaces) {
		specializationName = StringUtils.removeNewLineTabsAndEnter(specializationName);
		int places = Integer.valueOf(numberOfPlaces);
		int credits = Integer.valueOf(numberOfCredits);
		FacultySpecializationNomenclature facultySpecializationNomenclature = new FacultySpecializationNomenclature(
				null, specializationName, specializationName + " " + DESCRIPTION, URL, places, formOfLearning,
				accreditation, credits);
		facultySpecializationNomenclature.setContactInformation(new ContactInformation(EMAIL, NUMBER));
		return facultySpecializationNomenclature;
	}

	private University createUniversity(String universityName) {
		universityName = StringUtils.removeNewLineTabsAndEnter(universityName);
		University university = new University(universityName, universityName + " " + DESCRIPTION, URL,
				new ContactInformation(EMAIL, NUMBER));
		university.setFaculties(new ArrayList<>());
		return university;
	}

}
