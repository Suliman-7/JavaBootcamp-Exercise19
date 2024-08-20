package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.*;
import com.example.capstone2.Repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final OrganiserRepository organiserRepository;
    private final VenueRepository venueRepository;
    private final ReviewRepository reviewRepository;
    private final TicketRepository ticketRepository;
    private final CoordinatorRepository coordinatorRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void addEvent(Event event) {
        Organiser o = organiserRepository.findById(event.getOrganiserId());
        Venue v = venueRepository.findById(event.getVenueId());
        if (o == null) {
            throw new ApiException("Organiser not found");
        }
        if (v == null) {
            throw new ApiException("Venue not found");
        }

        LocalDate today = LocalDate.now();

        if(today.isAfter(event.getEventDate())){
            throw new ApiException("event date should be in future");
        }

        eventRepository.save(event);
    }

    public void updateEvent(int id, Event event) {
        Event e = eventRepository.findById(id);
        if (e == null) {
            throw new ApiException("Event not found");
        }

        Organiser o = organiserRepository.findById(e.getOrganiserId());

        if (o == null) {
            throw new ApiException("Organiser not found");
        }

        e.setCity(event.getCity());
        e.setDescription(event.getDescription());
        e.setEventDate(event.getEventDate());
        e.setEventType(event.getEventType());
        e.setNumOfBookings(event.getNumOfBookings());
        e.setFull(event.isFull());
        e.setOrganiserId(event.getOrganiserId());
        e.setName(event.getName());
        e.setReturnable(event.isReturnable());
        e.setVenueId(event.getVenueId());
        eventRepository.save(e);

    }

    public void deleteEvent(int id) {
        Event event = eventRepository.findById(id);
        if (event == null) {
            throw new ApiException("Event not found");
        }
        eventRepository.delete(event);
    }

    public List<Event> getByPeriod(LocalDate start, LocalDate end) {
        List<Event> events = eventRepository.findByEventDateBetween(start, end);
        if (events.isEmpty()) {
            throw new ApiException("No events found");
        }
        return events;
    }

    public List<Event> getByEventType(String eventType) {
        List<Event> events = eventRepository.findByEventType(eventType);
        if (events.isEmpty()) {
            throw new ApiException("No events found");
        }
        return events;
    }

    public List<Review> showReviews(int eventId) {
        List<Review> reviews = new ArrayList<>();
        for (Review review : reviewRepository.findAll()) {
            if (review.getEventId() == eventId) {
                reviews.add(review);
            }
        }
        if (reviews.isEmpty()) {
            throw new ApiException("No reviews found");
        }
        return reviews;
    }


    public void discount(int eventId) {
        LocalDate today = LocalDate.now();
        Event e = eventRepository.findById(eventId);
        Venue v = venueRepository.findById(e.getVenueId());

        if (today.datesUntil(e.getEventDate()).count() < 3 && (e.getNumOfBookings() < (0.5 * v.getCapacity()))) {
            e.setDiscountpercentage(10);
            eventRepository.save(e);
        }
        if (today.datesUntil(e.getEventDate()).count() < 2 && (e.getNumOfBookings() < (0.5 * v.getCapacity()))) {
            e.setDiscountpercentage(20);
            eventRepository.save(e);

        }
        if (today.datesUntil(e.getEventDate()).count() < 1 && (e.getNumOfBookings() < (0.5 * v.getCapacity()))) {
            e.setDiscountpercentage(30);
            eventRepository.save(e);

        }
    }

    public ArrayList reportEvents() {

        ArrayList r = new ArrayList<>();
        for (Event e : eventRepository.findAll()) {
            double sum = 0;
            Venue venue = venueRepository.findById(e.getVenueId());
            double percentage = ( (e.getNumOfBookings() / venue.getCapacity()) * 100);

            for (Ticket t : ticketRepository.findAll()) {
                if (t.getEventId() == e.getId()) {
                    sum += t.getPrice();
                }
            }

            r.add(" Event : " + e.getName());
            r.add(" Number of Booking : " + e.getNumOfBookings());
            r.add(" By " + percentage + "%");
            r.add(" With total revenue : " + sum );
            r.add(" --------------------------------------------");
        }
        return r;
    }



    public List<Event> getByRate(double rate) {
        List<Event> events = new ArrayList<>();
        for (Event e : eventRepository.findAll()) {
            if(e.getRate()>=rate){
                events.add(e);
            }
        }
        return events;
    }


    public List<Event> getMostBookings(){
        List<Event> events = eventRepository.findAll();
        List<Event> mostBookings = new ArrayList<>();
        List<Integer> numOfBookings = new ArrayList<>();
        for (Event e : events) {
            numOfBookings.add((int) e.getNumOfBookings());
        }
        Collections.sort(numOfBookings);

        for (int i = numOfBookings.size()-1 ; i >= 0 ; i--) {
            for (Event e : events) {
                if(e.getNumOfBookings() == numOfBookings.get(i) && ! mostBookings.contains(e)) {
                    mostBookings.add(e);
                }
            }
        }

        return mostBookings;
    }

    public List<Event> getByOrganiserId(int orgId) {
        List<Event> events = new ArrayList<>();
        for (Event e : eventRepository.findAll()) {
            if (e.getOrganiserId() == orgId) {
                events.add(e);
            }
        }
        if(events.isEmpty()) {
            throw new ApiException("No events found");
        }

        return events;
    }

    public List<Event> getClosestEvents(){
        List<Event> events = eventRepository.findAll();
        List<Event> closestEvent = events;

        for (int i = 0; i < closestEvent.size() - 1; i++) {
            for (int j = 0; j < closestEvent.size() - i - 1; j++) {
                if (closestEvent.get(j).getEventDate().isAfter(closestEvent.get(j + 1).getEventDate()) || closestEvent.get(j).getEventDate().isEqual(closestEvent.get(j + 1).getEventDate())) {
                    Event closest = closestEvent.get(j);
                    closestEvent.set(j, closestEvent.get(j + 1));
                    closestEvent.set(j + 1, closest);
                }
            }
        }
        return closestEvent;
        }

        public List<Event> highestRatedEvents() {
            List<Event> events = eventRepository.findAll();
            List<Event> highestRated = new ArrayList<>();
            List<Integer> rates = new ArrayList<>();
            for (Event e : events) {
                rates.add((int) e.getNumOfBookings());
            }
            Collections.sort(rates);

            for (int i = rates.size()-1 ; i >= 0 ; i--) {
                for (Event e : events) {
                    if(e.getNumOfBookings() == rates.get(i) && ! highestRated.contains(e)) {
                        highestRated.add(e);
                    }
                }
            }

            return highestRated;

        }

}
