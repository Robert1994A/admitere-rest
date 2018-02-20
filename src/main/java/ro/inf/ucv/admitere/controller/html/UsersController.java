package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController extends BaseController {
	@GetMapping("/pages/users.html")
	public String getUsersHTML() {
		return "template/users";
	}
	
	@GetMapping("/pages/add_user.html")
	public String getAddUsesHTML() {
		return "template/add_user";
	}


	@GetMapping("/modals/userDetails.html")
	public String getUserDetailsModal() {
		return "modals/userDetails";
	}

	@GetMapping("/modals/userProfile.html")
	public String getUserProfileModal() {
		return "modals/userProfile";
	}
}
