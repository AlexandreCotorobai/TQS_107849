package tqs.cars;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.cars.data.Car;
import tqs.cars.data.CarRepository;
import tqs.cars.service.CarManagerServiceImpl;


@ExtendWith(MockitoExtension.class)
public class CarService_UnitTest {

    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerServiceImpl carService;

    @BeforeEach
    public void setUp() {

        Car tesla = new Car("Tesla", "Model S");
        tesla.setCarId(111L);

        Car ford = new Car("Ford", "Mustang");
        Car audi = new Car("Audi", "A4");

        List<Car> allCars = Arrays.asList(tesla, ford, audi);

        Mockito.when(carRepository.findByCarId(tesla.getCarId())).thenReturn(tesla);
        Mockito.when(carRepository.findByCarId(audi.getCarId())).thenReturn(audi);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        Mockito.when(carRepository.findByCarId(-99L)).thenReturn(null);
    }
    
    @Test
    void whenSearchValidId_thenCarShouldBeFound() {
        Optional<Car> found = carService.getCarDetails(111L);
        assertThat(found.get().getCarId()).isEqualTo(111L);
        verifyFindByIdIsCalledOnce();

    }

    @Test
    void whenSearchInValidId_thenCarShouldNotBeFound() {
        Optional<Car> found = carService.getCarDetails(-99L);
        assertThat(found.isEmpty()).isTrue();
        verifyFindByIdIsCalledOnce();
    }

    @Test
    void whenValidId_thenCarShouldExist() {
        boolean doesCarExist = carService.getCarDetails(111L).isPresent();
        assertThat(doesCarExist).isTrue();
        verifyFindByIdIsCalledOnce();
    }

    @Test
    void whenNonExistingId_thenCarShouldNotExist() {
        boolean doesCarExist = carService.getCarDetails(999L).isPresent();
        assertThat(doesCarExist).isFalse();
        verifyFindByIdIsCalledOnce();
    }

    @Test
    void given3Cars_whengetAll_thenReturn3Records() {
        Car tesla = new Car("Tesla", "Model S");
        Car ford = new Car("Ford", "Mustang");
        Car audi = new Car("Audi", "A4");

        List<Car> allCars = carService.getAllCars();
        assertThat(allCars).hasSize(3).extracting(Car::getMaker).contains(tesla.getMaker(), ford.getMaker(), audi.getMaker());
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(carRepository, Mockito.times(1)).findByCarId(Mockito.anyLong());
    }
}
