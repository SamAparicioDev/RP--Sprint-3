package com.example.task.webflux.services.impl;

import com.example.task.webflux.dto.TaskDTO;
import com.example.task.webflux.mappers.TaskEntityAndTaskDTO;
import com.example.task.webflux.models.TaskEntity;
import com.example.task.webflux.repositories.TaskRepository;
import com.example.task.webflux.services.TaskService;
import com.example.task.webflux.services.exceptions.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Mono<TaskEntity> getTaskById(Long id) {
        return taskRepository.findById(id).switchIfEmpty(Mono.error(new TaskNotFoundException("Task not found")));
    }

    @Override
    public Flux<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Mono<TaskEntity> saveTask(TaskDTO task) {
        return taskRepository.save(TaskEntityAndTaskDTO.taskDTOToTaskEntity(task));
    }

    @Override
    public Mono<TaskEntity> updateTaskById(Long id, TaskDTO task) {
        return taskRepository.findById(id).flatMap((existedUser) -> {
            existedUser.setIdUser(task.idUser());
            existedUser.setStatus(task.status());
            existedUser.setDescription(task.description());
            existedUser.setTitle(task.title());
            return taskRepository.save(existedUser);
        } );
    }
    @Override
    public Mono<Void> deleteTaskById(Long id) {
        return taskRepository.deleteById(id);
    }
    @Override
    public Flux<TaskEntity> findByIdUser(Long idUser) {
        return taskRepository.findByIdUser(idUser);
    }
}
