package com.zim0101.concurrentbookingproblem.repository;

import com.zim0101.concurrentbookingproblem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
