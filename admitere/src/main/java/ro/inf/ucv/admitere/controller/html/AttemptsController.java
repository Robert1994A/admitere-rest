package ro.inf.ucv.admitere.controller.html;

import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AttemptsController extends BaseController {

	@RequestMapping(value = "/attempts", method=RequestMethod.GET)
	public String getAttemptsTemplatePage(Model model) {
		ConcurrentMap<String, Integer> attempts = attemptsService.getAttemptsCache().asMap();
		if (attempts == null) {
			model.addAttribute("exist", false);
		} else if(attempts.size() > 0) {
			model.addAttribute("exist", true);
			model.addAttribute("attempts", attempts);
		}else{
			model.addAttribute("exist", false);
		}
		return "/templates/attempts";
	}
}
