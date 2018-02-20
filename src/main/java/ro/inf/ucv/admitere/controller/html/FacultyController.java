package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacultyController extends BaseController {
	// HTML VERSION
	@GetMapping("/pages/faculties.html")
	public String getFacultiesPage() {
		return "template/faculties";
	}

	@GetMapping("/pages/faculty.html")
	public String getFacultyPage() {
		return "template/faculty";
	}

	@GetMapping("/pages/add_faculty.html")
	public String getAddFacultyPage() {
		return "template/add_faculty";
	}

	@GetMapping("/pages/add_specialization.html")
	public String getAddSpecializationPage() {
		return "template/add_specialization";
	}

	@GetMapping("/pages/domains.html")
	public String getDomainsPage() {
		return "template/domains";
	}

	@GetMapping("/pages/add_domain.html")
	public String getAddDomainPage() {
		return "template/add_domain";
	}

	@GetMapping("/pages/specializations.html")
	public String getSpecializationsPage() {
		return "template/specializations";
	}

	@GetMapping("/pages/sessions.html")
	public String getSessionsPage() {
		return "template/sessions";
	}
}