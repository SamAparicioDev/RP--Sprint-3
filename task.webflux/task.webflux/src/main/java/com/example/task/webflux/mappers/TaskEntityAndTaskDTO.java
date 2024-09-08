package com.example.task.webflux.mappers;

import com.example.task.webflux.dto.TaskDTO;
import com.example.task.webflux.models.TaskEntity;
import org.springframework.stereotype.Component;


@Component
public class TaskEntityAndTaskDTO {
    public static TaskEntity taskDTOToTaskEntity(TaskDTO taskDTO) {
        return new TaskEntity(taskDTO.title(), taskDTO.description(), taskDTO.status(), taskDTO.idUser());
    }
    public static TaskDTO taskEntityToTaskDTO(TaskEntity taskEntity) {
        return new TaskDTO(taskEntity.getTitle(), taskEntity.getDescription(), taskEntity.getStatus(), taskEntity.getIdUser());
    }
}
