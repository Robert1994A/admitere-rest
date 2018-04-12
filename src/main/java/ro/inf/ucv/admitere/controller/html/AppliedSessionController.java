package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppliedSessionController {

	@GetMapping("/pages/applied_sessions.html")
	public String aboutContent() {
		return "template/applied_sessions";
	}
}
