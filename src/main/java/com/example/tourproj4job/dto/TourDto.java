package com.example.tourproj4job.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder
public class TourDto {
    Long id;
    Collection<StoryDto> stories;
}
