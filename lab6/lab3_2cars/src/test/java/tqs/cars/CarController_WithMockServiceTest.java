package tqs.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqs.cars.boundary.*;
import tqs.cars.data.Car;
import tqs.cars.service.CarManagerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
class CarController_WithMockServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenPostCar_thenCreateCar() throws Exception {
        Car ferrari = new Car("Ferrari", "F50");

        when(service.save(Mockito.any())).thenReturn(ferrari);

        mvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(ferrari)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Ferrari")))
                .andExpect(jsonPath("$.model", is("F50")));

        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car ferrari = new Car("Ferrari", "F50");
        Car lamborghini = new Car("Lamborghini", "Aventador");
        Car porsche = new Car("Porsche", "911");

        List<Car> allCars = Arrays.asList(ferrari, lamborghini, porsche);

        when(service.getAllCars()).thenReturn(allCars);

        mvc.perform(
                get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maker", is(ferrari.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(lamborghini.getMaker())))
                .andExpect(jsonPath("$[2].maker", is(porsche.getMaker())));

        verify(service, times(1)).getAllCars();
    }

    @Test
    void whenGetCarByID_thenReturnJson() throws Exception {
        Car ferrari = new Car("Ferrari", "F50");

        when(service.getCarDetails(ferrari.getCarId())).thenReturn(Optional.of(ferrari));

        mvc.perform(
                get("/api/cars/" + ferrari.getCarId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maker", is(ferrari.getMaker()))
                )
                .andExpect(jsonPath("$.model", is(ferrari.getModel())));

        verify(service, times(1)).getCarDetails(ferrari.getCarId());
    }

    @Test
    void whenGetCarByID_thenReturnNotFound() throws Exception {
        when(service.getCarDetails(1L)).thenReturn(Optional.empty());

        mvc.perform(
                get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(1)).getCarDetails(1L);
    }
}