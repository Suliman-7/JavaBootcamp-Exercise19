package com.example.capstone2.Controller;


import com.example.capstone2.Model.Venue;
import com.example.capstone2.Service.VenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/venue")
@RequiredArgsConstructor


public class VenueController {

    private final VenueService venueService;

    @GetMapping("/get")
    public ResponseEntity getAllVenues(){
        return ResponseEntity.status(200).body(venueService.getAllVenues());
    }

    @PostMapping("/post")
    public ResponseEntity addVenue(@Valid @RequestBody Venue venue ){

        venueService.addVenue(venue);
        return ResponseEntity.status(200).body("Venue added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateVenue(@PathVariable int id, @Valid @RequestBody Venue venue , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        venueService.updateVenue(id, venue);
        return ResponseEntity.status(200).body("Venue updated successfully");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteVenue(@PathVariable int id){
        venueService.deleteVenue(id);
        return ResponseEntity.status(200).body("Venue deleted successfully");
    }

}
