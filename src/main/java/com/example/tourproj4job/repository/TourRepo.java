package com.example.tourproj4job.repository;


import com.example.tourproj4job.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepo extends JpaRepository<Tour, Long> {
}
