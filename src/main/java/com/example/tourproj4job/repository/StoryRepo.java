package com.example.tourproj4job.repository;


import com.example.tourproj4job.model.Story;
import com.example.tourproj4job.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StoryRepo extends JpaRepository<Story, Long> {

    Collection<Story> findAllByTour(Tour tourId);
}
