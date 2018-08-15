package pl.reverseAuctions.model.auction;

import org.springframework.stereotype.Repository;
import pl.reverseAuctions.model.Entity;

import java.util.List;

@Repository
public class AuctionDao implements Entity<Auction> {

    private final String SAVE = "";

    @Override
    public void saveToDb(Auction model) {

    }

    @Override
    public void delete(Auction model) {

    }

    @Override
    public List<Auction> getAll() {
        return null;
    }

    @Override
    public Auction getById(Long id) {
        return null;
    }
}
