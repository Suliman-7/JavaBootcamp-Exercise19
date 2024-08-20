package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.*;
import com.example.capstone2.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final ReviewRepository reviewRepository;
    private final VenueRepository venueRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(int id , User user) {
        User u = userRepository.findById(id);

        if (u == null) {
            throw new ApiException("User not found");
        }
        u.setBalance(user.getBalance());
        u.setAge(user.getAge());
        u.setEmail(user.getEmail());
        u.setName(user.getName());
        userRepository.save(u);

    }



    public void deleteUser(int id) {
        User u = userRepository.findById(id);
        if (u == null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(u);
    }

    public void chargeWallet(int id , double amount){
        User u = userRepository.findById(id);

        if (u == null) {
            throw new ApiException("User not found");
        }

        u.setBalance(u.getBalance()+amount);
        userRepository.save(u);

    }



    public void bookEvent (int userId , int eventId , int seatNumber) {

        Event e = eventRepository.findById(eventId);
        if (e == null) {
            throw new ApiException("Event not found");
        }


        User u = userRepository.findById(userId);
        if (u == null) {
            throw new ApiException("User not found");
        }


        for(Ticket t : ticketRepository.findAll()){
            if(t.getSeatNumber()==seatNumber && eventId==t.getEventId()){
                throw new ApiException("seat is already taken");
            }
        }

        Ticket ticket = new Ticket();


        if(e.getEventType().equalsIgnoreCase("Match")){
            if(seatNumber>=1 && seatNumber<=1000) {
                ticket.setSeatType("VIP seat");
                ticket.setPrice(150);
            }
            if(seatNumber>1000 && seatNumber<=3000) {
                ticket.setSeatType("Premium seat");
                ticket.setPrice(100);
            }
            if(seatNumber>3000 && seatNumber<=5000) {
                ticket.setSeatType("Standard seat");
                ticket.setPrice(50);
            }
        }
        if(e.getEventType().equalsIgnoreCase("Theatrical")){
            if(seatNumber>=1 && seatNumber<=1000) {
                ticket.setSeatType("VIP seat");
                ticket.setPrice(200);
            }
            if(seatNumber>1000 && seatNumber<=3000) {
                ticket.setSeatType("Premium seat");
                ticket.setPrice(150);
            }
            if(seatNumber>3000 && seatNumber<=5000) {
                ticket.setSeatType("Standard seat");
                ticket.setPrice(100);
            }
        }
        if(e.getEventType().equalsIgnoreCase("Concert")){
            if(seatNumber>=1 && seatNumber<=1000) {
                ticket.setSeatType("VIP seat");
                ticket.setPrice(400);
            }
            if(seatNumber>1000 && seatNumber<=3000) {
                ticket.setSeatType("Premium seat");
                ticket.setPrice(250);
            }
            if(seatNumber>3000 && seatNumber<=5000) {
                ticket.setSeatType("Standard seat");
                ticket.setPrice(150);
            }
        }
        double percentage = e.getDiscountpercentage()/100 ;

        ticket.setPrice(ticket.getPrice()-(ticket.getPrice()*percentage));

        if(u.getBalance()<ticket.getPrice()){
            throw new ApiException("insufficient funds");
        }

        LocalDate today = LocalDate.now();
        if(e.getEventDate().isBefore(today)){
            throw new ApiException("the show no longer exists");
        }

        Venue v = venueRepository.findById(e.getVenueId());


        if(v.getCapacity()==e.getNumOfBookings()){
            throw new ApiException("unfortunately , no ticket available ");
        }

        ticket.setDate(e.getEventDate());

        ticket.setSeatNumber(seatNumber);

        ticket.setEventId(e.getId());

        ticket.setUserId(u.getId());

        ticket.setReturnable(e.isReturnable());

        ticketRepository.save(ticket);

        u.setBalance(u.getBalance()-ticket.getPrice());


        e.setNumOfBookings(e.getNumOfBookings()+1);

        eventRepository.save(e);

        if(v.getCapacity()==e.getNumOfBookings()){
            e.setFull(true);
        }

        userRepository.save(u);

    }

    public void cancelBooking (int userId , int ticketId ) {

        Ticket t = ticketRepository.findById(ticketId);
        if (t == null) {
            throw new ApiException("Ticket not found");
        }

        User u = userRepository.findById(userId);
        if (u == null) {
            throw new ApiException("User not found");
        }

        if(userId != t.getUserId()){
            throw new ApiException("you cannot cancel the booking");
        }

        if(! t.isReturnable()){
            throw new ApiException("Unfortunately , Ticket is not returnable");
        }

        LocalDate today = LocalDate.now();

        if(today.datesUntil(t.getDate()).count()<2) {
            throw new ApiException("To cancel your reservation, there must be more than one day left until the event.");

        }


        u.setBalance(u.getBalance()+t.getPrice());

        userRepository.save(u);

        Event e = eventRepository.findById(t.getEventId());

        e.setNumOfBookings(e.getNumOfBookings()-1);

        eventRepository.save(e);

        ticketRepository.delete(t);

    }

    public List<Event> showHistory(int userId){
        List<Event> el = new ArrayList<>();
        User u = userRepository.findById(userId);
        if (u == null) {
            throw new ApiException("User not found");
        }

        for(Ticket t : ticketRepository.findAll()){
            if(t.getUserId()==userId){
                Event e = eventRepository.findById(t.getEventId());
                el.add(e);
            }
        }
        return el;
    }



    public void addReview(int userId , int eventId, String comment , double rate){
        boolean reviewable = false;
        User u = userRepository.findById(userId);
        if (u == null) {
            throw new ApiException("User not found");
        }
        Event e = eventRepository.findById(eventId);
        if (e == null) {
            throw new ApiException("Event not found");
        }
//        LocalDate today = LocalDate.now();
//        if(e.getEventDate().isAfter(today)){
//            throw new ApiException("the show not started you cannot add review until it finish");
//        }
        List<Review> review = reviewRepository.findAll();
        for(Review r : review){
            if(r.getUserId()==u.getId() && r.getEventId()==eventId){
                throw new ApiException("You are already reviewed");
            }
        }

        List<Event> el = showHistory(userId);
        for(Event ev : el){
            if (ev.getId()==eventId){
                Review r = new Review();
                r.setUserId(u.getId());
                r.setEventId(eventId);
                r.setComment(comment);
                r.setRating(rate);
                reviewRepository.save(r);
                reviewable = true;
            }
        }

        if(e.getRate()==0){
            e.setRate(rate);
            eventRepository.save(e);
        }
        else {
            e.setRate((e.getRate() + rate) / 2);
            eventRepository.save(e);

        }

        if(!reviewable){
            throw new ApiException("you didn't attend the event, so you can't review it");
        }

    }

    public List<Review> showReviews(int userId) {
        List<Review> reviews = new ArrayList<>();
        for (Review review : reviewRepository.findAll()) {
            if (review.getUserId() == userId) {
                reviews.add(review);
            }
        }
        if (reviews.isEmpty()) {
            throw new ApiException("No reviews found");
        }
        return reviews;
    }

    public List<Event> recommendEvents(int userId){
        List<Event> events = new ArrayList<>();
        int countMatch = 0 ;
        int countTheatrical = 0 ;
        int countConcerts = 0 ;
        List<Event> recommendEvents = new ArrayList<>();


        User u = userRepository.findById(userId);
        if (u == null) {
            throw new ApiException("User not found");
        }
        for(Ticket t : ticketRepository.findAll()){
            if(t.getUserId()==u.getId()){
                Event e = eventRepository.findById(t.getEventId());
                if(e.getEventType().equalsIgnoreCase("Match")){
                    countMatch++;
                }
                if(e.getEventType().equalsIgnoreCase("Concert")){
                    countConcerts++;
                }
                if(e.getEventType().equalsIgnoreCase("Theatrical")){
                    countTheatrical++;
                }
            }
        }
            if(countMatch>countTheatrical && countMatch>countConcerts){
                for(Event ev : eventRepository.findAll()){
                    if(ev.getEventType().equalsIgnoreCase("Match")){
                        recommendEvents.add(ev);
                    }
                }
                return recommendEvents;
            }
            if(countTheatrical>countMatch && countTheatrical>countConcerts){
                for(Event ev : eventRepository.findAll()){
                    if(ev.getEventType().equalsIgnoreCase("Theatrical")){
                        recommendEvents.add(ev);
                    }
                }
                return recommendEvents;

            }
            if(countConcerts>countTheatrical && countConcerts>countMatch){
                for(Event ev : eventRepository.findAll()){
                    if(ev.getEventType().equalsIgnoreCase("Concert")){
                        recommendEvents.add(ev);
                    }
                }
                return recommendEvents;
            }



        return events;
    }

}
