package pl.reverseAuctions.auction;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuctionService {
    void save(Auction auction);

    Auction getById(Long id);

    List<Auction> getAll();

    void delete(Auction auction);

    void delete(Long id);

    List<Auction> getAuctionsBySubcategoryId(Long id);

    List<Auction> getAuctionsByCategoryId(Long id);

    List<Auction> getAuctionsByUser(Long id);
}
