package net.engineeringdigest.journalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServices {
    @Autowired
    MailServuice mailServuice;

    @Scheduled(cron = "0 0 9 * * 0")
    public void setMailSchedule(){
        mailServuice.sendMail("kk.hacker.2.0@gmail.com", "Journal Schedule", "Journal Schedule");
    }
}
