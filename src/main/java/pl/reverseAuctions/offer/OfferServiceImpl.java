package pl.reverseAuctions.offer;

import org.springframework.stereotype.Service;
import pl.reverseAuctions.user.User;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void save(Offer offer) {
        offerRepository.save(offer);
    }

    @Override
    public Offer getById(Long id) {
        return offerRepository.findById(id);
    }

    @Override
    public List<Offer> getAll() {
        return offerRepository.findAll();
    }

    @Override
    public List<Offer> getAllByUser(Long id) {
        return offerRepository.findAllByUser_Id(id);
    }

    @Override
    public void delete(Long id) {
        offerRepository.delete(id);
    }

    @Override
    public void delete(Offer offer) {
        offerRepository.delete(offer);
    }

    @Override
    public List<Offer> getOfferByAuctionId(Long id) {
        return offerRepository.findByAuctionIdOrderByPriceAsc(id);
    }

    @Override
    public User getUserByOfferId(Long id){ return offerRepository.findById(id).getUser();};
}
