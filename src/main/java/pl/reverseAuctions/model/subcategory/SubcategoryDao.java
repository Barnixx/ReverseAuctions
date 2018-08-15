package pl.reverseAuctions.model.subcategory;

import org.springframework.stereotype.Repository;
import pl.reverseAuctions.model.Entity;

import java.util.List;

@Repository
public class SubcategoryDao implements Entity<Subcategory> {
    @Override
    public void saveToDb(Subcategory model) {

    }

    @Override
    public void delete(Subcategory model) {

    }

    @Override
    public List<Subcategory> getAll() {
        return null;
    }

    @Override
    public Subcategory getById(Long id) {
        return null;
    }
}
