package pl.edu.pjatk.MPR_Projekt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int year;
    private int identifier;

    public Car (int id,String name, int year) {
        this.id = (long) id;
        this.name = name;
        this.year = year;
        calculateIdentifier();

        }
    private void calculateIdentifier() {
        this.identifier = name.chars().sum() + String.valueOf(year).chars().sum();
    }

    public Car() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

