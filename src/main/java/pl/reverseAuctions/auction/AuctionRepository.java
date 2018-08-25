package pl.reverseAuctions.auction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    Auction findById(Long id);
}

