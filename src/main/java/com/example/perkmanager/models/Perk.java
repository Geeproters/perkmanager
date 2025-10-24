package com.example.perkmanager.models;

import jakarta.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "perks")
public class Perk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Description of the benefit, e.g., "10% off next flight"
    @Column(nullable = false)
    private String benefit;

    // Optional expiry date
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Calendar expiryDate;

    // Region where the perk applies
    @Column(nullable = true)
    private String region;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "membership_id", nullable = false)
    private Membership membership;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Account creator;

    // Upvotes
    @ManyToMany
    @JoinTable(
            name = "perk_upvotes",
            joinColumns = @JoinColumn(name = "perk_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<Account> upvotedBy = new HashSet<>();

    // Downvotes
    @ManyToMany
    @JoinTable(
            name = "perk_downvotes",
            joinColumns = @JoinColumn(name = "perk_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<Account> downvotedBy = new HashSet<>();

    // --- Constructors ---
    public Perk() {}

    public Perk(Membership membership, Product product, String benefit) {
        this.membership = membership;
        this.product = product;
        this.benefit = benefit;
        this.expiryDate = new GregorianCalendar(); // default to "now"
    }

    // --- ID ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // --- Benefit ---
    public String getBenefit() { return benefit; }
    public void setBenefit(String benefit) { this.benefit = benefit; }

    // --- Expiry Date ---
    public Calendar getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Calendar expiryDate) { this.expiryDate = expiryDate; }

    // --- Region ---
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    // --- Membership ---
    public Membership getMembership() { return membership; }
    public void setMembership(Membership membership) { this.membership = membership; }

    // --- Product ---
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    // --- Creator ---
    public Account getCreator() { return creator; }
    public void setCreator(Account creator) { this.creator = creator; }

    // --- Upvotes ---
    public int getUpvotes() { return upvotedBy.size(); }

    public Set<Account> getUpvoteList() { return upvotedBy; }

    public void setUpvoteList(Set<Account> upvoteList) { this.upvotedBy = upvoteList; }

    public void addUpvote(Account account) { upvotedBy.add(account); }

    public boolean removeUpvote(Account account) { return upvotedBy.remove(account); }

    // --- Downvotes ---
    public int getDownvotes() { return downvotedBy.size(); }

    public Set<Account> getDownvoteList() { return downvotedBy; }

    public void setDownvoteList(Set<Account> downvoteList) { this.downvotedBy = downvoteList; }

    public void addDownvote(Account account) { downvotedBy.add(account); }

    public boolean removeDownvote(Account account) { return downvotedBy.remove(account); }

    // --- Derived Metrics ---
    public int getRating() {
        return getUpvotes() - getDownvotes();
    }

    public int getTotalRatings() {
        return getUpvotes() + getDownvotes();
    }
}
