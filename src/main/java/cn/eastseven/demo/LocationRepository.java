package cn.eastseven.demo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author d7
 */
@Repository
public interface LocationRepository extends ReactiveMongoRepository<LocationEntity, String> {
}
