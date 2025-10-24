package com.example.perkmanager.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "perks")
public class Perk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Description or benefit of the perk, e.g., "10% off next flight"
    @Column(name = "benefit", nullable = false)
    private String benefit;

    // Optional expiry date
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    // Optional region(s) — could later be normalized to a Region table
    @Column(name = "region")
    private String region;

    // Many perks can be associated with one membership
    @ManyToOne
    @JoinColumn(name = "membership_id", nullable = false)
    private Membership membership;

    // Many perks can be associated with one product
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Account that created the perk
    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Account creator;

    // Users who upvoted this perk
    @ManyToMany
    @JoinTable(
            name = "perk_upvotes",
            joinColumns = @JoinColumn(name = "perk_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<Account> upvotedBy = new HashSet<>();

    // Users who downvoted this perk
    @ManyToMany
    @JoinTable(
            name = "perk_downvotes",
            joinColumns = @JoinColumn(name = "perk_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<Account> downvotedBy = new HashSet<>();

    public Perk() {}

    // --- Getters and Setters ---
    public Long getId() { return id; }

    public String getBenefit() { return benefit; }
    public void setBenefit(String benefit) { this.benefit = benefit; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public Membership getMembership() { return membership; }
    public void setMembership(Membership membership) { this.membership = membership; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Account getCreator() { return creator; }
    public void setCreator(Account creator) { this.creator = creator; }

    public Set<Account> getUpvotedBy() { return upvotedBy; }
    public void setUpvotedBy(Set<Account> upvotedBy) { this.upvotedBy = upvotedBy; }

    public Set<Account> getDownvotedBy() { return downvotedBy; }
    public void setDownvotedBy(Set<Account> downvotedBy) { this.downvotedBy = downvotedBy; }
}
