package ro.inf.ucv.admitere.controller.html;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.inf.ucv.admitere.entity.Citizenship;
import ro.inf.ucv.admitere.entity.ContactInformation;
import ro.inf.ucv.admitere.entity.Ethnicity;
import ro.inf.ucv.admitere.entity.Faculty;
import ro.inf.ucv.admitere.entity.FamilySituation;
import ro.inf.ucv.admitere.entity.Gender;
import ro.inf.ucv.admitere.entity.MaritalStatus;
import ro.inf.ucv.admitere.entity.MedicalCondition;
import ro.inf.ucv.admitere.entity.Religion;
import ro.inf.ucv.admitere.entity.Role;
import ro.inf.ucv.admitere.entity.SampleNomenclature;
import ro.inf.ucv.admitere.entity.SocialSituation;
import ro.inf.ucv.admitere.entity.SpecializationNomenclature;
import ro.inf.ucv.admitere.entity.University;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.enumerations.NomenclatureType;

/**
 * Handles requests for the application home page.
 */
@Controller
public class InitController extends BaseController {

	public void initDatabaseUsers(int numberOfUsers) throws Exception {
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleAdmin.setCreationDate(new Date());
		roleService.save(roleAdmin);

		Role roleFaculty = new Role();
		roleFaculty.setName("ROLE_FACULTY");
		roleFaculty.setCreationDate(new Date());
		roleService.save(roleFaculty);

		Role roleUniversity = new Role();
		roleUniversity.setName("ROLE_UNIVERSITY");
		roleUniversity.setCreationDate(new Date());
		roleService.save(roleUniversity);

		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleUser.setCreationDate(new Date());
		roleService.save(roleUser);

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

		for (int i = 0; i < numberOfUsers; i++) {
			String username = stringGenerator.getUsernameString();
			User userAdmin = new User();
			userAdmin.setEnabled(true);
			userAdmin.setCreationDate(new Date());
			userAdmin.setEmail("admin" + i + "@gmail.com");
			userAdmin.setRegisterToken(generateString());
			userAdmin.setUsername(username);
			userAdmin.setPassword(encoder.encode(username));
			userAdmin.setRoles(rolesAdmin);
			userAdmin.setRecoverPaswordToken(generateString());
			userService.save(userAdmin);
		}

		for (int i = 0; i < numberOfUsers; i++) {
			String username = stringGenerator.getUsernameString();
			User moderator = new User();
			moderator.setEnabled(true);
			moderator.setCreationDate(new Date());
			moderator.setEmail("faculty" + i + "@gmail.com");
			moderator.setRegisterToken(generateString());
			moderator.setUsername(username);
			moderator.setPassword(encoder.encode(username));
			moderator.setRoles(rolesFaculty);
			moderator.setRecoverPaswordToken(generateString());
			userService.save(moderator);
		}

		for (int i = 0; i < numberOfUsers; i++) {
			String username = stringGenerator.getUsernameString();
			User userFaculty = new User();
			userFaculty.setEnabled(true);
			userFaculty.setCreationDate(new Date());
			userFaculty.setEmail("university" + i + "@gmail.com");
			userFaculty.setRegisterToken(generateString());
			userFaculty.setUsername(username);
			userFaculty.setPassword(encoder.encode(username));
			userFaculty.setRoles(rolesFaculty);
			userFaculty.setRecoverPaswordToken(generateString());
			userService.save(userFaculty);
		}

		for (int i = 0; i < numberOfUsers; i++) {
			String username = stringGenerator.getUsernameString();
			User user = new User();
			user.setCreationDate(new Date());
			user.setEnabled(new Random().nextBoolean());
			user.setEmail("user" + i + "@gmail.com");
			user.setRegisterToken(generateString());
			user.setUsername(username);
			user.setPassword(username);
			user.setRoles(rolesUser);
			user.setRecoverPaswordToken(generateString());
			userService.save(user);
		}
	}

	String generateString() {
		return encoder.encode(stringGenerator.getRandomString());
	}

	@GetMapping("/init")
	public void home(Model model) throws Exception {
		if (userService.count() == 0) {
			initDatabaseUsers(30);
			initProfileData();
			initSampleNomenclature();
			initUniversities();
		}
	}

