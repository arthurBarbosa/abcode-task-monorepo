package com.abcode.tasks.domain.task;

import com.abcode.tasks.domain.user.AppUser;
import com.abcode.tasks.domain.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PrePersist;

@Component
public class TaskListenter {

    private static AppUserRepository appUserRepository;

    @PrePersist
    public void onPrePersistHandler(Task task) {
        if (task.getAppUser() == null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            AppUser appUser = appUserRepository.findByUsername(username);

            if (appUser == null) {
                throw new EntityNotFoundException("Usuario " + username + " nao foi encontrado");
            }
            task.setAppUser(appUser);
        }
    }

    @Autowired
    public void init(AppUserRepository appUserRepository) {
        TaskListenter.appUserRepository = appUserRepository;
    }
}
