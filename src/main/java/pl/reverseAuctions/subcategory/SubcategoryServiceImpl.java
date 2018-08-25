package pl.reverseAuctions.subcategory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public void save(Subcategory subcategory) {
        subcategoryRepository.save(subcategory);
    }

    @Override
    public Subcategory getById(Long id) {
        return subcategoryRepository.findById(id);
    }

    @Override
    public List<Subcategory> getAll() {
        return subcategoryRepository.findAll();
    }

    @Override
    public void delete(Subcategory subcategory) {
        subcategoryRepository.delete(subcategory);
    }

    @Override
    public void delete(Long id) {
        subcategoryRepository.delete(id);
    }
}
