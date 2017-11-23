package customer.engagement.service;

import customer.engagement.dto.EmailDTO;

public interface EmailService {

	public boolean send(EmailDTO email);

}
