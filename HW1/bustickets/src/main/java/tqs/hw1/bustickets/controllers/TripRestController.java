package tqs.hw1.bustickets.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tqs.hw1.bustickets.entities.Trip;
import tqs.hw1.bustickets.services.TripService;

@RestController
@RequestMapping("/api")
public class TripRestController {
    private final TripService tripService;

    public TripRestController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getTripsByLocations(
            @RequestParam(name = "startLocation", required = false) String startLocation,
            @RequestParam(name = "endLocation", required = false) String endLocation,
            @RequestParam(name = "dateTime", required = false) String dateTime) {

        List<Trip> trips = tripService.getTrips(startLocation, endLocation, dateTime);
        if (trips.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        Trip trip = tripService.getTripById(id);
        if (trip == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

}
