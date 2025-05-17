package com.janissary.fundraising.service;

import com.janissary.fundraising.dto.request.CreateEventRequest;
import com.janissary.fundraising.dto.response.CreateEventResponse;
import com.janissary.fundraising.dto.response.EventFinancialReportItem;

import java.util.List;

public interface EventService {
    CreateEventResponse createEvent(CreateEventRequest createRequest);

    List<EventFinancialReportItem> getFinancialReport();
}
