package com.example.capstone2.Controller;

import com.example.capstone2.Model.Event;
import com.example.capstone2.Service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/get")
    public ResponseEntity getEvents() {
        return ResponseEntity.status(200).body(eventService.getAllEvents());
    }

    @PostMapping("/post")
    public ResponseEntity postEvent(@Valid @RequestBody Event event ) {

        eventService.addEvent(event);
        return ResponseEntity.status(200).body("event added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEvent(@PathVariable int id, @Valid @RequestBody Event event ) {

        eventService.updateEvent(id, event);
        return ResponseEntity.status(200).body("event updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable int id) {
        eventService.deleteEvent(id);
        return ResponseEntity.status(200).body("event deleted successfully");
    }

    @GetMapping("/getperiod/{start}/{end}")
    public ResponseEntity getPeriod(@PathVariable LocalDate start, @PathVariable LocalDate end) {
        return ResponseEntity.status(200).body(eventService.getByPeriod(start, end));
    }

    @GetMapping("/getbytype/{type}")
    public ResponseEntity getByType(@PathVariable String type) {
        return ResponseEntity.status(200).body(eventService.getByEventType(type));
    }

    @GetMapping("/showreviews/{eid}")
    public ResponseEntity showReviews(@PathVariable int eid) {
        return ResponseEntity.status(200).body(eventService.showReviews(eid));
    }

    @PutMapping("/discount/{eid}")
    public ResponseEntity discount(@PathVariable int eid) {
        eventService.discount(eid);
        return ResponseEntity.status(200).body("discount successfully");
    }

    @GetMapping("/report")
    public ResponseEntity report() {
        return ResponseEntity.status(200).body(eventService.reportEvents());
    }

    @GetMapping("/getbyrate/{rate}")
    public ResponseEntity getByRate(@PathVariable double rate) {
        return ResponseEntity.status(200).body(eventService.getByRate(rate));
    }

    @GetMapping("/mostpooking")
    public ResponseEntity mostPooking() {
        return ResponseEntity.status(200).body(eventService.getMostBookings());
    }

    @GetMapping("/getbyorganiser/{oid}")
    public ResponseEntity getByOrg(@PathVariable int oid) {
        return ResponseEntity.status(200).body(eventService.getByOrganiserId(oid));
    }

    @GetMapping("/getclosestevents")
    public ResponseEntity getClosestEvents() {
        return ResponseEntity.status(200).body(eventService.getClosestEvents());
    }

    @GetMapping("/highestratedevents")
    public ResponseEntity getHighestRateEvents() {
        return ResponseEntity.status(200).body(eventService.highestRatedEvents());
    }



}
