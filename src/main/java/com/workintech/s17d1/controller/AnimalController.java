package com.workintech.s17d1.controller;

import com.workintech.s17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/workintech/animal")
public class AnimalController {
    private Map<Integer, Animal> animals;
    @Value("${s17d1.application.owner}")
    private String owner;

    @Value("${s17d1.application.name}")
    private String etut1;


    @PostConstruct
    public void init(){
        animals = new HashMap<>();
        animals.put(1,new Animal(1,"kuş"));
        animals.put(2,new Animal(2,"aslan"));
    }
    @GetMapping
    public List<Animal> getAll(){
        return new ArrayList<>(animals.values());
    }
    @GetMapping(path = "/{id}")
    public Animal getById(@PathVariable Integer id ){
        return animals.get(id);
    }

    @PostMapping
    public Animal save(@RequestBody Animal animal){
        animals.put(animal.getId(), animal);
        System.out.println("eklenen" + animal.toString());
        return animal;
    }
    @PutMapping(path = "/{id}")
    public Animal update(@PathVariable("id") Integer existingRecordId, @RequestBody Animal newAnimal){
        animals.replace(existingRecordId, newAnimal);
        return animals.get(newAnimal.getId());
    }
    @DeleteMapping(path = "/{id}")
    public void  delete(@PathVariable("id") Integer id){
        animals.remove(id);

    }
}
