package com.example.task.webflux.exceptions;

import com.example.task.webflux.services.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobanHandlerTaskException {
    @ExceptionHandler({TaskNotFoundException.class})
    public Mono<ResponseEntity<String> > handleTaskNotFoundException(TaskNotFoundException e) {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()));
    }

}
