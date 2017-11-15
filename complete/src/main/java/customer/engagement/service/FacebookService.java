package customer.engagement.service;

import org.springframework.social.facebook.api.Facebook;

import customer.engagement.dto.LoginDTO;

/**
 * Will handle the post login operation.
 * 
 * @author abdul
 *
 */
public interface FacebookService {

	public LoginDTO login(Facebook facebook);

}
