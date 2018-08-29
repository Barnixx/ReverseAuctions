package pl.reverseAuctions.role;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    void save(Role userRole);

    Role getById(Long id);

    List<Role> getAll();

    void delete(Long id);

    void delete(Role userRole);
}
