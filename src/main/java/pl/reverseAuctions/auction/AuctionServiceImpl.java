package pl.reverseAuctions.auction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;

    public AuctionServiceImpl(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Override
    public Auction save(Auction auction) {
        return auctionRepository.save(auction);
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

    @Override
    public List<Auction> getAuctionsBySubcategoryId(Long id) {
        return auctionRepository.findBySubcategory_Id(id);
    }

    @Override
    public Page<Auction> getAuctionsBySubcategoryId(Long id, Pageable pageable) {
        return auctionRepository.findBySubcategory_Id(id, pageable);
    }

    @Override
    public List<Auction> getAuctionsByCategoryId(Long id) {
        return auctionRepository.findBySubcategory_Category_Id(id);
    }

    @Override
    public Page<Auction> getAuctionsByCategoryId(Long id, Pageable pageable) {
        return auctionRepository.findBySubcategory_Category_Id(id, pageable);
    }

    @Override
    public List<Auction> getAuctionsByUser(Long id) {
        return auctionRepository.findAllByUser_Id(id);
    }

    @Override
    public Page<Auction> findAll(Pageable pageable) {
        return auctionRepository.findAll(pageable);
    }

    @Override
    public Page<Auction> getAuctionsByName(String name, Pageable pageable) {
        return auctionRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Page<Auction> getAllByNameAndCategory_Id(String name, Long id, Pageable pageable) {
        return auctionRepository.findAllByNameContainingAndSubcategory_Category_Id(name, id, pageable);
    }

}
