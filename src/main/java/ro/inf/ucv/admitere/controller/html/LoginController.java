package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController extends BaseController {

	@GetMapping("/pages/login.html")
	public String login() {
		return "template/login";
	}

	@RequestMapping(value = "/login_spring_security", method = RequestMethod.GET)
	public String loginSpringSecurityGET() {
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET() {
		return login();
	}
}
