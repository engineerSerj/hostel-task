package org.hostel.controller;

import lombok.RequiredArgsConstructor;
import org.hostel.domain.Guest;
import org.hostel.exception.ApartmentNotFoundException;
import org.hostel.exception.CategoryNotFoundException;
import org.hostel.dto.ApartmentDto;
import org.hostel.service.ApartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/apartment")
@RequiredArgsConstructor
@RestController
public class ApartmentController {

    private final ApartmentService apartmentService;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody ApartmentDto apartmentDto) {
        return apartmentService.add(apartmentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") long id) {
        return apartmentService.remove(id);
    }

    @PutMapping("/{apartmentId}")
    public ResponseEntity<ApartmentDto> setCategory(@PathVariable("apartmentId") long apartmentId, @RequestParam("categoryId") long categoryId) throws ApartmentNotFoundException{
        return apartmentService.setCategory(apartmentId, categoryId);
    }

    @GetMapping("/{id}/guestList")
    public ResponseEntity<List<Guest>> getGuestList(@PathVariable("id") long id) throws ApartmentNotFoundException {
        return apartmentService.getGuestList(id);
    }

    @GetMapping("/{id}/roomAmount")
    public ResponseEntity<Integer> getRoomAmount(@PathVariable("id") long id) throws ApartmentNotFoundException {
        return apartmentService.getRoomAmount(id);
    }

}
