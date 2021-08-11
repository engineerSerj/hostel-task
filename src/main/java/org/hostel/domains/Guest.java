package org.hostel.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
    private Date birthday;
    private Date checkInDate;
    private Date checkOutDate;
    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Apartment apartment;
}
