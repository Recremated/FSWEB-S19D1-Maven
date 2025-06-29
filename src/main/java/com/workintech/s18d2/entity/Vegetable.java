package com.workintech.s18d2.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "vegetable")
public class Vegetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "grown_on_tree")
    private Boolean grownOnTree;

    // Constructors
    public Vegetable() {}

    public Vegetable(String name, Double price, Boolean grownOnTree) {
        this.name = name;
        this.price = price;
        this.grownOnTree = grownOnTree;
    }

    // Getters and Setters
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getGrownOnTree() {
        return grownOnTree;
    }

    public Boolean getIsGrownOnTree() {
        return grownOnTree;
    }

    public boolean isGrownOnTree() {
        return grownOnTree != null ? grownOnTree : false;
    }

    public void setGrownOnTree(Boolean grownOnTree) {
        this.grownOnTree = grownOnTree;
    }
}