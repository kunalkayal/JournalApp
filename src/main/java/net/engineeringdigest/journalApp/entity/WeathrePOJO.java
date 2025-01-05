package net.engineeringdigest.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class WeathrePOJO {
    @JsonProperty("current")
    private Current current;

    @Getter
    @Setter
    //@JsonProperty("current")
    public class Current {
        private int temperature;
        private int feelslike;

    }


}



