package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.JournalEntity;
import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.repository.journalRepo;
import net.engineeringdigest.journalApp.repository.userRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public class EntryService {
    @Autowired
    journalRepo jRepo;
    @Autowired
    userRepo uRepo;

    public List<JournalEntity> getJournals() {return jRepo.findAll();}

    public List<JournalEntity> getUsersJournals(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = uRepo.findByUsername(username);
        return  user.getJournalEntityList();
    }

    @Transactional
    public void addJournal(JournalEntity journal) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = uRepo.findByUsername(username);

        JournalEntity save = jRepo.save(journal);
        user.getJournalEntityList().add(save);
        uRepo.save(user);

    }

    @Transactional
    public boolean deleteJournalById(ObjectId id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = uRepo.findByUsername(username);


        JournalEntity save = null;
        for(JournalEntity journal: user.getJournalEntityList()){if(journal.getJournalId().equals(id)){save = journal;break;}}

        if(save == null){return false;}
        jRepo.deleteById(id);
        if(!user.getJournalEntityList().remove(save))return false;
        uRepo.save(user);
        return true;

    }



    public JournalEntity updateJournal(ObjectId id, JournalEntity newJournal) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<JournalEntity> journalList = uRepo.findByUsername(username).getJournalEntityList();
        JournalEntity oldEntity = null;
        for(JournalEntity journal: journalList){if(journal.getJournalId().equals(id)){oldEntity = journal;break;}}

        if (oldEntity== null) {return null;}
        else {
            oldEntity.setJournalTitle(newJournal.getJournalTitle()!=null?newJournal.getJournalTitle():oldEntity.getJournalTitle());
            oldEntity.setContent(newJournal.getContent()!=null?newJournal.getContent():oldEntity.getContent());
            jRepo.save(oldEntity);
            return oldEntity;
        }
    }


}
