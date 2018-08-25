package pl.reverseAuctions.address;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    void save(Address address);

    Address getById(Long id);

    void delete(Long id);

    void delete(Address address);

    List<Address> getAll();
}
