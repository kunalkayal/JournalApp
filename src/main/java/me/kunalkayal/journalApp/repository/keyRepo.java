package me.kunalkayal.journalApp.repository;

import me.kunalkayal.journalApp.entity.KeyEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface keyRepo extends MongoRepository<KeyEntity, ObjectId> {
}
