package dev.zotware.apps.pts.interfaces;

import dev.zotware.apps.pts.models.auth.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {

}
