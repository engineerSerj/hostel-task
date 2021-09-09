package org.hostel.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import lombok.RequiredArgsConstructor;
import org.hostel.actor.SpringExtension;
import org.hostel.domain.Guest;
import org.hostel.dto.ApartmentDto;
import org.hostel.exception.ApartmentNotFoundException;
import org.hostel.exception.CategoryNotFoundException;
import org.hostel.service.ApartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/apartment")
@RequiredArgsConstructor
@RestController
public class ApartmentController {

    private final ApartmentService apartmentService;
    private final ActorSystem system;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApartmentDto> add(@RequestBody ApartmentDto apartmentDto) {
        return apartmentService.add(apartmentDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> remove(@PathVariable("id") long id) throws ApartmentNotFoundException {
        ActorRef removerActorRef = system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).props("removerActor"), "removerActorRef");
        removerActorRef.tell (new ApartmentDto(id), null);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{apartmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApartmentDto> setCategory(@PathVariable("apartmentId") long apartmentId, @RequestParam("categoryId") long categoryId) throws ApartmentNotFoundException, CategoryNotFoundException {
        return apartmentService.setCategory(apartmentId, categoryId);
    }

    @GetMapping("/{id}/guestList")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<List<Guest>> getGuestList(@PathVariable("id") long id) throws ApartmentNotFoundException {
        return apartmentService.getGuestList(id);
    }

    @GetMapping("/{id}/roomAmount")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<Integer> getRoomAmount(@PathVariable("id") long id) throws ApartmentNotFoundException {
        return apartmentService.getRoomAmount(id);
    }

}
