package com.example.task.webflux.controllers;

import com.example.task.webflux.dto.TaskDTO;
import com.example.task.webflux.models.TaskEntity;
import com.example.task.webflux.services.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v2/task")
public class TaskController{
    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @GetMapping
    public Flux<TaskEntity> getAllTasks() {
        return taskServiceImpl.getAllTasks();
    }
    @GetMapping("/get/{id}")
    public Mono<TaskEntity> getTaskById(@PathVariable Long id) {
        return taskServiceImpl.getTaskById(id);
    }
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteTaskById(@PathVariable Long id) {
        return taskServiceImpl.deleteTaskById(id);
    }
    @PostMapping
    public Mono<TaskEntity> createTask(@RequestBody TaskDTO task) {
        return taskServiceImpl.saveTask(task);
    }
    @GetMapping("/get/user/{id}")
    public Flux<TaskEntity> getTaskByUserId(@PathVariable Long id) {
        return taskServiceImpl.findByIdUser(id);
    }
    @PutMapping("/update/{id}")
    public Mono<TaskEntity> updateTask(@PathVariable Long id, @RequestBody TaskDTO task) {
        return taskServiceImpl.updateTaskById(id,task);
    }

}
