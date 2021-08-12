package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.domains.Apartment;
import org.hostel.domains.Guest;
import org.hostel.repositories.GuestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GuestService {

    private final GuestRepository guestRepository;

    public Guest add(Guest guest) {
        return guestRepository.save(guest);
    }

    public void remove(int id) {
        guestRepository.deleteById(id);
    }

    public List<Guest> getAll() {
        return guestRepository.findAll();
    }

    public void setApartment(int id, Apartment apartment) {
        Optional<Guest> guest = guestRepository.findById(id);
        guest.ifPresent(value -> value.setApartment(apartment));
    }

    public void editGuest(int id, Guest guest) {
        Optional<Guest> editGuest = guestRepository.findById(id);
        editGuest.ifPresent(g -> {
            g.setBirthday(guest.getBirthday());
            g.setPassport(guest.getPassport());
            g.setFullName(guest.getFullName());
            g.setFileNamePhoto(guest.getFileNamePhoto());
            g.setCheckInDate(guest.getCheckInDate());
            g.setCheckOutDate(guest.getCheckOutDate());
        });
    }

    public Guest getById(int id) {
        return guestRepository.getById(id);
    }
}
