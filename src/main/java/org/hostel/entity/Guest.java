package org.hostel.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="guests")
public class Guest {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String passport;
    private String fileNamePhoto;
    private Date birthday;
    private Date checkInDate;
    private Date checkOutDate;
    @ManyToOne
    @JoinColumn (name="guest_id")
    private Apartment apartment;

    public Guest() {
    }

    public Guest(String fullName, String passport, String fileNamePhoto, Date birthday, Date checkInDate, Date checkOutDate) {
        this.fullName = fullName;
        this.passport = passport;
        this.fileNamePhoto = fileNamePhoto;
        this.birthday = birthday;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getFileNamePhoto() {
        return fileNamePhoto;
    }

    public void setFileNamePhoto(String fileNamePhoto) {
        this.fileNamePhoto = fileNamePhoto;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guest guest = (Guest) o;

        if (id != guest.id) return false;
        if (fullName != null ? !fullName.equals(guest.fullName) : guest.fullName != null) return false;
        if (passport != null ? !passport.equals(guest.passport) : guest.passport != null) return false;
        if (fileNamePhoto != null ? !fileNamePhoto.equals(guest.fileNamePhoto) : guest.fileNamePhoto != null)
            return false;
        if (birthday != null ? !birthday.equals(guest.birthday) : guest.birthday != null) return false;
        if (checkInDate != null ? !checkInDate.equals(guest.checkInDate) : guest.checkInDate != null) return false;
        if (checkOutDate != null ? !checkOutDate.equals(guest.checkOutDate) : guest.checkOutDate != null) return false;
        return apartment != null ? apartment.equals(guest.apartment) : guest.apartment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (fileNamePhoto != null ? fileNamePhoto.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (checkInDate != null ? checkInDate.hashCode() : 0);
        result = 31 * result + (checkOutDate != null ? checkOutDate.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", passport='" + passport + '\'' +
                ", fileNamePhoto='" + fileNamePhoto + '\'' +
                ", birthday=" + birthday +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", apartment=" + apartment +
                '}';
    }
}
