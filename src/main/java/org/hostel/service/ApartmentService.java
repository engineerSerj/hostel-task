package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.exception.ApartmentNotFoundException;
import org.hostel.exception.CategoryNotFoundException;
import org.hostel.domain.Apartment;
import org.hostel.domain.Category;
import org.hostel.domain.Guest;
import org.hostel.dto.ApartmentDto;
import org.hostel.repositoriy.ApartmentRepository;
import org.hostel.repositoriy.CategoryRepository;
import org.hostel.repositoriy.GuestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final CategoryRepository categoryRepository;
    private final GuestRepository guestRepository;

    public ResponseEntity<?> add(ApartmentDto apartmentDto) {
        apartmentRepository.save(new Apartment(apartmentDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> remove(long id) {
        apartmentRepository.deleteById(id);
        return !apartmentRepository.findById(id).isPresent() ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    public ResponseEntity<ApartmentDto> setCategory(long apartmentId, long categoryId) throws ApartmentNotFoundException {
        Apartment apartment = getById(apartmentId);
        Category category = categoryRepository.getById(categoryId);
        apartment.setCategory(category);
        if (apartment.getCategory().getId() == categoryId) {
            return new ResponseEntity<>(new ApartmentDto(apartment), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    public ResponseEntity<List<Guest>> getGuestList(long id) throws ApartmentNotFoundException {
        List<Guest> guests = guestRepository.findAllByApartment(getById(id));
        return guests != null && !guests.isEmpty() ? new ResponseEntity<>(guests, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Integer> getRoomAmount(long id) throws ApartmentNotFoundException {
        int roomAmount = getById(id).getRoomAmount();
        return roomAmount != 0 ? new ResponseEntity<>(roomAmount, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public Apartment getById(long id) throws ApartmentNotFoundException {
        return apartmentRepository.findById(id).orElseThrow(() -> new ApartmentNotFoundException(id));
    }
}
