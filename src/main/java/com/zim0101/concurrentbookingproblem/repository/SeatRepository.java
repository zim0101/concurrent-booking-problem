package com.zim0101.concurrentbookingproblem.repository;

import com.zim0101.concurrentbookingproblem.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
