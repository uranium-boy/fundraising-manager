package com.janissary.fundraising.service;

import com.janissary.fundraising.dto.request.DonateMoneyRequest;
import com.janissary.fundraising.dto.response.CollectionBoxInfo;
import com.janissary.fundraising.dto.response.CreateCollectionBoxResponse;

import java.util.List;

public interface CollectionBoxService {
    CreateCollectionBoxResponse createBox();

    List<CollectionBoxInfo> getBoxes();

    void assignBoxToEvent(Long eventId);

    void donateMoney(Long boxId, DonateMoneyRequest donateRequest);

    void transferMoney(Long boxId);

    void unregisterBox(Long boxId);
}
