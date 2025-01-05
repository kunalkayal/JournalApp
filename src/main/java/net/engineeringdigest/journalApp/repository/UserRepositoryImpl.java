package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserRepositoryImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<UserEntity> getUsersForSA(){

        Query query= new Query();
        query.addCriteria(Criteria.where("email").exists(true));
        query.addCriteria(Criteria.where("semanticAnalysis").is(true));
        List<UserEntity> users = mongoTemplate.find(query, UserEntity.class);
        return users;
    }
}
