package com.example.task.webflux;

import com.example.task.webflux.controllers.TaskController;
import com.example.task.webflux.dto.StatusEnum;
import com.example.task.webflux.dto.TaskDTO;
import com.example.task.webflux.models.TaskEntity;
import com.example.task.webflux.services.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@WebFluxTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private TaskServiceImpl taskServiceImpl;

    private TaskEntity taskEntity;

    @BeforeEach
    void setUp() {
        taskEntity = new TaskEntity(1L, "This is a task", "I'll wash my mouth every day", StatusEnum.PENDING, 1L);

        when(taskServiceImpl.saveTask(any(TaskDTO.class)))
                .thenReturn(Mono.just(taskEntity));

        when(taskServiceImpl.getTaskById(anyLong()))
                .thenReturn(Mono.just(taskEntity));

        when(taskServiceImpl.getAllTasks()).
                thenReturn(Flux.just(taskEntity));

        when(taskServiceImpl.
                updateTaskById(anyLong(),any(TaskDTO.class))).
                thenReturn(Mono.just(new TaskEntity(1L, "This is a new task", "I'll wash my mouth every day", StatusEnum.PENDING, 1L)));

        when(taskServiceImpl.
                findByIdUser(anyLong())).
                thenReturn(Flux.just(new TaskEntity(1L, "This is a new task", "I'll wash my mouth every day", StatusEnum.PENDING, 1L)));
    }

    @Test
    public void createTaskTest(){
        webClient.post().uri("/api/v2/task")
                .bodyValue(taskEntity)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskEntity.class)
                .value(task -> assertEquals("This is a task", task.getTitle()));
    }

    @Test
    public void getTaskByIdTest(){
        webClient.get().uri("/api/v2/task/get/{id}", 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskEntity.class)
                .value(task -> assertEquals(1L, task.getId()));
    }

    @Test
    public void getAllTaskTest(){
        webClient.get().uri("/api/v2/task")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TaskEntity.class)
                .hasSize(1);
    }
    @Test
    public void updateTaskByIdTest(){
        webClient.put().uri("/api/v2/task/update/{id}", 1L)
                .bodyValue(taskEntity)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskEntity.class)
                .value(task -> assertNotEquals("This is a task", task.getTitle()));

    }
    @Test
    public void deleteTaskByIdTest(){
        webClient.delete().uri("/api/v2/task/delete/{id}", 1L)
                .exchange()
                .expectStatus().isOk();
    }

  @Test
    public void getTaskByIdUserTest(){
        webClient.get().uri("/api/v2/task/get/user/{id}", 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TaskEntity.class)
                .hasSize(1);
  }
}


