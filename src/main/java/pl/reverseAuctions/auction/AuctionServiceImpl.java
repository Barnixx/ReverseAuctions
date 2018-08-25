package pl.reverseAuctions.auction;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;

    public AuctionServiceImpl(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Override
    public void save(Auction auction) {
        auctionRepository.save(auction);
    }

    @Override
    public Auction getById(Long id) {
        return auctionRepository.findById(id);
    }

    @Override
    public List<Auction> getAll() {
        return auctionRepository.findAll();
    }

    @Override
    public void delete(Auction auction) {
        auctionRepository.delete(auction);
    }

    @Override
    public void delete(Long id) {
        auctionRepository.delete(id);
    }
}
