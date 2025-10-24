package com.example.perkmanager.models;

import jakarta.persistence.*;

@Entity
@Table(name = "memberships")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Type of membership, e.g., Credit Card, Air Miles, etc.
    @Column(name = "type", nullable = false)
    private String type;

    // Organization associated with the membership, e.g., RBC, WestJet, etc.
    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    // Name/description of the membership
    @Column(name = "description")
    private String description;

    public Membership() {}

    public Membership(String type, String organizationName, String description) {
        this.type = type;
        this.organizationName = organizationName;
        this.description = description;
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getOrganizationName() { return organizationName; }
    public void setOrganizationName(String organizationName) { this.organizationName = organizationName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
