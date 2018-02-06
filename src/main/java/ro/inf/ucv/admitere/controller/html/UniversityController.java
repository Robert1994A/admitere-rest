package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UniversityController extends BaseController {
	// HTML VERSION
	@GetMapping("/pages/universities.html")
	public String getUsersHTML() {
		return "template/universities";
	}
}
