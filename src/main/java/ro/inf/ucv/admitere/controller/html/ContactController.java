package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

	@GetMapping("/pages/contact.html")
	public String contactContent() {
		return "template/contact";
	}
}
