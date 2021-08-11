package org.hostel.repositories;

import org.hostel.domains.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment,Integer> {

    Apartment getApartmentByApartmentNumber(int number);
}
