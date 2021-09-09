package org.hostel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domain.Apartment;
import org.hostel.domain.Guest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class GuestDto {
    public GuestDto(long id) {
        this.id = id;
    }

    private long id;
    private String fullName;
    private String passport;
    private String fileNamePhoto;
    private LocalDate birthday;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Apartment apartment;

    public GuestDto(Guest guest) {
        this.id = guest.getId();
        this.fullName = guest.getFullName();
        this.passport = guest.getPassport();
        this.fileNamePhoto = guest.getFileNamePhoto();
        this.birthday = guest.getBirthday();
        this.checkInDate = guest.getCheckInDate();
        this.checkOutDate = guest.getCheckOutDate();
    }
}
