package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Venue;
import com.example.capstone2.Repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public List<Venue> getAllVenues(){
        return venueRepository.findAll();
    }

    public void addVenue(Venue venue){
        venueRepository.save(venue);
    }

    public void updateVenue( int id , Venue venue){
        Venue v = venueRepository.findById(id);
        v.setName(venue.getName());
        v.setAddress(venue.getAddress());
        v.setVenueType(venue.getVenueType());
        v.setCapacity(venue.getCapacity());
        venueRepository.save(v);

    }

    public void deleteVenue(int id){
        Venue venue = venueRepository.findById(id);
        if(venue==null){
            throw new ApiException("Venue not found");
        }
        venueRepository.delete(venue);
    }
}
