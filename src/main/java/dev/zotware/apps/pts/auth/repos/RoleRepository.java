package dev.zotware.apps.pts.auth.repos;

import dev.zotware.apps.pts.auth.ERole;
import dev.zotware.apps.pts.auth.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);

}