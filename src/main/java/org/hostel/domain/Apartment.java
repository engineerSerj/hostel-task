package org.hostel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hostel.dto.ApartmentDto;
import org.hostel.util.DateTimeUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int apartmentNumber;
    private int roomAmount;
    @OneToMany(mappedBy = "apartment", fetch = FetchType.EAGER)
    private List<Guest> guestList;
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime cleaningDate;

    public Apartment(ApartmentDto apartment) {
        this.id = apartment.getId();
        this.apartmentNumber = apartment.getApartmentNumber();
        this.roomAmount = apartment.getRoomAmount();
        this.guestList = apartment.getGuestList();
        this.category = apartment.getCategory();
        this.cleaningDate = apartment.getCleaningDate();
    }
}
