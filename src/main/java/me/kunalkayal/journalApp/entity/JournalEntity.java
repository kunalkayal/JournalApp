package me.kunalkayal.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

//@Component
@Document("journalEntry")
@Data
@NoArgsConstructor
public class JournalEntity {
    @Id
    ObjectId journalId;
    String journalTitle;
    String content;
    LocalDateTime date;
}
