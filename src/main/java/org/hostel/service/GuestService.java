package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.exception.GuestNotFoundException;
import org.hostel.domain.Apartment;
import org.hostel.domain.Guest;
import org.hostel.dto.GuestDto;
import org.hostel.repositoriy.ApartmentRepository;
import org.hostel.repositoriy.GuestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class GuestService {

    private final GuestRepository guestRepository;
    private final ApartmentRepository apartmentRepository;

    private static final Logger logger = LoggerFactory.getLogger(GuestService.class);

    @Value("${upload.path}")
    private String uploadPath;

    public ResponseEntity<GuestDto> add(GuestDto guestDto, MultipartFile file) throws IOException {
        if (guestRepository.existsByFullName(guestDto.getFullName())) {
            logger.warn("guest already exists with full name {}", guestDto.getPassport());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Guest guestToSave = new Guest(guestDto);
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));
            guestToSave.setFileNamePhoto(resultFileName);
        }
        Guest guest = guestRepository.save(guestToSave);
        logger.info("add guest with id {}", guest.getId());
        return new ResponseEntity<>(new GuestDto(guest), HttpStatus.CREATED);
    }

    public ResponseEntity<GuestDto> remove(long id) {
        if (guestRepository.findById(id).isPresent()) {
            guestRepository.deleteById(id);
            logger.info("remove guest with id {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.warn("guest not found with id {}", id);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<GuestDto> setApartment(long guestId, long apartmentId) throws GuestNotFoundException {
        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new GuestNotFoundException(guestId));
        Apartment apartment = apartmentRepository.findById(apartmentId).orElseThrow(() -> new GuestNotFoundException(apartmentId));
        guest.setApartment(apartment);
        if (guest.getApartment().getId() == apartmentId) {
            logger.info("set apartment with id {} for guest with id {}", apartment.getId(), guest.getId());
            return new ResponseEntity<>(new GuestDto(guest), HttpStatus.OK);
        }
        logger.warn("set apartment failed with id {} for guest with id {}", apartment.getId(), guest.getId());
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    public ResponseEntity<GuestDto> editGuest(GuestDto guestDto) {
        if (!guestRepository.findById(guestDto.getId()).isPresent()) {
            logger.info("guest not found with id {} ", guestDto.getId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Guest guest = guestRepository.save(new Guest(guestDto));
        logger.info("update guest with id {} and full name {}", guest.getId(), guest.getFullName());
        return new ResponseEntity<>(new GuestDto(guest), HttpStatus.OK);
    }

    public ResponseEntity<List<Guest>> getAll() {
        logger.info("get all guests");
        return new ResponseEntity<>(guestRepository.findAll(), HttpStatus.OK);
    }
}

