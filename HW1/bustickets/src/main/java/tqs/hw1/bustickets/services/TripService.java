package tqs.hw1.bustickets.services;

import java.util.List;

import tqs.hw1.bustickets.entities.Trip;

public interface TripService {
    public List<Trip> getTrips(String startLocation, String endLocation, String dateTime);
    public Trip getTripById(Long id);
}
