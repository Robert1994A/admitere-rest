package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModalController {

	@GetMapping("/modals/error-modal.html")
	public String errorModal() {
		return "modals/error-modal";
	}

	@GetMapping("/modals/success-modal.html")
	public String successModal() {
		return "modals/success-modal";
	}
}
