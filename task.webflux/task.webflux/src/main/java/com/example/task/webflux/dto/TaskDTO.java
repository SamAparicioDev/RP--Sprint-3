package com.example.task.webflux.dto;




public record TaskDTO(String title,
                      String description,
                      StatusEnum status,
                      Long idUser) {

}
