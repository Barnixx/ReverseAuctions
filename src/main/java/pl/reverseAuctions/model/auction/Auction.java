package pl.reverseAuctions.model.auction;

import pl.reverseAuctions.model.offer.Offer;
import pl.reverseAuctions.model.subcategory.Subcategory;
import pl.reverseAuctions.model.user.User;

import java.time.LocalDateTime;

public class Auction {

    private Long id;
    private String auctionName;
    private String auctionDescription;
    private Subcategory Subcategory;
    private User User;
    private LocalDateTime auctionStartTime;
    private LocalDateTime auctionEndTime;
    private boolean auctionIsActive;
    private Long auctionView;
    private Offer auctionWinOfferId;

    public Auction() {
    }

    public Auction(Long id, String auctionName, String auctionDescription,
                   pl.reverseAuctions.model.subcategory.Subcategory subcategory,
                   pl.reverseAuctions.model.user.User user, LocalDateTime auctionStartTime,
                   LocalDateTime auctionEndTime, boolean auctionIsActive,
                   Long auctionView, Offer auctionWinOfferId) {
        this.id = id;
        this.auctionName = auctionName;
        this.auctionDescription = auctionDescription;
        Subcategory = subcategory;
        User = user;
        this.auctionStartTime = auctionStartTime;
        this.auctionEndTime = auctionEndTime;
        this.auctionIsActive = auctionIsActive;
        this.auctionView = auctionView;
        this.auctionWinOfferId = auctionWinOfferId;
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

    public pl.reverseAuctions.model.subcategory.Subcategory getSubcategory() {
        return Subcategory;
    }

    public void setSubcategory(pl.reverseAuctions.model.subcategory.Subcategory subcategory) {
        Subcategory = subcategory;
    }

    public pl.reverseAuctions.model.user.User getUser() {
        return User;
    }

    public void setUser(pl.reverseAuctions.model.user.User user) {
        User = user;
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

    public boolean isAuctionIsActive() {
        return auctionIsActive;
    }

    public void setAuctionIsActive(boolean auctionIsActive) {
        this.auctionIsActive = auctionIsActive;
    }

    public Long getAuctionView() {
        return auctionView;
    }

    public void setAuctionView(Long auctionView) {
        this.auctionView = auctionView;
    }

    public Offer getAuctionWinOfferId() {
        return auctionWinOfferId;
    }

    public void setAuctionWinOfferId(Offer auctionWinOfferId) {
        this.auctionWinOfferId = auctionWinOfferId;
    }
}
