package net.engineeringdigest.journalApp.repository;
import net.engineeringdigest.journalApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface journalRepo extends MongoRepository<JournalEntity, ObjectId> {
}
