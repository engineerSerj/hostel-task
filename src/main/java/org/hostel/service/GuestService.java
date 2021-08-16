package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.Exceptions.GuestNotFoundException;
import org.hostel.domains.Apartment;
import org.hostel.domains.Guest;
import org.hostel.dto.GuestDto;
import org.hostel.repositories.ApartmentRepository;
import org.hostel.repositories.GuestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GuestService {

    private final GuestRepository guestRepository;
    private final ApartmentRepository apartmentRepository;

    public void add(GuestDto guestDto) {
        guestRepository.save(new Guest(guestDto));
    }

    public void remove(int id) {
        guestRepository.deleteById(id);
    }

    public GuestDto setApartment(int guestId, int apartmentId) throws GuestNotFoundException {
        Guest guest = getById(guestId);
        Apartment apartment = apartmentRepository.getById(apartmentId);
        guest.setApartment(apartment);
        return new GuestDto(guest);
    }

    public GuestDto editGuest(int id, GuestDto guest) {
        return new GuestDto(guestRepository.save(new Guest(guest)));
    }

    public List<Guest> getAll() {
        return guestRepository.findAll();
    }

    public Guest getById(int id) throws GuestNotFoundException {
        return guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException(id));
    }

}
