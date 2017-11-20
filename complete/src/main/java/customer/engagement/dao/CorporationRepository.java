package customer.engagement.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import customer.engagement.dto.Corporation;

@Repository
public interface CorporationRepository extends MongoRepository<Corporation, String>{

}
