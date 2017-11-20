package customer.engagement.service;

import java.util.List;

import customer.engagement.dto.Customer;

public interface FetchEntities {
	
	public Customer getUserDTO(String id);
	
	public Customer getUserDTOByFacebookId(String id);
	
	public Customer getUserDTOByEmail(String email);
	
	public List<Customer> getAll();

}
