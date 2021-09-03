package org.hostel.controller;

import lombok.RequiredArgsConstructor;
import org.hostel.exception.CategoryNotFoundException;
import org.hostel.exception.GuestNotFoundException;
import org.hostel.domain.Guest;
import org.hostel.dto.GuestDto;
import org.hostel.service.GuestService;
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

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<?> add(@RequestBody GuestDto guestDto, @RequestParam("file") MultipartFile file) throws IOException {
        return guestService.add(guestDto, file);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<?> remove(@PathVariable("id") long id) {
        return guestService.remove(id);
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
