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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = "apartments")
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final CategoryRepository categoryRepository;
    private final GuestRepository guestRepository;

    private static final Logger logger = LoggerFactory.getLogger(ApartmentService.class);

    public ResponseEntity<ApartmentDto> add(ApartmentDto apartmentDto) {
        if (apartmentRepository.existsByApartmentNumber(apartmentDto.getApartmentNumber())) {
            logger.error("apartment already exists with apartment number {}", apartmentDto.getApartmentNumber());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Apartment apartment = apartmentRepository.save(new Apartment(apartmentDto));
        logger.info("add apartment with id {}", apartment.getId());
        return new ResponseEntity<>(new ApartmentDto(apartment), HttpStatus.CREATED);
    }

    public ResponseEntity<ApartmentDto> remove(long id){
        if (apartmentRepository.findById(id).isPresent()) {
            apartmentRepository.deleteById(id);
            logger.info("remove apartment with id {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.error("apartment not found with id {}", id);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<ApartmentDto> setCategory(long apartmentId, long categoryId) throws ApartmentNotFoundException, CategoryNotFoundException {
        Apartment apartment = apartmentRepository.findById(apartmentId).orElseThrow(() -> new ApartmentNotFoundException(apartmentId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        apartment.setCategory(category);
        if (apartment.getCategory().getId() == categoryId) {
            logger.info("set category with id {} for apartment with id {}", category.getId(), apartment.getId());
            return new ResponseEntity<>(new ApartmentDto(apartment), HttpStatus.OK);
        }
        logger.error("set category failed with id {} for apartment with id {}", category.getId(), apartment.getId());
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Cacheable("guests")
    public ResponseEntity<List<Guest>> getGuestList(long id) throws ApartmentNotFoundException {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(() -> new ApartmentNotFoundException(id));
        List<Guest> guests = guestRepository.findAllByApartment(apartment);
        if(guests != null && !guests.isEmpty()){
            logger.info("get guest list for apartment with id {}", apartment.getId());
            return new ResponseEntity<>(guests, HttpStatus.OK);
        }
        logger.error("guest list is empty for apartment with id {}", apartment.getId());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Cacheable("apartments")
    public ResponseEntity<Integer> getRoomAmount(long id) throws ApartmentNotFoundException {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(() -> new ApartmentNotFoundException(id));
        int roomAmount = apartment.getRoomAmount();
        if(roomAmount != 0){
        logger.info("get room amount for apartment with id {}", apartment.getId());
        return new ResponseEntity<>(roomAmount, HttpStatus.OK);
        }
        logger.error("room amount not found for apartment with id {}", apartment.getId());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

