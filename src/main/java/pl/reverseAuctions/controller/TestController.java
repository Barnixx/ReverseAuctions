package pl.reverseAuctions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.reverseAuctions.model.auction.Auction;
import pl.reverseAuctions.model.auction.AuctionDao;

import java.sql.SQLException;

@Controller
public class TestController {

    @Autowired
    AuctionDao auctionDao;

    @RequestMapping("/")
    public String index() {
        Auction byId;
        try {
            byId = auctionDao.getById(1L);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "index";
    }
}