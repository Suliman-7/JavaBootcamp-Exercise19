package com.example.capstone2.Controller;

import com.example.capstone2.Model.Ticket;
import com.example.capstone2.Service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ticket")
@RequiredArgsConstructor

public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/get")
    public ResponseEntity getAllTickets() {
        return ResponseEntity.status(200).body(ticketService.getAllTickets());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateTicket(@PathVariable int id, @Valid @RequestBody Ticket ticket , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        ticketService.updateTicket(id, ticket);
        return ResponseEntity.status(200).body("ticket updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTicket(@PathVariable int id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.status(200).body("ticket deleted successfully");
    }
}
