package org.lhind.SpringBootExercise.repository;

import org.lhind.SpringBootExercise.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT flight_id FROM new_db.booking_flight WHERE booking_id = :id", nativeQuery = true)
    List<Integer> findAllById(@Param("id") Integer id);
}