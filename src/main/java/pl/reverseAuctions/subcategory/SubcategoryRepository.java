package pl.reverseAuctions.subcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Subcategory findById(Long id);
}
