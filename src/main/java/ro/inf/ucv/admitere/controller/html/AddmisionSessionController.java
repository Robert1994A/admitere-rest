package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddmisionSessionController extends BaseController {
	// HTML VERSION
	@GetMapping("/pages/add_session.html")
	public String getAddSessionPage() {
		return "template/add_session";
	}

	// HTML VERSION
	@GetMapping("/pages/admission_session.html")
	public String getSessionPage() {
		return "template/admission_session";
	}

}