package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecoverPasswordContoller extends BaseController {

	@GetMapping("/pages/recover.html")
	public String recover() {
		return "template/recover";
	}
}
