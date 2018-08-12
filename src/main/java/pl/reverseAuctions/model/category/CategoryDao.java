package pl.reverseAuctions.model.category;

import org.springframework.stereotype.Repository;
import pl.reverseAuctions.model.Entity;

import java.util.List;

@Repository
public class CategoryDao implements Entity<Category> {

    @Override
    public void saveToDb(Category model) {

    }

    @Override
    public void delete(Category model) {

    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public Category getById(int id) {
        return null;
    }
}
