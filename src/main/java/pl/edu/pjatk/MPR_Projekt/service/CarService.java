package pl.edu.pjatk.MPR_Projekt.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.MPR_Projekt.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CarService {

    private static List<Car> cars;

    public CarService() {
        this.cars = new ArrayList<>();
        this.cars.add(new Car(1L, "Toyota Corolla", 2015));
        this.cars.add(new Car(2L, "Honda Civic", 2018));
        this.cars.add(new Car(3L, "Ford Mustang", 2020));
    }

    public static List<Car> getAllCars() {
        return cars;
    }

    public Car addCar(Car car) {
        car.setId(getNextId());
        cars.add(car);
        return car;
    }

    public void deleteCar(Long id) {
        cars.removeIf(car -> car.getId().equals(id));
    }

    public Car getCarById(Long id) {
        Optional<Car> optionalCar = cars.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst();
        return optionalCar.orElse(null);
    }

    public Car updateCar(Long id, Car updatedCar) {
        Car car = getCarById(id);
        if (car != null) {
            car.setName(updatedCar.getName());
            car.setYear(updatedCar.getYear());
        }
        return car;
    }

    private Long getNextId() {
        return cars.stream()
                .mapToLong(Car::getId)
                .max()
                .orElse(0L) + 1;
    }
}