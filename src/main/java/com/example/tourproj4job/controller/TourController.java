package com.example.tourproj4job.controller;

import com.example.tourproj4job.dto.StoryDto;
import com.example.tourproj4job.dto.TourDto;
import com.example.tourproj4job.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/tour")
public class TourController {

    private final TourService tourService;

    @GetMapping(path = "/{id}/lang/{lang}")
    public ResponseEntity<Collection<StoryDto>> getActiveByLang(@PathVariable(name = "id") Long id,
                                                                @PathVariable(name = "lang") String lang) {

        log.debug("Try to find stories for lang {} and id {}", lang, id);
        final var response = tourService.getByLangPublished(id, lang);
        return response.size() > 0
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }

    @PostMapping
    public ResponseEntity<TourDto> saveTour(@RequestBody TourDto tourDto) {
        log.debug("Try to save tour {}", tourDto);
        return ResponseEntity.ok(tourService.save(tourDto));
    }

    @GetMapping(path = "/{id}/langColl")
    public ResponseEntity<Collection<StoryDto>> getAllByLang(@PathVariable(name = "id") Long id,
                                                             @RequestParam(value = "langColl") Collection<String> langColl) {

        log.debug("Try to find all history for languages {} and id {}", langColl, id);
        return ResponseEntity.ok(tourService.getByLang(id, langColl));
    }

}
