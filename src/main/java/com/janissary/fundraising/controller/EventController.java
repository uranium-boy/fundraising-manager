package com.janissary.fundraising.controller;

import com.janissary.fundraising.dto.EventDto;
import com.janissary.fundraising.request.CreateEventRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    @PostMapping("/events")
    public ResponseEntity<EventDto> createEvent(@RequestBody CreateEventRequest req) {
        // TODO: create new event, return event id
        throw new UnsupportedOperationException("not implemented");
    }

    @GetMapping("/events")
    public List<EventDto> eventFinancialReport() {
        // TODO: return account sum of each event
        throw new UnsupportedOperationException("not implemented");
    }
}
