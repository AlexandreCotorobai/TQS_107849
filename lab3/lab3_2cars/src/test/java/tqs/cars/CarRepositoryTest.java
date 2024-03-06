package tqs.cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tqs.cars.data.Car;
import tqs.cars.data.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void whenFindCarById_thenReturnCar() {
        Car tesla = new Car("Tesla", "Model S");
        entityManager.persistAndFlush(tesla);

        Car found = carRepository.findByCarId(tesla.getCarId());
        assertThat(found).isEqualTo(tesla);
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findByCarId(-99L);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car tesla = new Car("Tesla", "Model S");
        Car ford = new Car("Ford", "Mustang");
        Car audi = new Car("Audi", "A4");

        entityManager.persist(tesla);
        entityManager.persist(ford);
        entityManager.persist(audi);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getModel).containsOnly(tesla.getModel(), ford.getModel(), audi.getModel());
    }
    
}
