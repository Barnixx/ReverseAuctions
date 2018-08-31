package pl.reverseAuctions.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void save(User user);

    void update (User user);

    User getById(Long id);

    List<User> getAll();

    void delete(User user);

    void delete(Long id);

    User findByUserName(String username);

    Boolean isUserExist(String username);

    Boolean isUserMailExist(String mail);

}
