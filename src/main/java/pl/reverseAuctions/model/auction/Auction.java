package pl.reverseAuctions.model.auction;

import pl.reverseAuctions.model.offer.Offer;
import pl.reverseAuctions.model.subcategory.Subcategory;
import pl.reverseAuctions.model.user.User;

import java.time.LocalDateTime;

public class Auction {

    private Long id;
    private String auctionName;
    private String auctionDescription;
    private Subcategory subcategory;
    private User user;
    private LocalDateTime auctionStartTime;
    private LocalDateTime auctionEndTime;
    private byte auctionIsActive;
    private Long auctionView;
    private Offer auctionWinOffer;

    public Auction() {
    }

    public Auction(String auctionName, String auctionDescription,
                   Subcategory subcategory,
                   User user, LocalDateTime auctionStartTime,
                   LocalDateTime auctionEndTime, byte auctionIsActive,
                   Long auctionView, Offer auctionWinOffer) {
        this.auctionName = auctionName;
        this.auctionDescription = auctionDescription;
        this.subcategory = subcategory;
        this.user = user;
        this.auctionStartTime = auctionStartTime;
        this.auctionEndTime = auctionEndTime;
        this.auctionIsActive = auctionIsActive;
        this.auctionView = auctionView;
        this.auctionWinOffer = auctionWinOffer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public String getAuctionDescription() {
        return auctionDescription;
    }

    public void setAuctionDescription(String auctionDescription) {
        this.auctionDescription = auctionDescription;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getAuctionStartTime() {
        return auctionStartTime;
    }

    public void setAuctionStartTime(LocalDateTime auctionStartTime) {
        this.auctionStartTime = auctionStartTime;
    }

    public LocalDateTime getAuctionEndTime() {
        return auctionEndTime;
    }

    public void setAuctionEndTime(LocalDateTime auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }

    public byte isAuctionIsActive() {
        return auctionIsActive;
    }

    public void setAuctionIsActive(byte auctionIsActive) {
        this.auctionIsActive = auctionIsActive;
    }

    public Long getAuctionView() {
        return auctionView;
    }

    public void setAuctionView(Long auctionView) {
        this.auctionView = auctionView;
    }

    public Offer getAuctionWinOffer() {
        return auctionWinOffer;
    }

    public void setAuctionWinOffer(Offer auctionWinOffer) {
        this.auctionWinOffer = auctionWinOffer;
    }
}
