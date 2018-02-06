package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController {

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/pages/home.html")
	public String homeContent() {
		return "template/home";
	}

	@GetMapping("/validation-messages.html")
	public String validatioonMessages() {
		return "message/validation-messages";
	}

	@GetMapping("/no-data-found.html")
	public String noDataFound() {
		return "message/no-data-found";
	}
}