	private void initSampleNomenclature() {
		SampleNomenclature sampleNomenclature = new SampleNomenclature("Nota BAC", NomenclatureType.BACCALUAREATE);
		SampleNomenclature sampleNomenclature1 = new SampleNomenclature("Interview", NomenclatureType.INTERVIEW);
		SampleNomenclature sampleNomenclature2 = new SampleNomenclature("Other", NomenclatureType.OTHER);
		SampleNomenclature sampleNomenclature3 = new SampleNomenclature("Transcript", NomenclatureType.TRANSCRIPT);
		SampleNomenclature sampleNomenclature4 = new SampleNomenclature("Written test", NomenclatureType.WRITTEN_TEST);
		sampleNomenclatureService.saveAndFlush(sampleNomenclature);
		sampleNomenclatureService.saveAndFlush(sampleNomenclature1);
		sampleNomenclatureService.saveAndFlush(sampleNomenclature2);
		sampleNomenclatureService.saveAndFlush(sampleNomenclature3);
		sampleNomenclatureService.saveAndFlush(sampleNomenclature4);
	}

	private void initUniversities() {
		ClassLoader classLoader = getClass().getClassLoader();
		File universitiesFile = new File(classLoader.getResource("init/universities.csv").getFile());
		try {
			List<String> allLines = Files.readAllLines(Paths.get(universitiesFile.getAbsolutePath()));
			int counter = 0;
			for (String line : allLines) {
				String[] tmp = line.split(",");
				ContactInformation contactInformation = new ContactInformation("university" + counter + "@gmail.com",
						"0720123456");
				University university = new University(tmp[0], "Description text", tmp[1], contactInformation);

				Set<Faculty> faculties = new HashSet<>();
				for (int i = 0; i < 10; i++) {
					Set<SpecializationNomenclature> specializations = new HashSet<>();
					for (int j = 0; j < 10; j++) {
						SpecializationNomenclature specializationNomenclature = new SpecializationNomenclature(
								"Specialization name" + j, "Specialization description", "Specialization domain");
						specializations.add(specializationNomenclature);
					}
					Faculty faculty = new Faculty("Faculty " + counter + i, "Description", "http://google.ro",
							new ContactInformation("faculty" + counter + i + "@gmail.com", "0250123412"),
							specializations);
					faculties.add(faculty);
				}
				university.setFaculties(faculties);
				universityService.save(university);
				counter += 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initProfileData() {
		// Initialize gender
		genderService.saveAndFlush(new Gender("Female"));
		genderService.saveAndFlush(new Gender("Male"));
		genderService.saveAndFlush(new Gender("Other"));

		// Initialize citizenship
		citizenshipService.saveAndFlush(new Citizenship("Romanian"));
		citizenshipService.saveAndFlush(new Citizenship("English"));
		citizenshipService.saveAndFlush(new Citizenship("American"));
		citizenshipService.saveAndFlush(new Citizenship("Other"));

		// Initialize ethnicity
		ethnicityService.saveAndFlush(new Ethnicity("Romanian"));
		ethnicityService.saveAndFlush(new Ethnicity("English"));
		ethnicityService.saveAndFlush(new Ethnicity("Other"));

		// Initialize family situation
		familySituationService.saveAndFlush(new FamilySituation("Orphan by a parent"));
		familySituationService.saveAndFlush(new FamilySituation("Orphan by both parents"));
		familySituationService.saveAndFlush(new FamilySituation("Other"));

		// Initialize marital status
		maritalStatusService.saveAndFlush(new MaritalStatus("Married"));
		maritalStatusService.saveAndFlush(new MaritalStatus("Unmarried"));
		maritalStatusService.saveAndFlush(new MaritalStatus("Divorced"));
		maritalStatusService.saveAndFlush(new MaritalStatus("Widow(er)"));

		// Initialize religion
		religionService.saveAndFlush(new Religion("Orthodox"));
		religionService.saveAndFlush(new Religion("Catholic"));
		religionService.saveAndFlush(new Religion("Other"));

		// Initialize medical condition
		medicalConditionService.saveAndFlush(new MedicalCondition("Grade 1 handicap"));
		medicalConditionService.saveAndFlush(new MedicalCondition("Grade 2 handicap"));
		medicalConditionService.saveAndFlush(new MedicalCondition("Serious and incurable diseases"));

		// Initialize social situation
		socialSituationService.saveAndFlush(new SocialSituation("Institutionalized"));
		socialSituationService.saveAndFlush(new SocialSituation("In placement"));
		socialSituationService.saveAndFlush(new SocialSituation("Derived from the single parent family"));
		socialSituationService.saveAndFlush(new SocialSituation("Other"));
	}

}
