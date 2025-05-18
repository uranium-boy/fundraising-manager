package com.janissary.fundraising.controller;

import com.janissary.fundraising.dto.request.CreateEventRequest;
import com.janissary.fundraising.dto.response.CreateEventResponse;
import com.janissary.fundraising.dto.response.EventFinancialReportItem;
import com.janissary.fundraising.service.impl.EventServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    private final EventServiceImpl service;

    public EventController(EventServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/events")
    public ResponseEntity<CreateEventResponse> createEvent(@RequestBody @Valid CreateEventRequest createRequest) {
        CreateEventResponse response = service.createEvent(createRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventFinancialReportItem>> eventFinancialReport() {
        List<EventFinancialReportItem> reportItems = service.getFinancialReport();
        return new ResponseEntity<>(reportItems, HttpStatus.OK);
    }
}
