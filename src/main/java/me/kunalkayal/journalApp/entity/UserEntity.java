package me.kunalkayal.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.LinkedList;
import java.util.List;

//@Component
@Document("userEntry")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;
    private String email;
    private boolean semanticAnalysis;

    private List<String> roles = new LinkedList<>();

    @DBRef
    List<JournalEntity> journalEntityList = new LinkedList<>();
}
