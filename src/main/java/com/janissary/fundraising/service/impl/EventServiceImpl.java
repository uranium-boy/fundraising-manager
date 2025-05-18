package com.janissary.fundraising.service.impl;

import com.janissary.fundraising.dto.request.CreateEventRequest;
import com.janissary.fundraising.dto.response.CreateEventResponse;
import com.janissary.fundraising.dto.response.EventFinancialReportItem;
import com.janissary.fundraising.model.Currency;
import com.janissary.fundraising.model.Event;
import com.janissary.fundraising.repository.CurrencyRepository;
import com.janissary.fundraising.repository.EventRepository;
import com.janissary.fundraising.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CurrencyRepository currencyRepository;

    public EventServiceImpl(EventRepository eventRepository, CurrencyRepository currencyRepository) {
        this.eventRepository = eventRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public CreateEventResponse createEvent(CreateEventRequest createRequest) {
        Currency defaultCurrency = currencyRepository.findById(createRequest.defaultCurrencyCode())
                .orElseThrow(() -> new IllegalArgumentException("Currency code not found"));

        Event event = Event.builder()
                .name(createRequest.name())
                .defaultCurrency(defaultCurrency)
                .build();

        Event savedEvent = eventRepository.save(event);

        return new CreateEventResponse(
                savedEvent.getId(),
                savedEvent.getName(),
                savedEvent.getDefaultCurrency().getId()
        );
    }

    @Override
    public List<EventFinancialReportItem> getFinancialReport() {
        List<EventFinancialReportItem> allItems = new ArrayList<>();

        for (Event event : eventRepository.findAll()) {
            EventFinancialReportItem item = new EventFinancialReportItem(
                    event.getName(),
                    event.getTotalCollected(),
                    event.getDefaultCurrency().getId()
            );
            allItems.add(item);
        }
        return allItems;
    }
}
