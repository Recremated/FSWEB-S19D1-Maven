package com.workintech.s18d2.dao;

import com.workintech.s18d2.entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VegetableRepository extends JpaRepository<Vegetable, Long> {

    List<Vegetable> findAllByOrderByPriceAsc();

    List<Vegetable> findAllByOrderByPriceDesc();

    List<Vegetable> findByNameContainingIgnoreCase(String name);
}
