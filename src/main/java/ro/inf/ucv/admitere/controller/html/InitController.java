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

	public void initDatabaseUsers(int numberOfUsers, boolean flush) throws Exception {
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleAdmin.setCreationDate(new Date());
		roleService.save(roleAdmin, flush);

		Role roleFaculty = new Role();
		roleFaculty.setName("ROLE_FACULTY");
		roleFaculty.setCreationDate(new Date());
		roleService.save(roleFaculty, flush);

		Role roleUniversity = new Role();
		roleUniversity.setName("ROLE_UNIVERSITY");
		roleUniversity.setCreationDate(new Date());
		roleService.save(roleUniversity, flush);

		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
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
			userService.save(userAdmin, flush);
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
			userService.save(moderator, flush);
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
			userService.save(userFaculty, flush);
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
			userService.save(user, flush);
		}
	}

	@GetMapping("/init")
	public void home(Model model) throws Exception {
		if (userService.count() == 0) {
			initDatabaseUsers(30, true);
			initProfileData(true);
			initSampleNomenclature(true);
			initUniversities(true);
		}
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

	private void initUniversities(boolean flush) {
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
				universityService.save(university, flush);
				counter += 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
}
