package com.janissary.fundraising.controller;

import com.janissary.fundraising.dto.EventDto;
import com.janissary.fundraising.request.CreateEventRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    @PostMapping("/events")
    public ResponseEntity<EventDto> createEvent(@RequestBody @Valid CreateEventRequest req) {
        // TODO: create new event, return event id
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/events")
    public List<EventDto> eventFinancialReport() {
        // TODO: return account sum of each event
        throw new UnsupportedOperationException("not implemented");
    }
}
