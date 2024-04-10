package tqs.cars.boundary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqs.cars.service.CarManagerService;
import tqs.cars.data.Car;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarManagerService carManagerService;


    public CarController(CarManagerService carManagerService) {
        this.carManagerService = carManagerService;
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        HttpStatus status = HttpStatus.CREATED;
        Car createdCar = this.carManagerService.save(car);
        return new ResponseEntity<>(createdCar, status);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return this.carManagerService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Car car = this.carManagerService.getCarDetails(id).orElse(null);
        HttpStatus status = car == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(car, status);
    }
}