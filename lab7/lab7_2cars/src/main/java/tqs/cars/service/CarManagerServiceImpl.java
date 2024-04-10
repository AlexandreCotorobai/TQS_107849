package tqs.cars.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.cars.data.Car;
import tqs.cars.data.CarRepository;


@Service
public class CarManagerServiceImpl implements CarManagerService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car save(Car car) {
        return this.carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return this.carRepository.findAll();
    }

    @Override
    public Optional<Car> getCarDetails(long id) {
        return Optional.ofNullable(this.carRepository.findByCarId(id));
    }
    
}
