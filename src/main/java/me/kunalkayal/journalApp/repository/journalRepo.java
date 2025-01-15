package me.kunalkayal.journalApp.repository;
import me.kunalkayal.journalApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface journalRepo extends MongoRepository<JournalEntity, ObjectId> {
}
