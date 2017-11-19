/**
 * 
 */
package customer.engagement.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import customer.engagement.domain.User;

/**
 * @author abdul
 *
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public User findOneByName(String name);

	public User findByFacebookId(String facebookId);

	public User findByEmail(String email);

}