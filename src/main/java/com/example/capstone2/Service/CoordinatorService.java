package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Coordinator;
import com.example.capstone2.Model.Event;
import com.example.capstone2.Repository.CoordinatorRepository;
import com.example.capstone2.Repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service

public class CoordinatorService {

    private final CoordinatorRepository coordinatorRepository;
    private final EventRepository eventRepository;

    public List<Coordinator> getAllCoordinators() {
        return coordinatorRepository.findAll();
    }

    public void addCoordinator(Coordinator coordinator) {
        coordinatorRepository.save(coordinator);
    }

    public void updateCoordinator(int id, Coordinator coordinator) {
        Coordinator c = coordinatorRepository.findById(id);
        if(c == null){
            throw new ApiException("Coordinator not found");
        }
        c.setName(coordinator.getName());
        c.setHonorarium(coordinator.getHonorarium());
        c.setAge(coordinator.getAge());
        c.setAssignedEvents(coordinator.getAssignedEvents());
        c.setEmail(coordinator.getEmail());
        c.setEventId(coordinator.getEventId());
        coordinatorRepository.save(c);

    }

    public void deleteCoordinator(int id) {
        Coordinator e = coordinatorRepository.findById(id);
        if(e == null){
            throw new ApiException("Coordinator not found");
        }
        coordinatorRepository.delete(e);
    }

    public void assignToEvent(int coordinatorId, int eventId) {

        Event event = eventRepository.findById(eventId);
        Coordinator coordinator = coordinatorRepository.findById(coordinatorId);

        if (event == null) {
            throw new ApiException("Event not found");
        }
        if (coordinator == null) {
            throw new ApiException("Venue not found");
        }

        coordinator.setAssignedEvents(coordinator.getAssignedEvents() + 1);
        if(coordinator.getEventId()== eventId){
            throw new ApiException("Event already assigned");
        }
        coordinator.setEventId(eventId);
        eventRepository.save(event);

    }

    public void raiseSalary(int id) {
        Coordinator e = coordinatorRepository.findById(id);
        if(e == null){
            throw new ApiException("Coordinator not found");
        }
        if(e.getAssignedEvents()>=5 && e.getAssignedEvents()<10){
            e.setHonorarium(e.getHonorarium()+(e.getHonorarium()*0.10));
            coordinatorRepository.save(e);
        }
        if(e.getAssignedEvents()>=10 && e.getAssignedEvents()<15){
            e.setHonorarium(e.getHonorarium()+(e.getHonorarium()*0.15));
            coordinatorRepository.save(e);
        }
        if(e.getAssignedEvents()>=20){
            e.setHonorarium(e.getHonorarium()+(e.getHonorarium()*0.20));
            coordinatorRepository.save(e);
        }
    }

    public List<Event> showAssignedEvents(int coordinatorId) {
        Coordinator coordinator = coordinatorRepository.findById(coordinatorId);
        List<Event> events = eventRepository.findAll();
        List<Event> assignedEvents = new ArrayList<>();
        if(coordinator == null){
            throw new ApiException("Coordinator not found");
        }
        for(Event e : events){
            if(e.getId() == coordinator.getEventId()){
                assignedEvents.add(e);
            }
        }
        return assignedEvents;
    }
}
