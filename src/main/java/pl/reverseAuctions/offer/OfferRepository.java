package pl.reverseAuctions.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Offer findById(Long id);
}
