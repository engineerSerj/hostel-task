package org.hostel.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
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

    public Apartment() {
    }

    public Apartment(int apartmentNumber, int roomAmount, List<Guest> guestList, Date cleaningDate) {
        this.apartmentNumber = apartmentNumber;
        this.roomAmount = roomAmount;
        this.guestList = guestList;
        this.cleaningDate = cleaningDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public int getRoomAmount() {
        return roomAmount;
    }

    public void setRoomAmount(int roomAmount) {
        this.roomAmount = roomAmount;
    }

    public List<Guest> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<Guest> guestList) {
        this.guestList = guestList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCleaningDate() {
        return cleaningDate;
    }

    public void setCleaningDate(Date cleaningDate) {
        this.cleaningDate = cleaningDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        if (id != apartment.id) return false;
        if (apartmentNumber != apartment.apartmentNumber) return false;
        if (roomAmount != apartment.roomAmount) return false;
        if (guestList != null ? !guestList.equals(apartment.guestList) : apartment.guestList != null) return false;
        if (category != null ? !category.equals(apartment.category) : apartment.category != null) return false;
        return cleaningDate != null ? cleaningDate.equals(apartment.cleaningDate) : apartment.cleaningDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + apartmentNumber;
        result = 31 * result + roomAmount;
        result = 31 * result + (guestList != null ? guestList.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (cleaningDate != null ? cleaningDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", apartmentNumber=" + apartmentNumber +
                ", roomAmount=" + roomAmount +
                ", category=" + category +
                ", cleaningDate=" + cleaningDate +
                '}';
    }
}
