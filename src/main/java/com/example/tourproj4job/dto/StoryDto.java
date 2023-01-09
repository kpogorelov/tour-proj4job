package com.example.tourproj4job.dto;

import lombok.*;

@Value
@Builder
public class StoryDto {
    Long id;
    String lang;
    String title;
    boolean published;
    Integer order;
}
