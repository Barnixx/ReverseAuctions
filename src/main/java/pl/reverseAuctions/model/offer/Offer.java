package pl.reverseAuctions.model.offer;

import pl.reverseAuctions.model.auction.Auction;
import pl.reverseAuctions.model.user.User;

import java.time.LocalDateTime;

public class Offer {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private User user;
    private Auction auction;
    private String addTime;

    public Offer() {
    }

    public Offer(String title, String description, Double price, User user, Auction auction) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.user = user;
        this.auction = auction;
        this.addTime = LocalDateTime.now().toString();
    }

    public Offer(Long id, String title, String description, Double price, User user, Auction auction) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.user = user;
        this.auction = auction;
        this.addTime = LocalDateTime.now().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
