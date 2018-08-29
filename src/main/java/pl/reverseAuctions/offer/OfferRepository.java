package pl.reverseAuctions.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Offer findById(Long id);

    List<Offer> findAllByUser_Id(Long id);

    List<Offer> findByAuctionIdOrderByPriceAsc(Long id);

}
