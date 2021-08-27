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

    public ResponseEntity<ApartmentDto> add(ApartmentDto apartmentDto) {
        return new ResponseEntity<>(new ApartmentDto(apartmentRepository.save(new Apartment(apartmentDto))), HttpStatus.CREATED);
    }

    public ResponseEntity<ApartmentDto> remove(long id) throws ApartmentNotFoundException {
        if (apartmentRepository.findById(id).isPresent()) {
            apartmentRepository.deleteById(id);
        } else {
            throw new ApartmentNotFoundException(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<ApartmentDto> setCategory(long apartmentId, long categoryId) throws ApartmentNotFoundException, CategoryNotFoundException {
        Apartment apartment = apartmentRepository.findById(apartmentId).orElseThrow(() -> new ApartmentNotFoundException(apartmentId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        apartment.setCategory(category);
        if (apartment.getCategory().getId() == categoryId) {
            return new ResponseEntity<>(new ApartmentDto(apartment), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    public ResponseEntity<List<Guest>> getGuestList(long id) throws ApartmentNotFoundException {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(() -> new ApartmentNotFoundException(id));
        List<Guest> guests = guestRepository.findAllByApartment(apartment);
        return guests != null && !guests.isEmpty() ? new ResponseEntity<>(guests, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Integer> getRoomAmount(long id) throws ApartmentNotFoundException {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(() -> new ApartmentNotFoundException(id));
        int roomAmount = apartment.getRoomAmount();
        return roomAmount != 0 ? new ResponseEntity<>(roomAmount, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

