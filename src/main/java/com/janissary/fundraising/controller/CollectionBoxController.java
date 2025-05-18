package com.janissary.fundraising.controller;

import com.janissary.fundraising.dto.CollectionBoxDto;
import com.janissary.fundraising.dto.request.AssignBoxRequest;
import com.janissary.fundraising.dto.request.DonateMoneyRequest;
import com.janissary.fundraising.dto.response.CollectionBoxStatus;
import com.janissary.fundraising.dto.response.CreateCollectionBoxResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollectionBoxController {
    @PostMapping("/boxes")
    public ResponseEntity<CreateCollectionBoxResponse> createBox() {
        // TODO: create new box, return newly created box
        throw new UnsupportedOperationException("TODO");
    }

    @GetMapping("/boxes")
    public ResponseEntity<List<CollectionBoxStatus>> getBoxes() {
        // TODO: return all boxes, don't specify the event, no actual value
        throw new UnsupportedOperationException("TODO");
    }

    @PostMapping("/boxes/{id}/assign")
    public ResponseEntity<Void> assignBoxToEvent(
            @PathVariable Long id,
            @RequestBody @Valid AssignBoxRequest assignRequest
    ) {
        // TODO: assign collection box lmao
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/boxes/{id}/donate")
    public ResponseEntity<Void> donateMoney(
            @PathVariable Long id,
            @RequestBody @Valid DonateMoneyRequest req
            ) {
        // TODO: add money to current amount
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/boxes/{id}/transfer")
    public ResponseEntity<Void> transferMoney(@PathVariable Long id) {
        // TODO: clear account, convert currencies and add to event's account
        throw new UnsupportedOperationException("TODO");
    }

    @DeleteMapping("/boxes/{id}")
    public ResponseEntity<Void> unregisterBox(@PathVariable Long id) {
        // TODO: don't transfer money, delete box
        throw new UnsupportedOperationException("TODO");
    }
}
