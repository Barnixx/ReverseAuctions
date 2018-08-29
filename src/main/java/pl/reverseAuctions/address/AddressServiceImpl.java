package pl.reverseAuctions.address;

import org.springframework.stereotype.Service;
import pl.reverseAuctions.user.User;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void delete(Address address) {
        addressRepository.delete(address);
    }

    @Override
    public void delete(Long id) {
        addressRepository.delete(id);
    }

    @Override
    public Address getById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address getByUser(User user) {
        return addressRepository.findByUser(user);
    }

    @Override
    public Address getByUser_Id(Long id) {
        return addressRepository.findByUser_Id(id);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public List<Address> getAllByUser_Id(Long id) {
        return addressRepository.findAllByUser_Id(id);
    }

}
