package org.hostel.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.dto.GuestDto;
import org.hostel.util.DateTimeUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@RequiredArgsConstructor
@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String passport;
    private String fileNamePhoto;
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate birthday;
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime checkInDate;
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime checkOutDate;
    @ManyToOne( fetch = FetchType.EAGER)
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
