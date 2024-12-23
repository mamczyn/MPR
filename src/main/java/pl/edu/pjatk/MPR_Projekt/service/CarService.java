package pl.edu.pjatk.MPR_Projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.MPR_Projekt.model.Car;
import pl.edu.pjatk.MPR_Projekt.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service  // zamiast @Component używamy @Service
public class CarService {

    private final CarRepository carRepository; // dodajemy pole carRepository

    @Autowired // dodajemy wstrzykiwanie zależności
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
        // inicjalizacja przykładowych danych
        if (getAllCars().isEmpty()) {
            addCar(new Car(1,"Toyota Corolla", 2015));
            addCar(new Car(2,"Honda Civic", 2018));
            addCar(new Car(3,"Ford Mustang", 2020));
        }
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(cars::add);
        return cars;
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car getCarByName(String name) {
        return carRepository.findByName(name).stream().findFirst().orElse(null);
    }

    public Car getCarByYear(int year) {
        return carRepository.findByYear(year).stream().findFirst().orElse(null);
    }

    public Car updateCar(Long id, Car updatedCar) {
        Optional<Car> existingCar = carRepository.findById(id);
        if (existingCar.isPresent()) {
            Car car = existingCar.get();
            car.setName(updatedCar.getName());
            car.setYear(updatedCar.getYear());
            return carRepository.save(car);
        }
        return null;
    }

    public Car patchCar(Long id, Car updatedCar) {
        Optional<Car> existingCar = carRepository.findById(id); // poprawione z CarRepository na carRepository
        if (existingCar.isPresent()) {
            Car car = existingCar.get();
            if (updatedCar.getName() != null) {
                car.setName(updatedCar.getName());
            }
            if (updatedCar.getYear() != 0) {
                car.setYear(updatedCar.getYear());
            }
            return carRepository.save(car);
        }
        return null;
    }
}