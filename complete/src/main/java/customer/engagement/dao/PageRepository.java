package customer.engagement.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import customer.engagement.dto.Page;

@Repository
public interface PageRepository extends MongoRepository<Page, String> {

}
