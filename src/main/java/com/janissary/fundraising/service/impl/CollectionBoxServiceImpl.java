package com.janissary.fundraising.service.impl;

import com.janissary.fundraising.dto.request.AssignBoxRequest;
import com.janissary.fundraising.dto.request.DonateMoneyRequest;
import com.janissary.fundraising.dto.response.CollectionBoxInfo;
import com.janissary.fundraising.dto.response.CreateCollectionBoxResponse;
import com.janissary.fundraising.exception.CollectionBoxNotAssignedException;
import com.janissary.fundraising.exception.CollectionBoxNotFoundException;
import com.janissary.fundraising.exception.EventNotFoundException;
import com.janissary.fundraising.exception.UnacceptedCurrencyException;
import com.janissary.fundraising.model.CollectionBox;
import com.janissary.fundraising.model.Currency;
import com.janissary.fundraising.model.Event;
import com.janissary.fundraising.repository.CollectionBoxRepository;
import com.janissary.fundraising.repository.CurrencyRepository;
import com.janissary.fundraising.repository.EventRepository;
import com.janissary.fundraising.service.CollectionBoxService;
import com.janissary.fundraising.service.ExchangeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class CollectionBoxServiceImpl implements CollectionBoxService {
    private final CollectionBoxRepository collectionBoxRepository;
    private final EventRepository eventRepository;
    private final CurrencyRepository currencyRepository;
    private final ExchangeService exchangeService;

    public CollectionBoxServiceImpl(
            CollectionBoxRepository collectionBoxRepository,
            EventRepository eventRepository,
            CurrencyRepository currencyRepository,
            ExchangeService exchangeService
    ) {
        this.collectionBoxRepository = collectionBoxRepository;
        this.eventRepository = eventRepository;
        this.currencyRepository = currencyRepository;
        this.exchangeService = exchangeService;
    }

    @Override
    public CreateCollectionBoxResponse createBox() {
        CollectionBox box = collectionBoxRepository.save(new CollectionBox());
        return new CreateCollectionBoxResponse(box.getId());
    }

    @Override
    public List<CollectionBoxInfo> getBoxes() {
        return collectionBoxRepository.findAll().stream()
                .map(box -> new CollectionBoxInfo(
                        box.getEvent() != null,
                        box.getCollectedAmounts().isEmpty()
                ))
                .toList();
    }

    @Override
    public void assignBoxToEvent(Long boxId, AssignBoxRequest assignRequest) {
        Event event = eventRepository.findById(assignRequest.eventId())
                .orElseThrow(() -> new EventNotFoundException(assignRequest.eventId().toString()));

        // should I enable overwriting assigned event?
        CollectionBox collectionBox = collectionBoxRepository.findById(boxId)
                .orElseThrow(() -> new CollectionBoxNotFoundException(boxId.toString()));

        collectionBox.setEvent(event);
        collectionBoxRepository.save(collectionBox);
    }

    @Override
    public void donateMoney(Long boxId, DonateMoneyRequest donateRequest) {
        CollectionBox collectionBox = collectionBoxRepository.findById(boxId)
                .orElseThrow(() -> new CollectionBoxNotFoundException(boxId.toString()));

        if (collectionBox.getEvent() == null) {
            throw new CollectionBoxNotAssignedException(boxId.toString());
        }

        Currency currency = currencyRepository.findById(donateRequest.currency())
                .orElseThrow(() -> new UnacceptedCurrencyException(donateRequest.currency()));

        collectionBox.getCollectedAmounts().merge(
                currency,
                donateRequest.amount(),
                BigDecimal::add
        );

        collectionBoxRepository.save(collectionBox);
    }

    @Override
    public void transferMoney(Long boxId) {
        CollectionBox collectionBox = collectionBoxRepository.findById(boxId)
                .orElseThrow(() -> new CollectionBoxNotFoundException(boxId.toString()));

        Event event = collectionBox.getEvent();

        if (event == null) {
            throw new CollectionBoxNotAssignedException(boxId.toString());
        }

        BigDecimal total = collectionBox.getCollectedAmounts().entrySet().stream()
                .map(entry -> exchangeService.exchange(
                        entry.getValue(),
                        entry.getKey(),
                        collectionBox.getEvent().getDefaultCurrency())
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        event.setTotalCollected(event.getTotalCollected().add(total));
        eventRepository.save(event);

        collectionBox.setCollectedAmounts(Collections.emptyMap());
        collectionBoxRepository.save(collectionBox);
    }

    @Override
    public void unregisterBox(Long boxId) {
        CollectionBox collectionBox = collectionBoxRepository.findById(boxId)
                .orElseThrow(() -> new CollectionBoxNotFoundException(boxId.toString()));

        collectionBoxRepository.delete(collectionBox);
    }
}
