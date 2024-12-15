package pl.edu.pjatk.MPR_Projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Projekt.model.Car;
import pl.edu.pjatk.MPR_Projekt.service.CarService;

import java.util.List;

@RestController
public class MyRestController {

    private final CarService carService;

    @Autowired
    public MyRestController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = CarService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car savedCar = carService.addCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id) {
        Car car = carService.getCarById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        Car updatedCar = carService.updateCar(id, car);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }
}


//Klasa MyRestController będzie pełnić rolę kontrolera REST (ang. REST controller)
// w aplikacji Spring Boot. Kontroler REST jest odpowiedzialny za obsługę żądań HTTP
// przychodzących do aplikacji i mapowanie tych żądań na odpowiednie metody obsługujące logikę biznesową.
//W tym przypadku, klasa MyRestController będzie obsługiwać następujące operacje na zasobach samochodów
// (reprezentowanych przez klasę Car):
//
//Pobranie listy wszystkich samochodów (GET /cars)
//Dodanie nowego samochodu (POST /cars)
//Usunięcie istniejącego samochodu (DELETE /cars/{id})
//Pobranie informacji o pojedynczym samochodzie (GET /cars/{id})
//Aktualizacja istniejącego samochodu (PUT /cars/{id})
//
//Każda z tych operacji jest zaimplementowana jako metoda w klasie MyRestController,
// z odpowiednimi adnotacjami mapującymi je na konkretne punkty końcowe (endpoints) REST API.
//Kontroler REST jest ważnym elementem architektury aplikacji opartej na REST,
// ponieważ odpowiada za komunikację między zewnętrznymi klientami (np. aplikacjami mobilnymi,
// web aplikacjami, innymi systemami) a logiką biznesową aplikacji.
// Dzięki temu użytkownicy lub inne systemy mogą w łatwy sposób korzystać z funkcjonalności aplikacji
// przez interfejs REST API.