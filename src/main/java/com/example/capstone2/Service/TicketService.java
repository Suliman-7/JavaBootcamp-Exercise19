package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Ticket;
import com.example.capstone2.Repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }


    public void updateTicket( int id , Ticket ticket) {
        Ticket t = ticketRepository.findById(id);
        if (t == null) {
            throw new ApiException("Ticket not found");
        }
        t.setDate(ticket.getDate());
        t.setPrice(ticket.getPrice());
        t.setEventId(ticket.getEventId());
        t.setSeatNumber(ticket.getSeatNumber());
        ticketRepository.save(t);
    }

    public void deleteTicket(int id) {
        Ticket ticket = ticketRepository.findById(id);
        if (ticket == null) {
            throw new ApiException("Ticket not found");
        }
        ticketRepository.delete(ticket);
    }
}
