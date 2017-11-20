package customer.engagement.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import customer.engagement.dto.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>{

}
