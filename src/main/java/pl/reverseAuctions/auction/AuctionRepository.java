package pl.reverseAuctions.auction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.reverseAuctions.category.Category;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    Auction findById(Long id);

    List<Auction> findByCategory_Id(Long id);

    Page<Auction> findByCategory_Id(Long id, Pageable pageable);

    Page<Auction> findAllByCategory_IdAndEndTimeGreaterThanEqual(Long id, LocalDate endTime, Pageable pageable);

    List<Auction> findByCategory_ParentCategory_Id(Long id);

    Page<Auction> findByCategory_ParentCategory_Id(Long id, Pageable pageable);
    List<Auction> findAllByUser_Id(Long id);

    Page<Auction> findAllByNameContaining(String name, Pageable pageable);

    Page<Auction> findAllByCategory_ParentCategory_IdAndEndTimeGreaterThanEqual(Long id, LocalDate endTime, Pageable pageable);

    Page<Auction> findAllByNameContainingAndCategory_ParentCategory_Id(String name, Long id, Pageable pageable);

    Page<Auction> findDistinctByCategoryInAndEndTimeGreaterThanEqual(Set<Category> categories, LocalDate localDate, Pageable pageable);

}

