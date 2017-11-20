package customer.engagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import customer.engagement.dao.UserRepository;
import customer.engagement.domain.User;
import customer.engagement.dto.Customer;

@Service
@Transactional(readOnly = true)
public class FetchEntitiesImpl implements FetchEntities {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Customer getUserDTO(String id) {
		Customer userDTO = new Customer();
		User user = userRepository.findOne(id);
		userToUserDTOMapping(user, userDTO);
		return userDTO;
	}

	@Override
	public Customer getUserDTOByFacebookId(String id) {
		Customer userDTO = new Customer();
		User user = userRepository.findByFacebookId(id);
		userToUserDTOMapping(user, userDTO);
		return userDTO;
	}

	@Override
	public Customer getUserDTOByEmail(String email) {
		Customer userDTO = new Customer();
		User user = userRepository.findByEmail(email);
		userToUserDTOMapping(user, userDTO);
		return userDTO;
	}

	@Override
	public List<Customer> getAll() {
		List<Customer> userDTOs = new ArrayList<>();
		Customer userDTO = new Customer();
		List<User> users = userRepository.findAll();
		for(User u: users) {
			userToUserDTOMapping(u, userDTO);
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	private void userToUserDTOMapping(customer.engagement.domain.User user, Customer userDTO) {

	}

}
