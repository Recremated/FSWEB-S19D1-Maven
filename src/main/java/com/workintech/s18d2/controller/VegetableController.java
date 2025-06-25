package com.workintech.s18d2.controller;

import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.InvalidInputException;
import com.workintech.s18d2.exceptions.ResourceNotFoundException;
import com.workintech.s18d2.services.VegetableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/vegetables")
@RequiredArgsConstructor
@Slf4j
public class VegetableController {

    private final VegetableService vegetableService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Vegetable>>> getAllVegetablesAsc() {
        List<Vegetable> vegetables = vegetableService.getAllVegetablesAsc();
        return ResponseEntity.ok(new ApiResponse<>("Vegetables listed successfully.", vegetables));
    }

    @GetMapping("/desc")
    public ResponseEntity<ApiResponse<List<Vegetable>>> getAllVegetablesDesc() {
        List<Vegetable> vegetables = vegetableService.getAllVegetablesDesc();
        return ResponseEntity.ok(new ApiResponse<>("Vegetables listed in descending price order.", vegetables));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Vegetable>> getVegetableById(@PathVariable Long id) {
        validateId(id);
        Vegetable vegetable = vegetableService.getVegetableById(id).orElseThrow(() -> new ResourceNotFoundException("Vegetable not found with id: " + id));
        return ResponseEntity.ok(new ApiResponse<>("Vegetable found.", vegetable));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Vegetable>> saveOrUpdateVegetable(@Valid @RequestBody Vegetable vegetable) {
        validateVegetableData(vegetable);
        Vegetable savedVegetable = vegetableService.saveOrUpdateVegetable(vegetable);
        return ResponseEntity.ok(new ApiResponse<>("Vegetable saved successfully.", savedVegetable));
    }

    @PostMapping("/{name}")
    public ResponseEntity<ApiResponse<List<Vegetable>>> getVegetablesByName(@PathVariable String name) {
        List<Vegetable> vegetables = vegetableService.getVegetablesByName(name);
        return ResponseEntity.ok(new ApiResponse<>("Vegetables found by name.", vegetables));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVegetable(@PathVariable Long id) {
        validateId(id);
        vegetableService.getVegetableById(id).orElseThrow(() -> new ResourceNotFoundException("Vegetable not found with id: " + id));
        vegetableService.deleteVegetableById(id);
        return ResponseEntity.ok(new ApiResponse<>("Vegetable deleted successfully.", null));
    }

    private void validateId(Long id) {
        if (id < 0) {
            throw new InvalidInputException("Id must be non-negative.");
        }
    }

    private void validateVegetableData(Vegetable vegetable) {
        if (vegetable.getName() == null || vegetable.getName().isEmpty() ||
                vegetable.getPrice() == null || vegetable.getGrownOnTree() == null) {
            throw new InvalidInputException("Missing required vegetable data.");
        }
    }
}