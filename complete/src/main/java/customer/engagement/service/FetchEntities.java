package customer.engagement.service;

import java.util.List;

import customer.engagement.dto.UserDTO;

public interface FetchEntities {
	
	public UserDTO getUserDTO(String id);
	
	public UserDTO getUserDTOByFacebookId(String id);
	
	public UserDTO getUserDTOByEmail(String email);
	
	public List<UserDTO> getAll();

}
