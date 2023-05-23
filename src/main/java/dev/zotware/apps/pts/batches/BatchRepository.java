package dev.zotware.apps.pts.interfaces;

import dev.zotware.apps.pts.entities.Batch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends MongoRepository<Batch, String> {

}
