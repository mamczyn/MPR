package pl.edu.pjatk.MPR_Projekt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.MPR_Projekt.model.Car;
import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
//    List<Car> findById(Long id);
    List<Car> findByName(String name);
    List<Car> findByYear(int year);
}