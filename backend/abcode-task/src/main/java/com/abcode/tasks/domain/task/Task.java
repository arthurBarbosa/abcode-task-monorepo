package com.abcode.tasks.domain.task;

import com.abcode.tasks.domain.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@EntityListeners(TaskListenter.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Descricao obrigatoria")
    @Length(min = 3, max = 40, message = "O tamanho da tarefa 'e invalido")
    private String description;

    @NotNull(message = "A data da tarefa 'e obrigatoria")
    @FutureOrPresent(message = "A data da tarefa n~ao pode estar no passado")
    private LocalDate whenToDo;

    private Boolean done = false;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    @NotNull(message = "O usuario da tarefa 'e obrigatorio")
    @JsonIgnore
    private AppUser appUser;

    public Task() {

    }

    public Task(Integer id, String description, LocalDate whenToDo, Boolean done) {
        this.description = description;
        this.whenToDo = whenToDo;
        this.done = done;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getWhenToDo() {
        return whenToDo;
    }

    public void setWhenToDo(LocalDate whenToDo) {
        this.whenToDo = whenToDo;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
