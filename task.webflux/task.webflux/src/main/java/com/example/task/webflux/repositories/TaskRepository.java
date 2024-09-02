package com.example.task.webflux.repositories;

import com.example.task.webflux.models.TaskEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<TaskEntity, Long> {
    Flux<TaskEntity> findByIdUser(Long idUser);
}
