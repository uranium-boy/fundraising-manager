package com.janissary.fundraising.controller;

import com.janissary.fundraising.dto.request.AssignBoxRequest;
import com.janissary.fundraising.dto.request.DonateMoneyRequest;
import com.janissary.fundraising.dto.response.CollectionBoxInfo;
import com.janissary.fundraising.dto.response.CreateCollectionBoxResponse;
import com.janissary.fundraising.service.impl.CollectionBoxServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollectionBoxController {
    private final CollectionBoxServiceImpl service;

    public CollectionBoxController(CollectionBoxServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/boxes")
    public ResponseEntity<CreateCollectionBoxResponse> createBox() {
        CreateCollectionBoxResponse response = service.createBox();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/boxes")
    public ResponseEntity<List<CollectionBoxInfo>> getBoxes() {
        // TODO: return all boxes, don't specify the event, no actual value
        List<CollectionBoxInfo> boxList = service.getBoxes();
        return new ResponseEntity<>(boxList, HttpStatus.OK);
    }

    @PostMapping("/boxes/{id}/assign")
    public ResponseEntity<Void> assignBoxToEvent(
            @PathVariable Long id,
            @RequestBody @Valid AssignBoxRequest assignRequest
    ) {
        service.assignBoxToEvent(id, assignRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/boxes/{id}/donate")
    public ResponseEntity<Void> donateMoney(
            @PathVariable Long id,
            @RequestBody @Valid DonateMoneyRequest donateRequest
            ) {
        service.donateMoney(id, donateRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/boxes/{id}/transfer")
    public ResponseEntity<Void> transferMoney(@PathVariable Long id) {
        service.transferMoney(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/boxes/{id}")
    public ResponseEntity<Void> unregisterBox(@PathVariable Long id) {
        service.unregisterBox(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
