/**
 * 
 */
package customer.engagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import customer.engagement.dto.EmailDTO;
import customer.engagement.service.EmailService;

/**
 * @author abdul
 *
 */
@Controller
@RequestMapping("/email")
public class EmailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	private EmailService emailService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public boolean senEmail(@RequestBody EmailDTO emailDTO) {
		return emailService.send(emailDTO);
	}

}
