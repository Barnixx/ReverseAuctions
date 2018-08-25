package pl.reverseAuctions.category;

import org.springframework.stereotype.Service;
import pl.reverseAuctions.subcategory.Subcategory;
import pl.reverseAuctions.subcategory.SubcategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubcategoryService subcategoryService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, SubcategoryService subcategoryService) {
        this.categoryRepository = categoryRepository;
        this.subcategoryService = subcategoryService;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Map<Category, List<Subcategory>> getAllCategoriesWithSubcategories() {
        Map<Category, List<Subcategory>> subcategoryMap = new HashMap<>();

        List<Category> categories = categoryRepository.findAll();
        System.out.println(categories);
        List<Subcategory> subcategories = subcategoryService.getAll();
        System.out.println(subcategories);
        for (Category category : Objects.requireNonNull(categories)) {
            subcategoryMap.put(category, Objects.requireNonNull(subcategories)
                    .stream()
                    .filter(sub -> sub.getCategory().getId().equals(category.getId()))
                    .collect(Collectors.toList()));
        }
        return subcategoryMap;
    }
}
