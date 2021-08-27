package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.exception.CategoryNotFoundException;
import org.hostel.exception.GuestNotFoundException;
import org.hostel.domain.Apartment;
import org.hostel.domain.Guest;
import org.hostel.dto.GuestDto;
import org.hostel.repositoriy.ApartmentRepository;
import org.hostel.repositoriy.GuestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GuestService {

    private final GuestRepository guestRepository;
    private final ApartmentRepository apartmentRepository;

    public ResponseEntity<GuestDto> add(GuestDto guestDto) {
        return new ResponseEntity<>(new GuestDto(guestRepository.save(new Guest(guestDto))), HttpStatus.CREATED);
    }

    public ResponseEntity<GuestDto> remove(long id) throws CategoryNotFoundException {
        if (guestRepository.findById(id).isPresent()) {
            guestRepository.deleteById(id);
        } else {
            throw new CategoryNotFoundException(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<GuestDto> setApartment(long guestId, long apartmentId) throws GuestNotFoundException {
        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new GuestNotFoundException(guestId));
        Apartment apartment = apartmentRepository.findById(apartmentId).orElseThrow(() -> new GuestNotFoundException(apartmentId));
        guest.setApartment(apartment);
        if (guest.getApartment().getId() == apartmentId) {
            return new ResponseEntity<>(new GuestDto(guest), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    public ResponseEntity<GuestDto> editGuest(GuestDto guestDto) {
        guestRepository.save(new Guest(guestDto));
        return new ResponseEntity<>(guestDto, HttpStatus.OK);
    }

    public ResponseEntity<List<Guest>> getAll() {
        return new ResponseEntity<>(guestRepository.findAll(), HttpStatus.OK);
    }
}

