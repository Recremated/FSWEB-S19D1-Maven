package com.workintech.s18d2.services;

import com.workintech.s18d2.dao.VegetableRepository;
import com.workintech.s18d2.entity.Vegetable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VegetableServiceImpl implements VegetableService {

    private final VegetableRepository vegetableRepository;

    @Override
    public List<Vegetable> getAllVegetablesAsc() {
        return vegetableRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<Vegetable> getAllVegetablesDesc() {
        return vegetableRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public Optional<Vegetable> getVegetableById(Long id) {
        return vegetableRepository.findById(id);
    }

    @Override
    public Vegetable saveOrUpdateVegetable(Vegetable vegetable) {
        return vegetableRepository.save(vegetable);
    }

    @Override
    public void deleteVegetableById(Long id) {
        vegetableRepository.deleteById(id);
    }

    @Override
    public List<Vegetable> getVegetablesByName(String name) {
        return vegetableRepository.findByNameContainingIgnoreCase(name);
    }
}
