package org.hostel.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import lombok.RequiredArgsConstructor;
import org.hostel.actor.SpringExtension;
import org.hostel.domain.Guest;
import org.hostel.dto.GuestDto;
import org.hostel.exception.GuestNotFoundException;
import org.hostel.service.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/guest")
@RequiredArgsConstructor
@RestController
public class GuestController {

    private final GuestService guestService;
    private final ActorSystem system;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<?> add(@RequestBody GuestDto guestDto, @RequestParam("file") MultipartFile file) throws IOException {
        return guestService.add(guestDto, file);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<?> remove(@PathVariable("id") long id) {
        ActorRef removerActorRef = system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).props("removerActor"), "removerActorRef");
        removerActorRef.tell (new GuestDto(id), null);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{guestId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<GuestDto> setApartment(@PathVariable("guestId") long guestId, @RequestParam("apartmentId") long apartmentId) throws GuestNotFoundException {
        return guestService.setApartment(guestId, apartmentId);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<GuestDto> editGuest(@RequestBody GuestDto guestDto) {
        return guestService.editGuest(guestDto);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<List<Guest>> getAllGuests() {
        return guestService.getAll();
    }
}
