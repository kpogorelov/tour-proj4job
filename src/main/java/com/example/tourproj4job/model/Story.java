package com.example.tourproj4job.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Entity
public class Story extends SharedId {

    @Column(length = 2)
    private String lang;
    @Column(length = 150)
    private String title;
    private boolean published;
    @Column(name = "story_order")
    private Integer order;
    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;
}
