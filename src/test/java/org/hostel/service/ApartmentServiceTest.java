package org.hostel.service;

import org.hostel.domain.Apartment;
import org.hostel.domain.Category;
import org.hostel.domain.CategoryName;
import org.hostel.dto.ApartmentDto;
import org.hostel.exception.ApartmentNotFoundException;
import org.hostel.exception.CategoryNotFoundException;
import org.hostel.repositoriy.ApartmentRepository;
import org.hostel.repositoriy.CategoryRepository;
import org.hostel.repositoriy.GuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApartmentServiceTest {
    @Mock
    private ApartmentRepository apartmentRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private GuestRepository guestRepository;

    private ApartmentService apartmentService;
    private ApartmentDto apartmentDto;
    private Category category;

    @BeforeEach
    void setUp() {
        apartmentService = new ApartmentService(apartmentRepository, categoryRepository, guestRepository);
        apartmentDto = new ApartmentDto();
        apartmentDto.setId(1L);
        apartmentDto.setApartmentNumber(1);
        apartmentDto.setRoomAmount(2);
        category = new Category();
        category.setId(1L);
        category.setCategoryName(CategoryName.STANDARD);
    }

    @Test
    void canAdd() {
        try {
            apartmentService.add(apartmentDto);
        } catch (Exception e) {
            //throws expected NullPointerException because
            //apartmentService method is trying to get result - Apartment apartment = apartmentRepository.save(new Apartment(apartmentDto));
            e.printStackTrace();
        }
        Apartment apartmentFromDTO = new Apartment(apartmentDto);
        ArgumentCaptor<Apartment> apartmentArgumentCaptor = ArgumentCaptor.forClass(Apartment.class);

        verify(apartmentRepository).save(apartmentArgumentCaptor.capture());
        Apartment capturedApartment = apartmentArgumentCaptor.getValue();
        System.out.println(capturedApartment.toString() + "\ncompared to \n" + apartmentFromDTO);
        assertThat(capturedApartment).isEqualTo(apartmentFromDTO);
    }

    @Test
    void canRemove() {
        apartmentService.remove(1L);
        verify(apartmentRepository).findById(1L);
        // if apartment with id 1L is present this apartmentRepository delete by id
    }

    @Test
    void canSetCategory() throws ApartmentNotFoundException, CategoryNotFoundException {
        when(apartmentRepository.findById(1L)).thenReturn(Optional.of(new Apartment(apartmentDto)));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        ResponseEntity<ApartmentDto> resultResponseEntity = apartmentService.setCategory(apartmentDto.getId(), category.getId());
        System.out.println("apartmentDto sent to setCategory method " + apartmentDto.toString());
        apartmentDto.setCategory(category);
        ResponseEntity<ApartmentDto> myResponseEntity = new ResponseEntity<>(apartmentDto, HttpStatus.OK);
        System.out.println(resultResponseEntity.toString() + "\ncompared to \n" + myResponseEntity);
        assertThat(resultResponseEntity).isEqualTo(myResponseEntity);
    }

    @Test
    void canGetGuestList() throws ApartmentNotFoundException {
        Apartment apartment = new Apartment(apartmentDto);
        when(apartmentRepository.findById(anyLong())).thenReturn(Optional.of(apartment));
        apartmentService.getGuestList(1L);
        verify(guestRepository).findAllByApartment(apartment);
    }


}