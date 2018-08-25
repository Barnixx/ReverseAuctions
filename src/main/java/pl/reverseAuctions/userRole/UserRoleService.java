package pl.reverseAuctions.userRole;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRoleService {
    void save(UserRole userRole);

    UserRole getById(Long id);

    List<UserRole> getAll();

    void delete(Long id);

    void delete(UserRole userRole);
}
