package pl.reverseAuctions.subcategory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubcategoryService {

    void save(Subcategory subcategory);

    Subcategory getById(Long id);

    List<Subcategory> getAll();

    void delete(Subcategory subcategory);

    void delete(Long id);
}
