package net.engineeringdigest.journalApp.cache;

import lombok.Getter;
import lombok.Setter;
import net.engineeringdigest.journalApp.entity.KeyEntity;
import net.engineeringdigest.journalApp.repository.keyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;


@Getter
@Setter
@Component
public class AppCache {
    private HashMap<String,String> appCache = new HashMap<>();
    @Autowired
    keyRepo kRepository;
    @PostConstruct
    private void getCache(){
      List<KeyEntity> all = kRepository.findAll();
      for(KeyEntity keyEntity : all){
          appCache.put(keyEntity.getKey(),keyEntity.getValue());
      }

    }
}
