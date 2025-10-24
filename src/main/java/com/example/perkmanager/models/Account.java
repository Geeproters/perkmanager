package com.example.perkmanager.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
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

    // Password hash (never store plain text in production)
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

    // --- Constructors ---

    public Account() {}

    public Account(String username, String password) {
        this.username = username;
        this.passwordHash = password; // Placeholder – hash later with BCrypt
        this.perks = new HashSet<>();
    }

    // --- Getters & Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    //TODO Check database for uniqueness when setting username
    public void setUsername(String username) { this.username = username; }

    //TODO implement password hashing and checking
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    // For test convenience (plaintext password setter)
    public void setPassword(String password) { this.passwordHash = password; }
    public boolean isCorrectPassword(String password) {
        return this.passwordHash != null && this.passwordHash.equals(password);
    }

    public Set<Perk> getPerks() { return perks; }
    public void setPerks(Set<Perk> perks) { this.perks = perks; }

    public Set<Perk> getUpvotedPerks() { return upvotedPerks; }
    public void setUpvotedPerks(Set<Perk> upvotedPerks) { this.upvotedPerks = upvotedPerks; }

    public Set<Perk> getDownvotedPerks() { return downvotedPerks; }
    public void setDownvotedPerks(Set<Perk> downvotedPerks) { this.downvotedPerks = downvotedPerks; }

    // --- Domain Logic Methods ---

    /** Add a perk created by this account */
    public void addPerk(Perk perk) {
        if (perk != null) {
            perks.add(perk);
            perk.setCreator(this);
        }
    }

    /** Remove a perk by entity reference */
    public boolean removePerk(Perk perk) {
        if (perk == null) return false;
        perk.setCreator(null);
        return perks.remove(perk);
    }

    /**
     * Remove a perk by ID and return the removed object (or null)
     */
    public Perk removePerk(Long id) {
        if (id == null) return null;
        Iterator<Perk> iterator = perks.iterator();
        while (iterator.hasNext()) {
            Perk perk = iterator.next();
            if (id.equals(perk.getId())) {
                iterator.remove();
                perk.setCreator(null);
                return perk;
            }
        }
        return null;
    }

    // --- Equality and Hashing (for Set behavior) ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account other = (Account) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
