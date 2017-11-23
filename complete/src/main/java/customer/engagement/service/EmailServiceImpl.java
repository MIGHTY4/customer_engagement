package customer.engagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import customer.engagement.dto.EmailDTO;

@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender sender;

	@Override
	public boolean send(EmailDTO email) {

		boolean isSuccess = true;
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setTo(email.getTo());
			messageHelper.setSubject(email.getSubject());
			messageHelper.setText(email.getBody());
		};
		try {
			sender.send(messagePreparator);
		} catch (MailException e) {
			LOGGER.warn("Error while sending EMAIL: " + e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}
}
