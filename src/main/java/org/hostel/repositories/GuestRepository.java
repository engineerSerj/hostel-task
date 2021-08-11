package org.hostel.repositories;

import org.hostel.domains.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    Collection<Guest> getAllById (int id);
}