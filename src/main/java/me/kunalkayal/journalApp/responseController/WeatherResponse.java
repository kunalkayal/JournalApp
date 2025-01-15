package me.kunalkayal.journalApp.responseController;

import me.kunalkayal.journalApp.cache.AppCache;
import me.kunalkayal.journalApp.entity.WeathrePOJO;
import me.kunalkayal.journalApp.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherResponse {
    @Autowired
    AppCache appCache;
    @Value("${weather.api.key}")
    private  String API_KEY ;

   // private final RestTemplateBuilder restTemplateBuilder;
    @Autowired
   RestTemplate restTemplate;
    HttpEntity<String> httpEntity;

    @Autowired
    private RedisService redisService;


    public  String getWeather(String q) {
        WeathrePOJO body;
        body = redisService.get(q, WeathrePOJO.class);
        if (body == null) {
            String URL = appCache.getAppCache().get("API");
            String finalUrl = URL.replace("ACCESS_KEY", API_KEY).replace("CITY", q);
            ResponseEntity<WeathrePOJO> exchange = restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeathrePOJO.class);
            body = exchange.getBody();
            if(body == null || body.getCurrent()==null) {return "";}
            redisService.set(q,body,300l);
        }
        if(body == null || body.getCurrent()==null) {return "";}

        return ""+body.getCurrent().getFeelslike();

    }
}
