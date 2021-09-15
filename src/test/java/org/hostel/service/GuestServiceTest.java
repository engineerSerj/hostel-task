package org.hostel.service;

import org.hostel.domain.Apartment;
import org.hostel.domain.Guest;
import org.hostel.dto.GuestDto;
import org.hostel.exception.GuestNotFoundException;
import org.hostel.repositoriy.ApartmentRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServiceTest {

    @Mock
    private GuestRepository guestRepository;
    @Mock
    private ApartmentRepository apartmentRepository;

    private GuestService guestService;
    private GuestDto guestDto;
    private Apartment apartment;

    @BeforeEach
    void setUp() {
        guestService = new GuestService(guestRepository, apartmentRepository);
        guestDto = new GuestDto();
        guestDto.setId(1L);
        guestDto.setFullName("Tom Smith");
        apartment = new Apartment();
        apartment.setId(1L);
        apartment.setApartmentNumber(1);
        apartment.setRoomAmount(2);
    }

    @Test
    void canAdd() {
        try {
            guestService.add(guestDto, null);
        } catch (Exception e) {
            //throws expected NullPointerException because
            //userService method is trying to get result - Guest guest = guestRepository.save(guestToSave)
            e.printStackTrace();
        }
        Guest guestFromDTO = new Guest(guestDto);
        ArgumentCaptor<Guest> guestArgumentCaptor = ArgumentCaptor.forClass(Guest.class);

        verify(guestRepository).save(guestArgumentCaptor.capture());
        Guest capturedGuest = guestArgumentCaptor.getValue();
        System.out.println(capturedGuest.toString() + "\ncompared to \n" + guestFromDTO);
        assertThat(capturedGuest).isEqualTo(guestFromDTO);
    }

    @Test
    void canEditGuest(){
        when(guestRepository.findById(any())).thenReturn(Optional.of(new Guest(guestDto)));
        try {
            guestService.editGuest(guestDto);
        } catch (Exception e){
            e.printStackTrace();
        }
        verify(guestRepository).save(any());
    }

    @Test
    void canRemove() {
        guestService.remove(1L);
        verify(guestRepository).findById(1L);
        // if apartment with id 1L is present this guestRepository delete by id
    }

    @Test
    void canSetApartment() throws GuestNotFoundException {
        when(apartmentRepository.findById(1L)).thenReturn(Optional.of(apartment));
        when(guestRepository.findById(1L)).thenReturn(Optional.of(new Guest(guestDto)));
        ResponseEntity<GuestDto> resultResponseEntity = guestService.setApartment(guestDto.getId(), apartment.getId());
        System.out.println("guestDto sent to setApartment method " + guestDto.toString());
        guestDto.setApartment(apartment);
        ResponseEntity<GuestDto> myResponseEntity = new ResponseEntity<>(guestDto, HttpStatus.OK);
        System.out.println(resultResponseEntity.toString() + "\ncompared to \n" + myResponseEntity);
        assertThat(resultResponseEntity).isEqualTo(myResponseEntity);
    }

    @Test
    void canGetAll(){
        guestService.getAll();
        verify(guestRepository).findAll();
    }
}