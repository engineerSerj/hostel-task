package org.hostel.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domains.Apartment;
import org.hostel.domains.Category;
import org.hostel.domains.Guest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ApartmentDto {
    private int id;
    private int apartmentNumber;
    private int roomAmount;
    private List<Guest> guestList;
    private Category category;
    private Date cleaningDate;

    public ApartmentDto(Apartment apartment)  {
        this.id = apartment.getId();
        this.apartmentNumber = apartment.getApartmentNumber();
        this.roomAmount = apartment.getRoomAmount();
        this.guestList = apartment.getGuestList();
        this.category = apartment.getCategory();
        this.cleaningDate = apartment.getCleaningDate();

    }
}
