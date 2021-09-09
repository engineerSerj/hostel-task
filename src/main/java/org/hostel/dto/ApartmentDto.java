package org.hostel.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domain.Apartment;
import org.hostel.domain.Category;
import org.hostel.domain.Guest;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ApartmentDto {

    public ApartmentDto(long id) {
        this.id = id;
    }

    private long id;
    private int apartmentNumber;
    private int roomAmount;
    private List<Guest> guestList;
    private Category category;
    private LocalDateTime cleaningDate;

    public ApartmentDto(Apartment apartment)  {
        this.id = apartment.getId();
        this.apartmentNumber = apartment.getApartmentNumber();
        this.roomAmount = apartment.getRoomAmount();
        this.guestList = apartment.getGuestList();
        this.category = apartment.getCategory();
        this.cleaningDate = apartment.getCleaningDate();

    }
}
