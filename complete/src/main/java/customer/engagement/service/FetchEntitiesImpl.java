package customer.engagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import customer.engagement.dao.UserRepository;
import customer.engagement.domain.User;
import customer.engagement.dto.UserDTO;

@Service
@Transactional(readOnly = true)
public class FetchEntitiesImpl implements FetchEntities {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO getUserDTO(String id) {
		UserDTO userDTO = new UserDTO();
		User user = userRepository.findOne(id);
		userToUserDTOMapping(user, userDTO);
		return userDTO;
	}

	@Override
	public UserDTO getUserDTOByFacebookId(String id) {
		UserDTO userDTO = new UserDTO();
		User user = userRepository.findByFacebookId(id);
		userToUserDTOMapping(user, userDTO);
		return userDTO;
	}

	@Override
	public UserDTO getUserDTOByEmail(String email) {
		UserDTO userDTO = new UserDTO();
		User user = userRepository.findByEmail(email);
		userToUserDTOMapping(user, userDTO);
		return userDTO;
	}

	@Override
	public List<UserDTO> getAll() {
		List<UserDTO> userDTOs = new ArrayList<>();
		UserDTO userDTO = new UserDTO();
		List<User> users = userRepository.findAll();
		for(User u: users) {
			userToUserDTOMapping(u, userDTO);
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	private void userToUserDTOMapping(customer.engagement.domain.User user, UserDTO userDTO) {

	}

}
