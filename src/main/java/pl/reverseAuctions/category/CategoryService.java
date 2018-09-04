package pl.reverseAuctions.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.reverseAuctions.subcategory.Subcategory;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

@Service
public interface CategoryService {
    void save(Category category);

    void delete(Long id);

    void delete(Category category);

    Category getById(Long id);

    List<Category> getAll();

    Map<Category, List<Subcategory>> getAllCategoriesWithSubcategories();

    Page<Category> getAll(Pageable pageable);

    LinkedHashSet<Category> getRootCategory();

    LinkedHashSet<Category> getSubCategory(Long id);
}
