package tqs.cars.service;

import tqs.cars.data.*;
import java.util.List;
import java.util.Optional;

public interface CarManagerService {

    Car save(Car car);

    List<Car> getAllCars();

    Optional<Car> getCarDetails(long id);
}