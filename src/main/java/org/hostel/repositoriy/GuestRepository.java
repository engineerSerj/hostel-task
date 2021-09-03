package org.hostel.repositoriy;

import org.hostel.domain.Apartment;
import org.hostel.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findAllByApartment(Apartment apartment);

    boolean existsByFullName(String fullName);
}
