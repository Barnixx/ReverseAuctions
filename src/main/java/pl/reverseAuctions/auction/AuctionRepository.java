package pl.reverseAuctions.auction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    Auction findById(Long id);

    List<Auction> findBySubcategory_Id(Long id);

    List<Auction> findBySubcategory_Category_Id(Long id);

    List<Auction> findAllByUser_Id(Long id);
}

