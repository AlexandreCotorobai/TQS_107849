package tqs.hw1.bustickets.services;

import org.springframework.stereotype.Service;
import java.util.List;

import tqs.hw1.bustickets.entities.Trip;
import tqs.hw1.bustickets.repositories.TripRepository;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public List<Trip> getTrips(String startLocation, String endLocation, String dateTime) {
        List<Trip> allTickets = tripRepository.findAll();
        return allTickets.stream()
                .filter(trip -> startLocation == null || trip.getOrigin().equals(startLocation))
                .filter(trip -> endLocation == null || trip.getDestination().equals(endLocation))
                .filter(trip -> dateTime == null || trip.getDepartureDateTime().contains(dateTime))
                .toList();

    }

    @Override
    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }
}
