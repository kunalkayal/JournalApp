package me.kunalkayal.journalApp.repository;

import me.kunalkayal.journalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userRepo extends MongoRepository<UserEntity, ObjectId> {
    UserEntity findByUsername(String username);

    void deleteByUsername(String username);
}
