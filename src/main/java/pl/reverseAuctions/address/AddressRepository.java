package pl.reverseAuctions.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.reverseAuctions.user.User;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findById(Long id);

    Address findByUser(User user);
    Address findByUser_Id(Long id);

    List<Address> findAllByUser_Id(Long id);
}
