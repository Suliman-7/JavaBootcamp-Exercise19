package com.example.capstone2.Controller;

import com.example.capstone2.Model.Coordinator;
import com.example.capstone2.Service.CoordinatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/coordinator")
@RequiredArgsConstructor

public class CoordinatorController {

    private final CoordinatorService coordinatorService;

    @GetMapping("/get")
    public ResponseEntity getAllCoordinators() {
        return ResponseEntity.ok(coordinatorService.getAllCoordinators());
    }

    @PostMapping("/post")
    public ResponseEntity addCoordinator(@Valid @RequestBody Coordinator coordinator) {

        coordinatorService.addCoordinator(coordinator);
        return ResponseEntity.status(200).body("Coordinator added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCoordinator(@PathVariable int id , @Valid @RequestBody Coordinator coordinator) {

        coordinatorService.updateCoordinator(id, coordinator);
        return ResponseEntity.status(200).body("Coordinator updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCoordinator(@PathVariable int id) {
        coordinatorService.deleteCoordinator(id);
        return ResponseEntity.status(200).body("Coordinator deleted successfully");
    }

    @PutMapping("/assigntoevent/{cid}/{eid}")
    public ResponseEntity assignToEvent(@PathVariable int cid, @PathVariable int eid) {
        coordinatorService.assignToEvent(cid, eid);
        return ResponseEntity.status(200).body("Coordinator assigned successfully");
    }

    @PutMapping("/raisehonorarium/{id}")
    public ResponseEntity raiseHonorarium(@PathVariable int id) {
        coordinatorService.raiseSalary(id);
        return ResponseEntity.status(200).body("Coordinator Honorarium raised successfully");
    }

    @GetMapping("/showassignedevents/{id}")
    public ResponseEntity showAssignedEvents(@PathVariable int id) {
        return ResponseEntity.status(200).body(coordinatorService.showAssignedEvents(id));
    }




}
