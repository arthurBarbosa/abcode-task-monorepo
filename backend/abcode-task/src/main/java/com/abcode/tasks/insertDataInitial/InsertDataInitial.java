package com.abcode.tasks.insertDataInitial;

import com.abcode.tasks.domain.task.Task;
import com.abcode.tasks.domain.task.TaskRepository;
import com.abcode.tasks.domain.user.AppUser;
import com.abcode.tasks.domain.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InsertDataInitial {

    private AppUserRepository appUserRepository;
    private TaskRepository taskRepository;

    @Autowired
    public InsertDataInitial(AppUserRepository appUserRepository, TaskRepository taskRepository) {
        this.appUserRepository = appUserRepository;
        this.taskRepository = taskRepository;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        AppUser appUser = new AppUser(null, "jhon", encoder.encode("abc"), "Jhon coder");
        appUserRepository.save(appUser);

        LocalDate baseDate = LocalDate.parse("2025-03-16");
        for (int i = 1; i <= 10; i++) {
            Task task = new Task(null, "Tarefa #" + i, baseDate.plusDays(i), false);
            task.setAppUser(appUser);
            taskRepository.save(task);
        }


    }
}
