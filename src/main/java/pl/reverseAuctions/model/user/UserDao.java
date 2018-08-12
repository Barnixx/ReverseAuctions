package pl.reverseAuctions.model.user;

import org.springframework.stereotype.Repository;
import pl.reverseAuctions.model.Entity;

import java.util.List;

@Repository
public class UserDao implements Entity<User> {
    @Override
    public void saveToDb(User model) {

    }

    @Override
    public void delete(User model) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }
}
