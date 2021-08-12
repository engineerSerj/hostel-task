package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.domains.Apartment;
import org.hostel.domains.Category;
import org.hostel.domains.Guest;
import org.hostel.repositories.ApartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public Apartment add(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    public Apartment getById(int id) {
        return apartmentRepository.getById(id);
    }

    public List<Guest> getGuestList(int id) {
        return apartmentRepository.getById(id).getGuestList();
    }

    public int getRoomAmount(int id) {
        return apartmentRepository.getById(id).getRoomAmount();
    }

    public void setCategory(int id, Category category) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        apartment.ifPresent(value -> value.setCategory(category));
    }

    public void remove(int id) {
        apartmentRepository.deleteById(id);
    }
}
