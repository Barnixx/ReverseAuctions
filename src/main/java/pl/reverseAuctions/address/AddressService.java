package pl.reverseAuctions.address;

import org.springframework.stereotype.Service;
import pl.reverseAuctions.user.User;

import java.util.List;

@Service
public interface AddressService {
    void save(Address address);

    Address getById(Long id);

    Address getByUser(User user);

    Address getByUser_Id(Long id);

    void delete(Long id);

    void delete(Address address);

    List<Address> getAll();

    List<Address> getAllByUser_Id(Long id);
}
