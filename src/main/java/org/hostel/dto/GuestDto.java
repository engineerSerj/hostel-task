package org.hostel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domains.Apartment;
import org.hostel.domains.Guest;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class GuestDto {
    private int id;
    private String fullName;
    private String passport;
    private String fileNamePhoto;
    private Date birthday;
    private Date checkInDate;
    private Date checkOutDate;
    private Apartment apartment;

    public GuestDto (Guest guest) {
        this.id = guest.getId();
        this.fullName = guest.getFullName();
        this.passport = guest.getPassport();
        this.fileNamePhoto = guest.getFileNamePhoto();
        this.birthday = guest.getBirthday();
        this.checkInDate = guest.getCheckInDate();
        this.checkOutDate = guest.getCheckOutDate();
    }
}
