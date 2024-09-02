package com.example.task.webflux.services;

import com.example.task.webflux.dto.TaskDTO;
import com.example.task.webflux.models.TaskEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {
    Mono<TaskEntity> getTaskById(Long id);
    Flux<TaskEntity> getAllTasks();
    Mono<TaskEntity> saveTask(TaskDTO task);
    Mono<TaskEntity> updateTaskById(Long id,TaskDTO task);
    Mono<Void> deleteTaskById(Long id);
    Flux<TaskEntity> findByIdUser(Long idUser);

}
