package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.KeyEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface keyRepo extends MongoRepository<KeyEntity, ObjectId> {
}
