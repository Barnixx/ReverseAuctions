package pl.reverseAuctions.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.LinkedHashSet;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findById(Long id);


    LinkedHashSet<Category> findByParentCategoryIsNull();

    LinkedHashSet<Category> findByParentCategory_Id(Long id);


}
