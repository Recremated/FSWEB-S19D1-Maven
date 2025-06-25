package com.workintech.s18d2.controller;


import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.exceptions.InvalidInputException;
import com.workintech.s18d2.exceptions.ResourceNotFoundException;
import com.workintech.s18d2.services.FruitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/fruits")
@RequiredArgsConstructor
@Slf4j
public class FruitController {

    private final FruitService fruitService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Fruit>>> getAllFruitsAsc() {
        List<Fruit> fruits = fruitService.getAllFruitsAsc();
        return ResponseEntity.ok(new ApiResponse<>("Fruits listed successfully.", fruits));
    }

    @GetMapping("/desc")
    public ResponseEntity<ApiResponse<List<Fruit>>> getAllFruitsDesc() {
        List<Fruit> fruits = fruitService.getAllFruitsDesc();
        return ResponseEntity.ok(new ApiResponse<>("Fruits listed in descending price order.", fruits));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Fruit>> getFruitById(@PathVariable Long id) {
        validateId(id);
        Fruit fruit = fruitService.getFruitById(id).orElseThrow(() -> new ResourceNotFoundException("Fruit not found with id: " + id));
        return ResponseEntity.ok(new ApiResponse<>("Fruit found.", fruit));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Fruit>> saveOrUpdateFruit(@Valid @RequestBody Fruit fruit) {
        validateFruitData(fruit);
        Fruit savedFruit = fruitService.saveOrUpdateFruit(fruit);
        return ResponseEntity.ok(new ApiResponse<>("Fruit saved successfully.", savedFruit));
    }

    @PostMapping("/{name}")
    public ResponseEntity<ApiResponse<List<Fruit>>> getFruitsByName(@PathVariable String name) {
        List<Fruit> fruits = fruitService.getFruitsByName(name);
        return ResponseEntity.ok(new ApiResponse<>("Fruits found by name.", fruits));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFruit(@PathVariable Long id) {
        validateId(id);
        fruitService.getFruitById(id).orElseThrow(() -> new ResourceNotFoundException("Fruit not found with id: " + id));
        fruitService.deleteFruitById(id);
        return ResponseEntity.ok(new ApiResponse<>("Fruit deleted successfully.", null));
    }

    private void validateId(Long id) {
        if (id < 0) {
            throw new InvalidInputException("Id must be non-negative.");
        }
    }

    private void validateFruitData(Fruit fruit) {
        if (fruit.getName() == null || fruit.getName().isEmpty() || fruit.getPrice() == null || fruit.getFruitType() == null) {
            throw new InvalidInputException("Missing required fruit data.");
        }
    }
}
