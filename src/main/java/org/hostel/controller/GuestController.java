package org.hostel.controller;

import lombok.RequiredArgsConstructor;
import org.hostel.exception.GuestNotFoundException;
import org.hostel.domain.Guest;
import org.hostel.dto.GuestDto;
import org.hostel.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/guest")
@RequiredArgsConstructor
@RestController
public class GuestController {

    private final GuestService guestService;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody GuestDto guestDto) {
        return guestService.add(guestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") long id) {
        return guestService.remove(id);
    }

    @PutMapping("/{guestId}")
    public ResponseEntity<GuestDto> setApartment(@PathVariable("guestId") long guestId, @RequestParam("apartmentId") long apartmentId) throws GuestNotFoundException {
        return guestService.setApartment(guestId, apartmentId);
    }

    @PutMapping()
    public ResponseEntity<GuestDto> editGuest(@RequestBody GuestDto guestDto) {
        return guestService.editGuest(guestDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Guest>> getAllGuests() {
        return guestService.getAll();
    }
}
