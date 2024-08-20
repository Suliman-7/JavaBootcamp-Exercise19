package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Event;
import com.example.capstone2.Model.Organiser;
import com.example.capstone2.Repository.EventRepository;
import com.example.capstone2.Repository.OrganiserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class OrganiserService {

    private final OrganiserRepository organiserRepository;
    private final EventRepository eventRepository;

    public List<Organiser> getAllOrganisers() {
        return organiserRepository.findAll();
    }

    public void addOrganiser(Organiser organiser) {
        organiserRepository.save(organiser);
    }

    public void updateOrganiser(int id , Organiser organiser) {
        Organiser o = organiserRepository.findById(id);
        if(o == null) {
            throw new ApiException("Organiser not found");
        }
        o.setName(organiser.getName());
        o.setAddress(organiser.getAddress());
        o.setEmail(organiser.getEmail());
        o.setFoundDate(organiser.getFoundDate());
        organiserRepository.save(o);

    }

    public void deleteOrganiser(int id) {
        Organiser organiser = organiserRepository.findById(id);
        if (organiser == null) {
            throw new ApiException("Organiser not found");
        }
        organiserRepository.delete(organiser);
    }


}
