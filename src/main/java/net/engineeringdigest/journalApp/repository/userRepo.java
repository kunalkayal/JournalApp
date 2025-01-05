package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

public interface userRepo extends MongoRepository<UserEntity, ObjectId> {
    UserEntity findByUsername(String username);

    void deleteByUsername(String username);
}
