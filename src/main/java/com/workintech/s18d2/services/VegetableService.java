package com.workintech.s18d2.services;

import com.workintech.s18d2.entity.Vegetable;

import java.util.List;
import java.util.Optional;

public interface VegetableService {

    List<Vegetable> getAllVegetablesAsc();

    List<Vegetable> getAllVegetablesDesc();

    Optional<Vegetable> getVegetableById(Long id);

    Vegetable saveOrUpdateVegetable(Vegetable vegetable);

    void deleteVegetableById(Long id);

    List<Vegetable> getVegetablesByName(String name);
}
