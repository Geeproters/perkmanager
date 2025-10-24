package com.example.perkmanager.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username associated with account
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    // Password hash (never store plain passwords!)
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    // Perks created by this account
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Perk> perks = new HashSet<>();

    // Perks the user upvoted
    @ManyToMany(mappedBy = "upvotedBy")
    private Set<Perk> upvotedPerks = new HashSet<>();

    // Perks the user downvoted
    @ManyToMany(mappedBy = "downvotedBy")
    private Set<Perk> downvotedPerks = new HashSet<>();

    public Account() {}

    // --- Getters and Setters ---
    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public Set<Perk> getPerks() { return perks; }
    public void setPerks(Set<Perk> perks) { this.perks = perks; }

    public Set<Perk> getUpvotedPerks() { return upvotedPerks; }
    public void setUpvotedPerks(Set<Perk> upvotedPerks) { this.upvotedPerks = upvotedPerks; }

    public Set<Perk> getDownvotedPerks() { return downvotedPerks; }
    public void setDownvotedPerks(Set<Perk> downvotedPerks) { this.downvotedPerks = downvotedPerks; }
}
