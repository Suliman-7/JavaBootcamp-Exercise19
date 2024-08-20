package com.example.capstone2.Controller;

import com.example.capstone2.Model.User;
import com.example.capstone2.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/post")
    public ResponseEntity addUser(@Valid @RequestBody User user) {

        userService.addUser(user);
        return ResponseEntity.status(200).body("user added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @Valid @RequestBody User user) {

        userService.updateUser(id, user);
        return ResponseEntity.status(200).body("user updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("user deleted successfully");

    }

    @PutMapping("/chargewallet/{id}/{amount}")
    public ResponseEntity chargeWallet(@PathVariable int id, @PathVariable int amount) {
        userService.chargeWallet(id, amount);
        return ResponseEntity.status(200).body("user charged wallet successfully");
    }

    @PutMapping("/bookevent/{uid}/{eid}/{seatnumber}")
    public ResponseEntity bookEvent(@PathVariable int uid, @PathVariable int eid , @PathVariable int seatnumber) {
        userService.bookEvent(uid, eid, seatnumber);
        return ResponseEntity.status(200).body("user book event successfully");
    }

    @PutMapping("/cancel/{uid}/{tid}")
    public ResponseEntity cancelEvent(@PathVariable int uid, @PathVariable int tid) {
        userService.cancelBooking(uid,tid);
        return ResponseEntity.status(200).body("user booking cancelled successfully");
    }

    @GetMapping("/showhistory/{uid}")
    public ResponseEntity showHistory(@PathVariable int uid) {
        return ResponseEntity.status(200).body(userService.showHistory(uid));
    }


    @PutMapping("/addreview/{uid}/{eid}/{comment}/{rate}")
    public ResponseEntity addReview(@PathVariable int uid, @PathVariable int eid, @PathVariable String comment, @Min(0) @Max(5) @PathVariable double rate) {
        userService.addReview(uid, eid, comment,rate);
        return ResponseEntity.status(200).body("user review added successfully");
    }

    @GetMapping("/showreviews/{uid}")
    public ResponseEntity showReviews(@PathVariable int uid) {
        return ResponseEntity.status(200).body(userService.showReviews(uid));
    }

    @GetMapping("/recommend/{uid}")
    public ResponseEntity recommend(@PathVariable int uid) {
        return ResponseEntity.status(200).body(userService.recommendEvents(uid));
    }




}