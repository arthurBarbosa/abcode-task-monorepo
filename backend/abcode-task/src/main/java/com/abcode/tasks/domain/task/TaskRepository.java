package com.abcode.tasks.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findByDescription(String description);

    @Override
    @Query("SELECT t FROM Task t WHERE t.appUser.username = ?#{principal}")
    List<Task> findAll();

    @Override
    @Query("SELECT t FROM Task t WHERE t.id = ?1 AND t.appUser.username = ?#{principal}")
    Optional<Task> findById(Integer integer);
}
