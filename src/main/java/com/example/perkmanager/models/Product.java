package com.example.perkmanager.models;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the product, e.g., KitKat
    @Column(name = "name", nullable = false)
    private String name;

    // Optional description of the product
    @Column(name = "description")
    private String description;

    // Company associated with the product, e.g., Nestlé
    @Column(name = "company", nullable = false)
    private String company;

    public Product() {}

    // --- Getters and Setters ---
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
}
