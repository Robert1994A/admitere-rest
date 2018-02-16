package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UniversityController extends BaseController {
	// HTML VERSION
	@GetMapping("/pages/universities.html")
	public String getUniversitiesPage() {
		return "template/universities";
	}

	@GetMapping("/pages/university.html")
	public String getUniversityPage() {
		return "template/university";
	}

	@GetMapping("/pages/add_university.html")
	public String getAddUniversityPage() {
		return "template/add_university";
	}
}
