package org.hostel.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
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
    @ManyToOne(optional=true)
    @JoinColumn (name="apartment_id")
    private Category category;
    //@Temporal(TemporalType.DATE)
    private Date cleaningDate;
}
