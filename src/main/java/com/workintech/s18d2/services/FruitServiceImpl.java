package com.workintech.s18d2.services;
import com.workintech.s18d2.dao.FruitRepository;
import com.workintech.s18d2.entity.Fruit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FruitServiceImpl implements FruitService {

    private final FruitRepository fruitRepository;

    @Override
    public List<Fruit> getAllFruitsAsc() {
        return fruitRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<Fruit> getAllFruitsDesc() {
        return fruitRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public Optional<Fruit> getFruitById(Long id) {
        return fruitRepository.findById(id);
    }

    @Override
    public Fruit saveOrUpdateFruit(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    @Override
    public void deleteFruitById(Long id) {
        fruitRepository.deleteById(id);
    }

    @Override
    public List<Fruit> getFruitsByName(String name) {
        return fruitRepository.findByNameContainingIgnoreCase(name);
    }
}
