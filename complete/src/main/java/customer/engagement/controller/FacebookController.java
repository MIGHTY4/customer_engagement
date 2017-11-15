package customer.engagement.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import customer.engagement.dto.LoginDTO;
import customer.engagement.service.FacebookService;

/**
 * Entry class for this application which will handles the Facebook login and
 * Retrieving user details.
 * 
 * @author abdul
 *
 */
@Controller
@RequestMapping("/")
public class FacebookController {

	private Facebook facebook;
	private ConnectionRepository connectionRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookController.class);
	
	@Autowired
	private FacebookService facebookService;

	public FacebookController(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
	}

	@GetMapping
	public String helloFacebook(Model model) {
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			LOGGER.info("User not logged in to Facebook. Redirecting to login page...");
			return "redirect:/connect/facebook";
		}

		LoginDTO loginDTO = facebookService.login(facebook);
		
		model.addAttribute("login", loginDTO);

		return "feed";
	}

	

}
