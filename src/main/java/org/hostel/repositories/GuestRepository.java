package org.hostel.repositories;

import org.hostel.domains.Apartment;
import org.hostel.domains.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    List<Guest> findAllByApartment(Apartment apartment);
}
