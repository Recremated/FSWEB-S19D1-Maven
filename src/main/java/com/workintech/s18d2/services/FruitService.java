package com.workintech.s18d2.services;

import com.workintech.s18d2.entity.Fruit;
import java.util.List;
import java.util.Optional;

public interface FruitService {

    List<Fruit> getAllFruitsAsc();

    List<Fruit> getAllFruitsDesc();

    Optional<Fruit> getFruitById(Long id);

    Fruit saveOrUpdateFruit(Fruit fruit);

    void deleteFruitById(Long id);

    List<Fruit> getFruitsByName(String name);
}
