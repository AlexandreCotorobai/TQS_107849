package tqs.hw1.bustickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tqs.hw1.bustickets.entities.*;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    
}
