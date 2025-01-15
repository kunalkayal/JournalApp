package me.kunalkayal.journalApp.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("keyEntry")
@Data
public class KeyEntity {
    private String key;
    private String value;
}
