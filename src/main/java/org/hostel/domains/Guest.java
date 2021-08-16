package org.hostel.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hostel.dto.GuestDto;

import javax.persistence.*;
import java.util.Date;


@Data
@RequiredArgsConstructor
@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String passport;
    private String fileNamePhoto;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutDate;
    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    public Guest(GuestDto guest) {
        this.id = guest.getId();
        this.fullName = guest.getFullName();
        this.passport = guest.getPassport();
        this.fileNamePhoto = guest.getFileNamePhoto();
        this.birthday = guest.getBirthday();
        this.checkInDate = guest.getCheckInDate();
        this.checkOutDate = guest.getCheckOutDate();
    }
}
