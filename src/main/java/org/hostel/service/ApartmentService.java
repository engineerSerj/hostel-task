package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.Exceptions.ApartmentNotFoundException;
import org.hostel.Exceptions.CategoryNotFoundException;
import org.hostel.domains.Apartment;
import org.hostel.domains.Category;
import org.hostel.domains.Guest;
import org.hostel.dto.ApartmentDto;
import org.hostel.repositories.ApartmentRepository;
import org.hostel.repositories.CategoryRepository;
import org.hostel.repositories.GuestRepository;
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

    public void add(ApartmentDto apartmentDto) {
        apartmentRepository.save(new Apartment(apartmentDto));
    }

    public Apartment getById(int id) throws ApartmentNotFoundException {
        return apartmentRepository.findById(id).orElseThrow(() -> new ApartmentNotFoundException(id));
    }

    public List<Guest> getGuestList(int id) throws ApartmentNotFoundException {
        return guestRepository.findAllByApartment(getById(id));
    }

    public int getRoomAmount(int id) throws ApartmentNotFoundException {
        return getById(id).getRoomAmount();
    }

    public ApartmentDto setCategory(int apartmentId, int categoryId) throws ApartmentNotFoundException, CategoryNotFoundException {
        Apartment apartment = getById(apartmentId);
        Category category = categoryRepository.getById(categoryId);
        apartment.setCategory(category);
        return new ApartmentDto(apartment);
    }

    public void remove(int id) {
        apartmentRepository.deleteById(id);
    }

}
