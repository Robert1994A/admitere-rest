package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddmisionSessionController extends BaseController {

	@GetMapping("/pages/add_session.html")
	public String getAddSessionPage() {
		return "template/add_session";
	}

	@GetMapping("/pages/admission_session.html")
	public String getSessionPage() {
		return "template/admission_session";
	}

	@GetMapping("/pages/admission_session_statistics.html")
	public String getAdmissionSessionStatisticsPage() {
		return "template/admission_session_statistics";
	}

	@GetMapping("/pages/admission_specialization_statistics.html")
	public String getAdmissionSpecializationStatisticsPage() {
		return "template/admission_specialization_statistics";
	}
	
	@GetMapping("/pages/admission_specialization.html")
	public String getAdmissionSpecializationPage() {
		return "template/admission_specialization";
	}

	@GetMapping("/pages/admission_specialization_users.html")
	public String getAdmissionSpecializationUsersPage() {
		return "template/admission_specialization_users";
	}

	@GetMapping("/pages/admission_session_users.html")
	public String getAdmissionSessionUsersPage() {
		return "template/admission_specialization_users";
	}
}