package cn.eastseven.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author d7
 */
@Repository
public interface LocationDao extends MongoRepository<LocationEntity, String> {
}
