package com.example.tourproj4job.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    String url;
    String error;
    String description;
}
