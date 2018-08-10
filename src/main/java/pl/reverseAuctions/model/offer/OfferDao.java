package pl.reverseAuctions.model.offer;

import org.springframework.stereotype.Repository;
import pl.reverseAuctions.model.Entity;

import java.util.List;

@Repository
public class OfferDao implements Entity<Offer> {
    @Override
    public void saveToDb(Offer model) {

    }

    @Override
    public void delete(Offer model) {

    }

    @Override
    public List<Offer> getAll() {
        return null;
    }

    @Override
    public Offer getById(int id) {
        return null;
    }
}
