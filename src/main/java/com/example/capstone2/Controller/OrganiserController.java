package com.example.capstone2.Controller;


import com.example.capstone2.Model.Organiser;
import com.example.capstone2.Service.OrganiserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/organiser")
@RequiredArgsConstructor

public class OrganiserController {

    private final OrganiserService organiserService;

    @GetMapping("/get")
    public ResponseEntity getAllOrganisers(){
        return ResponseEntity.status(200).body(organiserService.getAllOrganisers());
    }

    @PostMapping("/post")
    public ResponseEntity addOrganiser(@Valid @RequestBody Organiser organiser){

        organiserService.addOrganiser(organiser);
        return ResponseEntity.status(200).body("organiser added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrganiser(@PathVariable int id, @RequestBody Organiser organiser ){

        organiserService.updateOrganiser(id, organiser);
        return ResponseEntity.status(200).body("organiser updated successfully");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteOrganiser(@PathVariable int id){
        organiserService.deleteOrganiser(id);
        return ResponseEntity.status(200).body("organiser deleted successfully");
    }


}
