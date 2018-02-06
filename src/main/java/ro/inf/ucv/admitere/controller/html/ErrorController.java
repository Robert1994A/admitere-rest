package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

	@GetMapping("/pages/404.html")
	public String get404ErrorPage() {
		return "message/404";
	}
}
