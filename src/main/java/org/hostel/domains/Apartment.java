package org.hostel.domains;

import lombok.*;
import org.hostel.dto.ApartmentDto;
import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int apartmentNumber;
    private int roomAmount;
    @OneToMany(mappedBy = "apartment", fetch=FetchType.EAGER)
    private List<Guest> guestList;
    @ManyToOne
    @JoinColumn (name="category_id")
    private Category category;
    @Temporal(TemporalType.TIMESTAMP)
    private Date cleaningDate;

    public Apartment (ApartmentDto apartment){
        this.id = apartment.getId();
        this.apartmentNumber = apartment.getApartmentNumber();
        this.roomAmount = apartment.getRoomAmount();
        this.guestList = apartment.getGuestList();
        this.category = apartment.getCategory();
        this.cleaningDate = apartment.getCleaningDate();
    }
}
