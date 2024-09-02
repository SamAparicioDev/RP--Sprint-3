package com.example.task.webflux.models;

import com.example.task.webflux.dto.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("task")
@Getter
@ToString
public class TaskEntity {
    @Id
    private Long id;
    @Setter
    private String title, description;
    @Setter
    private StatusEnum status;
    @Setter
    private Long idUser;
    public TaskEntity() {

    }
    public TaskEntity(Long id, String title, String description,StatusEnum status, Long idUser) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.idUser = idUser;
    }
    public TaskEntity( String title, String description,  StatusEnum status,Long idUser) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.idUser = idUser;
    }

}
