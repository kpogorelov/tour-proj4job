package com.example.tourproj4job.service;

import com.example.tourproj4job.dto.StoryDto;
import com.example.tourproj4job.dto.TourDto;
import com.example.tourproj4job.model.Story;
import com.example.tourproj4job.model.Tour;
import com.example.tourproj4job.repository.StoryRepo;
import com.example.tourproj4job.repository.TourRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourService {
    private final StoryRepo storyRepo;
    private final TourRepo tourRepo;

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public Collection<StoryDto> getByLangPublished(Long id, String lang) {
        return tourRepo.findById(id)
                .map(storyRepo::findAllByTour)
                .orElse(new ArrayList<>())
                .stream()
                .filter(story -> lang.equalsIgnoreCase(story.getLang()) && story.isPublished())
                .sorted(Comparator.comparing(Story::getOrder))
                .map(story -> StoryDto.builder()
                        .id(story.getId())
                        .lang(story.getLang())
                        .title(story.getTitle()).
                        published(story.isPublished()).order(story.getOrder()).build())
                .toList();
    }

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public Collection<StoryDto> getByLang(Long id, Collection<String> lang) {
        return tourRepo.findById(id)
                .map(storyRepo::findAllByTour)
                .orElse(new ArrayList<>())
                .stream()
                .filter(story -> lang.contains(story.getLang()))
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(Story::getOrder))),
                        ArrayList::new))
                .stream()
                .map(story -> StoryDto.builder()
                        .id(story.getId())
                        .lang(story.getLang())
                        .title(story.getTitle()).
                        published(story.isPublished()).order(story.getOrder()).build())
                .sorted(Comparator.comparing(StoryDto::getOrder))
                .toList();
    }

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public TourDto save(TourDto tourDto) {
        final var tourEntity = new Tour();
        tourRepo.save(tourEntity);

        if (CollectionUtils.isEmpty(tourDto.getStories())) {
            return TourDto.builder()
                    .id(tourEntity.getId())
                    .stories(new ArrayList<>())
                    .build();
        }

        final var stories = tourDto.getStories().stream()
                .map(dto -> {
                    final var storyEntity = new Story();
                    storyEntity.setTour(tourEntity);
                    storyEntity.setLang(dto.getLang());
                    storyEntity.setOrder(dto.getOrder());
                    storyEntity.setTitle(dto.getTitle());
                    storyEntity.setPublished(dto.isPublished());
                    storyRepo.save(storyEntity);
                    return storyEntity;
                })
                .map(story -> StoryDto.builder()
                        .id(story.getId())
                        .lang(story.getLang())
                        .title(story.getTitle()).
                        published(story.isPublished()).order(story.getOrder()).build())
                .toList();

        return TourDto.builder()
                .id(tourEntity.getId())
                .stories(stories)
                .build();
    }
}
