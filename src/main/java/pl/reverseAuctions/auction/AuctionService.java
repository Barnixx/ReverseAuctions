package pl.reverseAuctions.auction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuctionService {
    Auction save(Auction auction);

    Auction getById(Long id);

    List<Auction> getAll();

    void delete(Auction auction);

    void delete(Long id);

    List<Auction> getAuctionsBySubcategoryId(Long id);

    Page<Auction> getAuctionsBySubcategoryId(Long id, Pageable pageable);

    List<Auction> getAuctionsByCategoryId(Long id);

    Page<Auction> getAuctionsByCategoryId(Long id, Pageable pageable);

    List<Auction> getAuctionsByUser(Long id);

    Page<Auction> findAll(Pageable pageable);

    Page<Auction> getAuctionsByName(String name, Pageable pageable);

    Page<Auction> getAllByNameAndCategory_Id(String name, Long id, Pageable pageable);
}
