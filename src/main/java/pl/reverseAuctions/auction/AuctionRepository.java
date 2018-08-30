package pl.reverseAuctions.auction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    Auction findById(Long id);

    List<Auction> findBySubcategory_Id(Long id);

    Page<Auction> findBySubcategory_Id(Long id, Pageable pageable);

    List<Auction> findBySubcategory_Category_Id(Long id);

    Page<Auction> findBySubcategory_Category_Id(Long id, Pageable pageable);
    List<Auction> findAllByUser_Id(Long id);

    Page<Auction> findAllByNameContaining(String name, Pageable pageable);

    Page<Auction> findAllByNameContainingAndSubcategory_Category_Id(String name, Long id, Pageable pageable);

}

