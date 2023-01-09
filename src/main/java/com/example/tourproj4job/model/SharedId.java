package com.example.tourproj4job.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@MappedSuperclass
public class SharedId implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY  )
    private Long id;


    @CreationTimestamp
    private OffsetDateTime creationDate;

}
