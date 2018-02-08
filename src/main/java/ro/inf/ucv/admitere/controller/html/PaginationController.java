package ro.inf.ucv.admitere.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginationController {

	@GetMapping("/include/pagination.html")
	public String pagination() {
		return "include/pagination";
	}
}
