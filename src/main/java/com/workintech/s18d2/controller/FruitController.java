package com.workintech.s18d2.controller;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruit")
public class FruitController {

    private final FruitService fruitService;

    @Autowired
    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping
    public ResponseEntity<List<Fruit>> getFruits() {
        List<Fruit> fruits = fruitService.getByPriceAsc();
        return ResponseEntity.ok(fruits);
    }

    @GetMapping("/desc")
    public ResponseEntity<List<Fruit>> getFruitsDesc() {
        List<Fruit> fruits = fruitService.getByPriceDesc();
        return ResponseEntity.ok(fruits);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Fruit>> getFruitsByName(@PathVariable String name) {
        List<Fruit> fruits = fruitService.searchByName(name);
        return ResponseEntity.ok(fruits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fruit> getFruitById(@PathVariable Long id) {
        Fruit fruit = fruitService.getById(id);
        return ResponseEntity.ok(fruit);
    }

    @PostMapping
    public ResponseEntity<Fruit> saveFruit(@RequestBody Fruit fruit) {
        Fruit savedFruit = fruitService.save(fruit);
        return ResponseEntity.ok(savedFruit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Fruit> deleteFruit(@PathVariable Long id) {
        Fruit deletedFruit = fruitService.delete(id);
        return ResponseEntity.ok(deletedFruit);
    }
}