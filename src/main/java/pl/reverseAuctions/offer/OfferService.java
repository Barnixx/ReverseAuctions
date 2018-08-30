package pl.reverseAuctions.offer;

import org.springframework.stereotype.Service;
import pl.reverseAuctions.user.User;

import java.util.List;

@Service
public interface OfferService {

    void save(Offer offer);

    Offer getById(Long id);

    List<Offer> getAll();

    List<Offer> getAllByUser(Long id);

    void delete(Long id);

    void delete(Offer offer);

    List<Offer> getOfferByAuctionId(Long id);

    User getUserByOfferId(Long id);
}
